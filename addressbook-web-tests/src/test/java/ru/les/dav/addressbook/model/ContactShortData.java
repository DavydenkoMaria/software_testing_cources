package ru.les.dav.addressbook.model;

import java.io.File;

public class ContactShortData {
   private String firstName;
   private String lastName;
   private String address;
   private String mobileNumber;
   private String email;
   private String email2;
   private String email3;
   private String allEmails;
   private String title;
   private String group;
   private String homePhone;
   private String workPhone;
   private String allPhones;
   private File photo;

   public File getPhoto() {
      return photo;
   }

   public ContactShortData withPhoto(File photo) {
      this.photo = photo;
      return this;
   }

   public String getEmail2() {
      return email2;
   }

   public ContactShortData withEmail2(String email2) {
      this.email2 = email2;
      return this;
   }

   public String getEmail3() {
      return email3;
   }

   public ContactShortData withEmail3(String email3) {
      this.email3 = email3;
      return this;
   }

   public String getAllEmails() {
      return allEmails;
   }

   public ContactShortData withAllEmails(String allEmails) {
      this.allEmails = allEmails;
      return this;
   }

   public String getAllPhones() {
      return allPhones;
   }

   public ContactShortData withAllPhones(String allPhones) {
      this.allPhones = allPhones;
      return this;
   }

   private int id = Integer.MAX_VALUE;

   public ContactShortData withFirstName(String firstName) {
      this.firstName = firstName;
      return this;
   }

   public ContactShortData withLastName(String lastName) {
      this.lastName = lastName;
      return this;
   }

   public ContactShortData withAddress(String address) {
      this.address = address;
      return this;
   }

   public ContactShortData withHomePhone(String homePhone) {
      this.homePhone = homePhone;
      return this;
   }

   public ContactShortData withWorkPhone(String workPhone) {
      this.workPhone = workPhone;
      return this;
   }

   public ContactShortData withMobileNumber(String mobileNumber) {
      this.mobileNumber = mobileNumber;
      return this;
   }

   public ContactShortData withEmail(String email) {
      this.email = email;
      return this;
   }

   public ContactShortData withTitle(String title) {
      this.title = title;
      return this;
   }

   public ContactShortData withGroup(String group) {
      this.group = group;
      return this;
   }

   public ContactShortData withId(int id) {
      this.id = id;
      return this;
   }

   public int getId() {
      return id;
   }

   @Override
   public String toString() {
      return "ContactShortData{" +
              "firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", id=" + id +
              '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      ContactShortData that = (ContactShortData) o;

      if (id != that.id) return false;
      if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
      return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
   }

   @Override
   public int hashCode() {
      int result = firstName != null ? firstName.hashCode() : 0;
      result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
      result = 31 * result + id;
      return result;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public String getAddress() {
      return address;
   }

   public String getMobileNumber() {
      return mobileNumber;
   }

   public String getHomePhone() {
      return homePhone;
   }

   public String getWorkPhone() {
      return workPhone;
   }

   public String getEmail() {
      return email;
   }

   public String getTitle() {
      return title;
   }

   public String getGroup() {
      return group;
   }
}
