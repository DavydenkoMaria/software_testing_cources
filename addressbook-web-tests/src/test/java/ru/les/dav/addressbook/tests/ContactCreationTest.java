package ru.les.dav.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.ContactShortData;
import ru.les.dav.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTest  extends TestBase{

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        List<ContactShortData> before = app.getContactHelper().getContactList();
        app.getContactHelper().createContact(new ContactShortData("Maria", "Davydenko", "Russia, Novosibirsk", "9998887766", "masha@gmail.com", "test", null));
        app.getNavigationHelper().gotoHomePage();
        List<ContactShortData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size()+1, after.size());
    }

}
