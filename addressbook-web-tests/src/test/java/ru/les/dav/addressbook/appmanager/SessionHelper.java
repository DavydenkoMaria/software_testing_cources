package ru.les.dav.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by saakovamr on 14.02.18.
 */
public class SessionHelper extends BaseHelper {

   public SessionHelper(WebDriver wd) {
      super(wd);
   }
   public void login(String username, String password) {
      type(By.name("user"),username);
      type(By.name("pass"),password);
      click(By.xpath("//form[@id='LoginForm']/input[3]"));
   }
}
