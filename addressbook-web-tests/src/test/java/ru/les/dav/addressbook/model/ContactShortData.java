package ru.les.dav.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactShortData {
   @XStreamOmitField
   @Id
   @Column(name = "id")
   private int id = Integer.MAX_VALUE;

   @Column(name = "firstname")
   private String firstName;

   @Column(name = "lastname")
   private String lastName;

   @Column(name = "address")
   @Type(type = "text")
   private String address;

   @Column(name = "mobile")
   @Type(type = "text")
   private String mobileNumber;

   @Type(type = "text")
   private String email;

   @Type(type = "text")
   private String email2;

   @Type(type = "text")
   private String email3;

   @Transient
   private String allEmails;

   @Transient
   private String title;

   @Column(name = "home")
   @Type(type = "text")
   private String homePhone;

   @Column(name = "work")
   @Type(type = "text")
   private String workPhone;

   @Transient
   private String allPhones;

   @Column(name = "photo")
   @Type(type = "text")
   private String photo;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "address_in_groups",
           joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
   private Set<GroupData> groups = new HashSet<GroupData>();

   public File getPhoto() {
      if (photo!= null) {
         return new File(photo);
      }
      return null;
   }

   public ContactShortData withPhoto(File photo) {
      this.photo = photo.getPath();
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

   public Groups getGroups() {
      return new Groups(groups);
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
              "id=" + id +
              ", firstName='" + firstName + '\'' +

              ", lastName='" + lastName + '\'' +
              ", address='" + address + '\'' +
              ", mobileNumber='" + mobileNumber + '\'' +
              ", email='" + email + '\'' +
              "groups=" + groups + '\'' +
              '}';
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

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      ContactShortData that = (ContactShortData) o;

      if (id != that.id) return false;
      if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
      if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
      if (address != null ? !address.equals(that.address) : that.address != null) return false;
      if (mobileNumber != null ? !mobileNumber.equals(that.mobileNumber) : that.mobileNumber != null) return false;
      if (email != null ? !email.equals(that.email) : that.email != null) return false;
      return groups != null ? groups.equals(that.groups) : that.groups == null;
   }

   @Override
   public int hashCode() {
      int result = id;
      result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
      result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
      result = 31 * result + (address != null ? address.hashCode() : 0);
      result = 31 * result + (mobileNumber != null ? mobileNumber.hashCode() : 0);
      result = 31 * result + (email != null ? email.hashCode() : 0);
      return result;
   }


   public ContactShortData inGroup(GroupData group) {
      groups.add(group);

      return this;
   }

   public ContactShortData withGroup(GroupData group) {
      this.groups.add(group);
      return this;
   }
}
