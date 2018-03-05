package ru.les.dav.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.ContactShortData;

import java.util.Comparator;
import java.util.List;

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
      List<ContactShortData> before = app.getContactHelper().getContactList();
      app.getContactHelper().initContactModification(before.size()+1);
      ContactShortData contact = new ContactShortData(before.get(before.size()-1).getId(),"NewName", "NewLastName", null, null, null, null, null);
      app.getContactHelper().fillContactForm(contact, false);
      app.getContactHelper().submitContactModification();
      app.getNavigationHelper().gotoHomePage();
      List<ContactShortData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(before.size(), after.size());
      before.remove(before.size()-1);
      before.add(contact);
      Comparator<? super ContactShortData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(before,after);
   }
}
