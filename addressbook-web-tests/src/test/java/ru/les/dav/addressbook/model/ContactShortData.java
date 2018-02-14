package ru.les.dav.addressbook.model;

public class ContactShortData {
   private final String firstName;
   private final String lastName;
   private final String address;
   private final String mobileNumber;
   private final String email;
   private final String title;

   public ContactShortData(String firstName, String lastName, String address, String mobileNumber, String email, String title) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = address;
      this.mobileNumber = mobileNumber;
      this.email = email;
      this.title = title;
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
}
