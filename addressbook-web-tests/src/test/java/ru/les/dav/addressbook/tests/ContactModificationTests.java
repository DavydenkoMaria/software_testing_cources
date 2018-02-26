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
      if (!app.getContactHelper().isThereAContact()){
         app.getContactHelper().createContact(new ContactShortData("Maria", "Davydenko", "Russia, Novosibirsk", "9998887766", "masha@gmail.com", "test", null));
      }
      app.getContactHelper().initContactModification();
      app.getContactHelper().fillContactForm(new ContactShortData(null, null, "new address", null, "newemail@mail.ru", null, null), false);
      app.getContactHelper().submitContactModification();
   }
}
