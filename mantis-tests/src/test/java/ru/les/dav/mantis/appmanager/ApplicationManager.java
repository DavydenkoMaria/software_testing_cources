package ru.les.dav.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by saakovamr on 14.02.18.
 */
public class ApplicationManager {
   private final Properties properties;
   private WebDriver wd;
   private String browser;
   private RegistrationHelper reqistrationHelper;

   public ApplicationManager(String browser) {
      this.browser = browser;
      properties = new Properties();
   }

   public void init() throws IOException {
      String target = System.getProperty("target", "local");
      properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
   }

   public void stop() {
      if(wd != null){
         wd.quit();
      }
   }

   public HttpSession newSession(){
      return new HttpSession(this);
   }

   public String getProperty(String key){
      return properties.getProperty(key);
   }

   public RegistrationHelper reqistration() {
      if (reqistrationHelper == null){
         reqistrationHelper = new RegistrationHelper(this);
      }
      return reqistrationHelper;
   }

   public WebDriver getDriver() {
      if(wd == null){
         if (browser.equals(BrowserType.FIREFOX)) {
            //wd = new FirefoxDriver();
            wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary("/home/saakovamr/Загрузки/firefox/firefox"));
         } else if (browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
         } else if (browser.equals(BrowserType.IE)) {
            wd = new InternetExplorerDriver();
         }

         wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
         wd.get(properties.getProperty("web.baseUrl"));
      }
      return wd;
   }
}
