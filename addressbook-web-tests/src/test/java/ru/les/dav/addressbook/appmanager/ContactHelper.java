package ru.les.dav.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.les.dav.addressbook.model.ContactShortData;

/**
 * Created by saakovamr on 14.02.18.
 */
public class ContactHelper extends BaseHelper {

   public ContactHelper(FirefoxDriver wd) {
      super(wd);
   }

   public void submitContactCreation() {
      click(By.xpath("//div[@id='content']/form/input[21]"));
   }

   public void fillContactForm(ContactShortData contactShortData) {
      type(By.name("firstname"),contactShortData.getFirstName());
      type(By.name("lastname"),contactShortData.getLastName());
      type(By.name("address"),contactShortData.getAddress());
      type(By.name("mobile"),contactShortData.getMobileNumber());
      type(By.name("email"),contactShortData.getEmail());
      type(By.name("title"),contactShortData.getTitle());
   }

   public void initContactCreation() {
      click(By.linkText("add new"));
   }
}
