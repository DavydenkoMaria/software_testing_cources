package ru.les.dav.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.ContactShortData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by saakovamr on 14.02.18.
 */
public class ContactModificationTests extends TestBase {

   @BeforeMethod
   public void ensurePreConditions(){
      app.goTo().HomePage();
      if (app.contact().list().size() == 0){
         app.contact().create(new ContactShortData("Maria", "Davydenko", "Russia, Novosibirsk", "9998887766", "masha@gmail.com", "test", null));
      }
   }


   @Test
   public void testContactModification(){
      List<ContactShortData> before = app.contact().list();
      ContactShortData contact = new ContactShortData(before.get(before.size()-1).getId(),"NewName", "NewLastName", null, null, null, null, null);
      app.contact().modify(before, contact);
      app.goTo().HomePage();
      List<ContactShortData> after = app.contact().list();
      Assert.assertEquals(before.size(), after.size());
      before.remove(before.size()-1);
      before.add(contact);
      Comparator<? super ContactShortData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(before,after);
   }


}
