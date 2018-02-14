package ru.les.dav.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by saakovamr on 14.02.18.
 */
public class NavigationHelper {
   private FirefoxDriver wd;

   public NavigationHelper(FirefoxDriver wd) {
      this.wd=wd;
   }

   public void gotoGroupPage() {
       wd.findElement(By.linkText("groups")).click();
   }
}
