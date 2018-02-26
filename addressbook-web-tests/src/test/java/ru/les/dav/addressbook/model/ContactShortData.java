package ru.les.dav.addressbook.model;

public class ContactShortData {
   private final String firstName;
   private final String lastName;
   private final String address;
   private final String mobileNumber;
   private final String email;
   private final String title;
   private String group;

   public ContactShortData(String firstName, String lastName, String address, String mobileNumber, String email, String title, String group) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = address;
      this.mobileNumber = mobileNumber;
      this.email = email;
      this.title = title;
      this.group = group;
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
