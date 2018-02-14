package ru.les.dav.addressbook.appmanager;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

/**
 * Created by saakovamr on 14.02.18.
 */
public class ApplicationManager {
   FirefoxDriver wd;
   private SessionHelper sessionHelper;
   private ContactHelper contactHelper;
   private NavigationHelper navigationHelper;
   private GroupHelper groupHelper;

   public static boolean isAlertPresent(FirefoxDriver wd) {
       try {
           wd.switchTo().alert();
           return true;
       } catch (NoAlertPresentException e) {
           return false;
       }
   }

   public void init() {
      //wd = new FirefoxDriver();
      wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary("/home/saakovamr/Загрузки/firefox/firefox"));
      wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
      wd.get("http://localhost/addressbook/index.php");
      groupHelper = new GroupHelper(wd);
      navigationHelper = new NavigationHelper(wd);
      contactHelper = new ContactHelper(wd);
      sessionHelper = new SessionHelper(wd);
      sessionHelper.login("admin", "secret");
   }

   public void stop() {
      wd.quit();
   }

   public GroupHelper getGroupHelper() {
      return groupHelper;
   }

   public NavigationHelper getNavigationHelper() {
      return navigationHelper;
   }

   public ContactHelper getContactHelper() {
      return contactHelper;
   }
}
