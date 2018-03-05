package ru.les.dav.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.les.dav.addressbook.model.ContactShortData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saakovamr on 14.02.18.
 */
public class ContactHelper extends BaseHelper {

   public ContactHelper(WebDriver wd) {
      super(wd);
   }

   public void submitContactCreation() {
      click(By.xpath("//div[@id='content']/form/input[21]"));
   }

   public void fillContactForm(ContactShortData contactShortData, boolean creation) {
      type(By.name("firstname"),contactShortData.getFirstName());
      type(By.name("lastname"),contactShortData.getLastName());
      type(By.name("address"),contactShortData.getAddress());
      type(By.name("mobile"),contactShortData.getMobileNumber());
      type(By.name("email"),contactShortData.getEmail());
      type(By.name("title"),contactShortData.getTitle());

      if (creation){
         if (contactShortData.getGroup() != null){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactShortData.getGroup());
         }
      } else {
         Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
   }

   public void initContactCreation() {
      click(By.linkText("add new"));
   }

   public void initContactModification() {
      click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
   }

   public void submitContactModification() {
      click(By.xpath("//div[@id='content']/form[1]/input[22]"));
   }

   public void selectContact() {
      //click(By.id("2"));
      click(By.name("selected[]"));
   }

   public void deleteContactDeletion() {
      click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
   }

   public void acceptContactDeletion() {
      wd.switchTo().alert().accept();
   }

   public void createContact(ContactShortData contactData) {
      initContactCreation();
      fillContactForm(contactData, true);
      submitContactCreation();
   }

   public boolean isThereAContact() {
      return isElementPresent(By.name("selected[]"));
   }

   public List<ContactShortData> getContactList() {
      List<ContactShortData> contacts = new ArrayList<ContactShortData>();
      List<WebElement> elements = wd.findElements(By.name("entry"));
      for (WebElement element : elements){
         String lastName = element.findElements(By.tagName("td")).get(1).getText();
         String firstName = element.findElements(By.tagName("td")).get(2).getText();
         ContactShortData contact = new ContactShortData(firstName, lastName, null, null, null, null, null);
         contacts.add(contact);
      }
      return contacts;
   }
}
