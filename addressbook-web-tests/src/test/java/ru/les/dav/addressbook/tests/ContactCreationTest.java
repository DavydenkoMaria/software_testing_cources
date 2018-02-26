package ru.les.dav.addressbook.tests;

import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.ContactShortData;
import ru.les.dav.addressbook.model.GroupData;

public class ContactCreationTest  extends TestBase{

    @Test
    public void testContactCreation() {
        app.getContactHelper().createContact(new ContactShortData("Maria", "Davydenko", "Russia, Novosibirsk", "9998887766", "masha@gmail.com", "test", null));
    }

}
