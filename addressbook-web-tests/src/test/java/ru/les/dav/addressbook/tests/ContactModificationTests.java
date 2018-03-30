package ru.les.dav.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.ContactShortData;

import java.util.Set;

/**
 * Created by saakovamr on 14.02.18.
 */
public class ContactModificationTests extends TestBase {

   @BeforeMethod
   public void ensurePreConditions(){
      app.goTo().HomePage();
      if (app.contact().all().size() == 0) {
         app.contact().create(new ContactShortData().withFirstName("Maria").withLastName("Davydenko")
                 .withAddress("Russia, Novosibirsk").withMobileNumber("9998887766").withEmail("masha@gmail.com"));
      }
   }


   @Test
   public void testContactModification(){
      Set<ContactShortData> before = app.contact().all();
      ContactShortData modifiedContact = before.iterator().next();
      ContactShortData contact = new ContactShortData().withId(modifiedContact.getId())
              .withFirstName("NewName").withLastName("NewLastName");
      app.contact().modify(contact);
      app.goTo().HomePage();
      Set<ContactShortData> after = app.contact().all();
      Assert.assertEquals(before.size(), after.size());
      before.remove(modifiedContact);
      before.add(contact);
      /*Comparator<? super ContactShortData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
      before.sort(byId);
      after.sort(byId);*/
      Assert.assertEquals(before,after);
   }


}
