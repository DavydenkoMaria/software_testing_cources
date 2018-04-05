package ru.les.dav.addressbook.tests;


import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.ContactShortData;
import ru.les.dav.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest  extends TestBase{

    @Test
    public void testContactCreation() {
        app.goTo().HomePage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/cat.jpg");
        ContactShortData contact = new ContactShortData().withFirstName("Maria").withLastName("Davydenko")
                .withAddress("Russia, Novosibirsk").withMobileNumber("9998887766").withEmail("masha@gmail.com")
                .withPhoto(photo);
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
