package ru.les.dav.addressbook.tests;


import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.ContactShortData;
import ru.les.dav.addressbook.model.Contacts;
import ru.les.dav.addressbook.model.GroupData;
import ru.les.dav.addressbook.model.Groups;

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


    @BeforeMethod
    public void ensurePreConditions(){
        if (app.db().contacts().size() ==0){
            app.contact().create(new ContactShortData().withFirstName("Maria").withLastName("Davydenko")
                    .withAddress("Russia, Novosibirsk").withMobileNumber("9998887766").withEmail("masha@gmail.com"));
        }
        //app.goTo().HomePage();
    }

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        try(BufferedReader reader  = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(ContactShortData.class);
            List<ContactShortData> contacts = (List<ContactShortData>) xstream.fromXML(xml);
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }
    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactShortData contact) {
        app.goTo().HomePage();
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        File photo = new File("src/test/resources/cat.jpg");
        ContactShortData newContact = new ContactShortData().withFirstName("Maria").withLastName("Davydenko")
                .withAddress("Russia, Novosibirsk").withMobileNumber("9998887766").withEmail("masha@gmail.com")
                .inGroup(groups.iterator().next());
        app.contact().create(contact);
        app.goTo().HomePage();
        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        verifyContactListInUI();
    }

    @Test
    public void testBadContactCreation() {
        app.goTo().HomePage();
        Contacts before = app.db().contacts();
        ContactShortData contact = new ContactShortData().withFirstName("Maria'").withLastName("Davydenko")
                .withAddress("Russia, Novosibirsk").withMobileNumber("9998887766").withEmail("masha@gmail.com");
        app.contact().create(contact);
        app.goTo().HomePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before));
        verifyContactListInUI();
    }

}
