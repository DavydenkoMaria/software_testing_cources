package ru.les.dav.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.les.dav.addressbook.model.ContactShortData;
import ru.les.dav.addressbook.model.Contacts;

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


   public void initContactModificationById(int id) {
      String link = ("a[href='edit.php?id=" + id +"']");
      WebElement first = wd.findElement(By.cssSelector(link));
      WebElement second = first.findElement(By.tagName("img"));
      second.click();
   }

   public void submitContactModification() {
      click(By.xpath("//div[@id='content']/form[1]/input[22]"));
   }


   public void selectContactById(int id) {
      wd.findElement(By.cssSelector("input[value='" + id +"']")).click();
   }

   public void deleteContactDeletion() {
      click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
   }

   public void acceptContactDeletion() {
      wd.switchTo().alert().accept();
   }

   public void create(ContactShortData contactData) {
      initContactCreation();
      fillContactForm(contactData, true);
      contsactCache = null;
      submitContactCreation();
   }

   public void modify(ContactShortData contact) {
      initContactModificationById(contact.getId());
      fillContactForm(contact, false);
      contsactCache = null;
      submitContactModification();
   }


   public void delete(ContactShortData contact) {
      selectContactById(contact.getId());
      deleteContactDeletion();
      contsactCache = null;
      acceptContactDeletion();
   }

   public boolean isThereAContact() {
      return isElementPresent(By.name("selected[]"));
   }

   private Contacts contsactCache = null;
   public Contacts all() {
      if (contsactCache!= null){
         return new Contacts(contsactCache);
      }
      contsactCache = new Contacts();
      List<WebElement> elements = wd.findElements(By.name("entry"));
      for (WebElement element : elements){
         String lastName = element.findElements(By.tagName("td")).get(1).getText();
         String firstName = element.findElements(By.tagName("td")).get(2).getText();
         int id = Integer.parseInt(element.findElements(By.tagName("td")).get(0).findElement(By.name("selected[]")).getAttribute("value"));
         contsactCache.add(new ContactShortData().withId(id).withFirstName(firstName).withLastName(lastName));
      }
      return new Contacts(contsactCache);
   }
}
