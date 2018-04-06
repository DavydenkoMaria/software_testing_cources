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
public class ContactDeletionTest extends TestBase {

   @BeforeMethod
   public void ensurePreConditions(){
      app.goTo().HomePage();
      if (app.db().contacts().size() ==0){
         app.contact().create(new ContactShortData().withFirstName("Maria").withLastName("Davydenko")
                 .withAddress("Russia, Novosibirsk").withMobileNumber("9998887766").withEmail("masha@gmail.com"));
      }
   }

   @Test
   public void testContactDeletion(){
      Contacts before = app.db().contacts();
      ContactShortData deletedContact = before.iterator().next();
      app.contact().delete(deletedContact);
      app.goTo().HomePage();
      assertThat(app.contact().count(), equalTo(before.size() -1));
      Contacts after = app.db().contacts();
      assertThat(after, equalTo(before.withOut(deletedContact)));
   }

}
