package ru.les.dav.addressbook.tests;


import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.ContactShortData;
import ru.les.dav.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest  extends TestBase{

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        BufferedReader reader  = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactShortData.class);
        List<ContactShortData> contacts = (List<ContactShortData>) xstream.fromXML(xml);
        return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactShortData contact) {
        app.goTo().HomePage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/cat.jpg");
        /*ContactShortData contact = new ContactShortData().withFirstName("Maria").withLastName("Davydenko")
                .withAddress("Russia, Novosibirsk").withMobileNumber("9998887766").withEmail("masha@gmail.com")
                .withPhoto(photo);*/
        app.contact().create(contact);
        app.goTo().HomePage();
        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadContactCreation() {
        app.goTo().HomePage();
        Contacts before = app.contact().all();
        ContactShortData contact = new ContactShortData().withFirstName("Maria'").withLastName("Davydenko")
                .withAddress("Russia, Novosibirsk").withMobileNumber("9998887766").withEmail("masha@gmail.com");
        app.contact().create(contact);
        app.goTo().HomePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }

}
