package ru.les.dav.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.ContactShortData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest  extends TestBase{

    @Test
    public void testContactCreation() {
        app.goTo().HomePage();
        List<ContactShortData> before = app.contact().list();
        ContactShortData contact = new ContactShortData().withFirstName("Maria").withLastName("Davydenko")
                .withAddress("Russia, Novosibirsk").withMobileNumber("9998887766").withEmail("masha@gmail.com");
        app.contact().create(contact);
        app.goTo().HomePage();
        List<ContactShortData> after = app.contact().list();
        Assert.assertEquals(before.size()+1, after.size());
        before.add(contact);
        Comparator<? super ContactShortData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }
}
