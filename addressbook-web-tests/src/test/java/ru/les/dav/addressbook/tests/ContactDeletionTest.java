package ru.les.dav.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.ContactShortData;

import java.util.List;

/**
 * Created by saakovamr on 14.02.18.
 */
public class ContactDeletionTest extends TestBase {

   @BeforeMethod
   public void ensurePreConditions(){
      app.goTo().HomePage();
      if (app.contact().list().size() == 0){
         app.contact().create(new ContactShortData("Maria", "Davydenko", "Russia, Novosibirsk", "9998887766", "masha@gmail.com", "test", null));
      }
   }

   @Test
   public void testContactDeletion(){
      List<ContactShortData> before = app.contact().list();
      int index = before.size()-1;
      app.contact().delete(index);
      app.goTo().HomePage();
      List<ContactShortData> after = app.contact().list();
      Assert.assertEquals(index, after.size());
      before.remove(index);
      Assert.assertEquals(before,after);
   }

}
