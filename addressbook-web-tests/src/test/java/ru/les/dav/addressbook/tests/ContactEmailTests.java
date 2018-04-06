package ru.les.dav.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.ContactShortData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by saakovamr on 05.04.18.
 */
public class ContactEmailTests extends TestBase {
   @BeforeMethod
   public void ensurePreConditions(){
      app.goTo().HomePage();
      if (app.db().contacts().size() == 0){
         app.contact().create(new ContactShortData().withFirstName("Maria").withLastName("Davydenko")
                 .withAddress("Russia, Novosibirsk").withMobileNumber("9998887766")
                 .withWorkPhone("222").withHomePhone("555").withEmail("masha@gmail.com")
                 .withEmail2("test@mail"));
      }
   }

   @Test
   public void testContactEmails(){
      app.goTo().HomePage();
      ContactShortData contact = app.contact().all().iterator().next();
      ContactShortData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
      assertThat(contact.getAllEmails(), equalTo(mergeEmials(contactInfoFromEditForm)));
   }

   private String mergeEmials(ContactShortData contact) {
      return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
              .stream().filter((s) -> ! s.equals(""))
              .collect(Collectors.joining("\n"));
   }

}
