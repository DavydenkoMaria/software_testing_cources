package ru.les.dav.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.les.dav.addressbook.appmanager.ApplicationManager;

import static org.openqa.selenium.remote.BrowserType.FIREFOX;

/**
 * Created by saakovamr on 13.02.18.
 */
public class TestBase {

   protected final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

   @BeforeMethod
   public void setUp() throws Exception {
      app.init();
   }

   @AfterMethod
   public void tearDown() {
      app.stop();
   }

}
