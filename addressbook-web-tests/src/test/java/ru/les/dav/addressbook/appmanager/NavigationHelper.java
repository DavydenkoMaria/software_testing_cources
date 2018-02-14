package ru.les.dav.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by saakovamr on 14.02.18.
 */
public class NavigationHelper extends BaseHelper {

   public NavigationHelper(FirefoxDriver wd) {
      super(wd);
   }

   public void gotoGroupPage() {
      click(By.linkText("groups"));
   }

   public void gotoHomePage() {
      click(By.linkText("home"));
   }
}
