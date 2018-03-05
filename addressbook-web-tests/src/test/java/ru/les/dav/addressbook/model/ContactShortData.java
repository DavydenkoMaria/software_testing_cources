package ru.les.dav.addressbook.model;

public class ContactShortData {
   private final String firstName;
   private final String lastName;
   private final String address;
   private final String mobileNumber;
   private final String email;
   private final String title;
   private String group;
   private int id;

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

   public ContactShortData(int id, String firstName, String lastName, String address, String mobileNumber, String email, String title, String group) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = address;
      this.mobileNumber = mobileNumber;
      this.email = email;
      this.title = title;
      this.group = group;
   }
   public ContactShortData(String firstName, String lastName, String address, String mobileNumber, String email, String title, String group) {
      this.id = Integer.MAX_VALUE;
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

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      ContactShortData that = (ContactShortData) o;

      if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
      return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
   }

   @Override
   public int hashCode() {
      int result = firstName != null ? firstName.hashCode() : 0;
      result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
      return result;
   }

   public String getGroup() {
      return group;
   }
}
