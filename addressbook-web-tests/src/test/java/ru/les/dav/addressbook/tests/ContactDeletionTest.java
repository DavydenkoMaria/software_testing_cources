package ru.les.dav.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.ContactShortData;

import java.util.List;

/**
 * Created by saakovamr on 14.02.18.
 */
public class ContactDeletionTest extends TestBase {

   @Test
   public void testContactDeletion(){
      app.getNavigationHelper().gotoHomePage();
      if (!app.getContactHelper().isThereAContact()){
         app.getContactHelper().createContact(new ContactShortData("Maria", "Davydenko", "Russia, Novosibirsk", "9998887766", "masha@gmail.com", "test", null));
      }
      List<ContactShortData> before = app.getContactHelper().getContactList();
      app.getContactHelper().selectContact();
      app.getContactHelper().deleteContactDeletion();
      app.getContactHelper().acceptContactDeletion();
      app.getNavigationHelper().gotoHomePage();
      List<ContactShortData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(before.size()-1, after.size());
   }
}
