package ru.les.dav.addressbook.tests;

import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.ContactShortData;

/**
 * Created by saakovamr on 14.02.18.
 */
public class ContactModificationTests extends TestBase {

   @Test
   public void testContactModification(){
      app.getNavigationHelper().gotoHomePage();
      app.getContactHelper().initContactModification();
      app.getContactHelper().fillContactForm(new ContactShortData("Masha", "Davydenk", "new address", "9875564764", "newemail@mail.ru", "test"));
      app.getContactHelper().submitContactModification();
   }
}
