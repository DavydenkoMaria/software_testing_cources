package ru.les.dav.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.ContactShortData;
import ru.les.dav.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
      Contacts before = app.contact().all();
      ContactShortData modifiedContact = before.iterator().next();
      ContactShortData contact = new ContactShortData().withId(modifiedContact.getId())
              .withFirstName("NewName").withLastName("NewLastName");
      app.contact().modify(contact);
      app.goTo().HomePage();
      Contacts after = app.contact().all();
      assertThat(before.size(), equalTo(after.size()));
      assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
   }


}
