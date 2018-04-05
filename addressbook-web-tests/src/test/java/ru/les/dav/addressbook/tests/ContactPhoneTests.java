package ru.les.dav.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.ContactShortData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by saakovamr on 30.03.18.
 */
public class ContactPhoneTests extends TestBase {


   @BeforeMethod
   public void ensurePreConditions(){
      app.goTo().HomePage();
      if (app.contact().all().size() == 0){
         app.contact().create(new ContactShortData().withFirstName("Maria").withLastName("Davydenko")
                 .withAddress("Russia, Novosibirsk").withMobileNumber("9998887766")
                 .withWorkPhone("222").withHomePhone("555").withEmail("masha@gmail.com"));
      }
   }


   @Test
   public void testContactPhones(){
      app.goTo().HomePage();
      ContactShortData contact = app.contact().all().iterator().next();
      ContactShortData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

      assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));

   }

   private String mergePhones(ContactShortData contact) {
      return Arrays.asList(contact.getHomePhone(), contact.getMobileNumber(), contact.getWorkPhone())
              .stream().filter((s) -> ! s.equals(""))
              .map(ContactPhoneTests::cleaned)
              .collect(Collectors.joining("\n"));
   }

   public static String cleaned (String phone) {
      return  phone.replaceAll("\\s", "").replaceAll("[-()]", "");
   }
}
