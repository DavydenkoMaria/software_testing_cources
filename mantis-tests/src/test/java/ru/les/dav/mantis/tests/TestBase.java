package ru.les.dav.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.les.dav.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;


/**
 * Created by saakovamr on 13.02.18.
 */
public class TestBase {

   protected static final ApplicationManager app
           = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

   @BeforeSuite
   public void setUp() throws Exception {
      app.init();
      app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.back");
   }

   @AfterSuite
   public void tearDown() throws IOException {
      app.ftp().restore("config_inc.php.back", "config_inc.php");
      app.stop();
   }

   public boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
         return app.soap().getIssueWithId(issueId);
   }

   public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
      if (isIssueOpen(issueId)) {
         throw new SkipException("Ignored because of issue " + issueId);
      }
   }

}
