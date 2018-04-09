package ru.les.dav.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.ContactShortData;
import ru.les.dav.addressbook.model.Contacts;
import ru.les.dav.addressbook.model.GroupData;
import ru.les.dav.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by saakovamr on 09.04.18.
 */
public class ContactRemoveFromGroupTest extends TestBase {
   @BeforeMethod
   public void ensurePreConditions(){
      if (app.db().contacts().size() ==0){
         app.contact().create(new ContactShortData().withFirstName("Maria").withLastName("Davydenko")
                 .withAddress("Russia, Novosibirsk").withMobileNumber("9998887766").withEmail("masha@gmail.com"));
      }
      if (app.db().groups().size() == 0){
         app.group().create(new GroupData().withName("test6"));
      }
   }

   @Test
   public void testContactAddToGroup() {
      Contacts before = app.db().contacts();
      ContactShortData contact = before.iterator().next();
      Groups beforeGroups = contact.getGroups();
      GroupData group = findGroupForContact(contact);
      app.goTo().HomePage();
      app.contact().removeFromGroup(contact, group);
      Contacts after = app.db().contacts();
      ContactShortData editContact = after.iterator().next();
      Groups afterGroups = editContact.getGroups();
      assertThat(afterGroups, equalTo(beforeGroups.withAdded(group)));
   }

   private GroupData findGroupForContact(ContactShortData contact) {
      int contactGroupsCount = contact.getGroups().size();
      Groups groupsExist = app.db().groups();
      if (contactGroupsCount == 0) {
         GroupData group = groupsExist.iterator().next();
         app.goTo().HomePage();
         app.contact().addToGroup(contact, group);
      }
      return contact.getGroups().iterator().next();
   }
}
