package ru.les.dav.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.ContactShortData;
import ru.les.dav.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest  extends TestBase{

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        List<ContactShortData> before = app.getContactHelper().getContactList();
        ContactShortData contact = new ContactShortData("Maria", "Davydenko", "Russia, Novosibirsk", "9998887766", "masha@gmail.com", "test", null);
        app.getContactHelper().createContact(contact);
        app.getNavigationHelper().gotoHomePage();
        List<ContactShortData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size()+1, after.size());
        before.add(contact);
        Comparator<? super ContactShortData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }

}
