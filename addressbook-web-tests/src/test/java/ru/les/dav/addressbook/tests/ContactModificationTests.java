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
      System.out.println(app.db().contacts().size());
      if (app.db().contacts().size() ==0){
         app.contact().create(new ContactShortData().withFirstName("Maria").withLastName("Davydenko")
                 .withAddress("Russia, Novosibirsk").withMobileNumber("9998887766").withEmail("masha@gmail.com"));
      }
   }


   @Test
   public void testContactModification(){
      Contacts before = app.db().contacts();
      ContactShortData modifiedContact = before.iterator().next();
      ContactShortData contact = new ContactShortData().withId(modifiedContact.getId())
              .withFirstName("NewName").withLastName("NewLastName").withEmail("testEmail")
              .withMobileNumber("123").withAddress("testAddress");
      app.contact().modify(contact);
      app.goTo().HomePage();
      assertThat(before.size(), equalTo(app.contact().count()));
      Contacts after = app.db().contacts();
      assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
      verifyContactListInUI();
   }


}
