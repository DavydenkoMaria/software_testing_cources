package ru.les.dav.addressbook.tests;


import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.ContactShortData;
import ru.les.dav.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest  extends TestBase{

    @Test
    public void testContactCreation() {
        app.goTo().HomePage();
        Contacts before = app.contact().all();
        ContactShortData contact = new ContactShortData().withFirstName("Maria").withLastName("Davydenko")
                .withAddress("Russia, Novosibirsk").withMobileNumber("9998887766").withEmail("masha@gmail.com");
        app.contact().create(contact);
        app.goTo().HomePage();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size()+1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
