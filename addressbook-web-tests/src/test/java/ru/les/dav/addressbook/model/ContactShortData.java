package ru.les.dav.addressbook.model;

public class ContactShortData {
   private  String firstName;
   private  String lastName;
   private  String address;
   private  String mobileNumber;
   private  String email;
   private  String title;
   private String group;
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
