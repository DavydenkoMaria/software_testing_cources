package ru.les.dav.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.les.dav.addressbook.appmanager.ApplicationManager;

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

}
