package ru.les.dav.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by saakovamr on 14.02.18.
 */
public class NavigationHelper extends BaseHelper {

   public NavigationHelper(WebDriver wd) {
      super(wd);
   }

   public void GroupPage() {
      if (isElementPresent(By.tagName("h1"))
              && wd.findElement(By.tagName("h1")).getText().equals("Groups")
              && isElementPresent(By.name("new"))){
         return;
      } else {
         click(By.linkText("groups"));
      }
   }

   public void HomePage() {
      if (isElementPresent(By.id("maintable"))){
         return;
      } else {
         click(By.linkText("home"));
      }
   }
}
