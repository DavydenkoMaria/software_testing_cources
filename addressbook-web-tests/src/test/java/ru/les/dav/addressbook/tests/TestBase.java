package ru.les.dav.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.les.dav.addressbook.appmanager.ApplicationManager;
import ru.les.dav.addressbook.model.ContactShortData;
import ru.les.dav.addressbook.model.Contacts;
import ru.les.dav.addressbook.model.GroupData;
import ru.les.dav.addressbook.model.Groups;

import javax.persistence.metamodel.MapAttribute;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;

/**
 * Created by saakovamr on 13.02.18.
 */
public class TestBase {

   protected static final ApplicationManager app
           = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
   //BrowserType.FIREFOX);

   @BeforeSuite
   public void setUp() throws Exception {
      app.init();
   }

   @AfterSuite
   public void tearDown() {
      app.stop();
   }

   public void verifyGroupListInUI() {
      if (Boolean.getBoolean("verifyUI")) {
         Groups dbGroups = app.db().groups();
         Groups uiGroups = app.group().all();
         assertThat(uiGroups, equalTo(dbGroups.stream()
                 .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                 .collect(Collectors.toSet())));
      }
   }

   public void verifyContactListInUI() {
      if (Boolean.getBoolean("verifyUI")) {
         Contacts dbContacts = app.db().contacts();
         Contacts uiContacts = app.contact().all();
         assertThat(uiContacts, equalTo(dbContacts.stream()
                 .map((g) -> new ContactShortData().withId(g.getId()).withFirstName(g.getFirstName())
                         .withLastName(g.getLastName()).withAddress(g.getAddress()))
                 .collect(Collectors.toSet())));
      }
   }
}
