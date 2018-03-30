package ru.les.dav.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.ContactShortData;

import java.util.Set;

public class ContactCreationTest  extends TestBase{

    @Test
    public void testContactCreation() {
        app.goTo().HomePage();
        Set<ContactShortData> before = app.contact().all();
        ContactShortData contact = new ContactShortData().withFirstName("Maria").withLastName("Davydenko")
                .withAddress("Russia, Novosibirsk").withMobileNumber("9998887766").withEmail("masha@gmail.com");
        app.contact().create(contact);
        app.goTo().HomePage();
        Set<ContactShortData> after = app.contact().all();
        Assert.assertEquals(before.size()+1, after.size());
        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        /*Comparator<? super ContactShortData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);*/
        Assert.assertEquals(before,after);
    }
}
