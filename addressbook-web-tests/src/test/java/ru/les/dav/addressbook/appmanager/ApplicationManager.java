package ru.les.dav.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

/**
 * Created by saakovamr on 14.02.18.
 */
public class ApplicationManager {
   WebDriver wd;
   private SessionHelper sessionHelper;
   private ContactHelper contactHelper;
   private NavigationHelper navigationHelper;
   private GroupHelper groupHelper;
   private String browser;

   public ApplicationManager(String browser) {
      this.browser = browser;
   }

   public void init() {
      if (browser.equals(BrowserType.FIREFOX)){
         //wd = new FirefoxDriver();
         wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary("/home/saakovamr/Загрузки/firefox/firefox"));
      } else if (browser.equals(BrowserType.CHROME)) {
         wd = new ChromeDriver();
      } else if (browser.equals(BrowserType.IE)) {
         wd = new InternetExplorerDriver();
      }

      wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
