package ru.les.dav.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.ContactShortData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by saakovamr on 05.04.18.
 */
public class ContactAddressTest extends TestBase {

   @BeforeMethod
   public void ensurePreConditions(){
      app.goTo().HomePage();
      if (app.db().contacts().size() == 0){
         app.contact().create(new ContactShortData().withFirstName("Maria").withLastName("Davydenko")
                 .withAddress("Russia, Novosibirsk").withMobileNumber("9998887766")
                 .withWorkPhone("222").withHomePhone("555").withEmail("masha@gmail.com"));
      }
   }

   @Test
   public void testContactAddress(){
      app.goTo().HomePage();
      ContactShortData contact = app.contact().all().iterator().next();
      ContactShortData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

      assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));

   }
}
