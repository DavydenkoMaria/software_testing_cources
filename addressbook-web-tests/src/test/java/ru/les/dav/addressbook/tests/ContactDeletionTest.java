package ru.les.dav.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by saakovamr on 14.02.18.
 */
public class ContactDeletionTest extends TestBase {

   @Test
   public void testContactDeletion(){
      app.getNavigationHelper().gotoHomePage();
      app.getContactHelper().selectContact();
      app.getContactHelper().deleteContactDeletion();
      app.getContactHelper().acceptContactDeletion();
   }
}
