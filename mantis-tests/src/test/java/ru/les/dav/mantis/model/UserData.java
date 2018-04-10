package ru.les.dav.mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by saakovamr on 10.04.18.
 */

@Entity
@Table(name = "mantis_user_table")
public class UserData {
   @Id
   @Column(name = "id")
   public int id;

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      UserData userData = (UserData) o;

      return username != null ? username.equals(userData.username) : userData.username == null;
   }

   @Override
   public int hashCode() {
      return username != null ? username.hashCode() : 0;
   }

   @Column(name = "username")

   public String username;
   @Transient
   public String password;
   @Transient
   public String email;

   @Override
   public String toString() {
      return "UserData{" +
              "id=" + id +
              ", username='" + username + '\'' +
              ", password='" + password + '\'' +
              ", email='" + email + '\'' +
              '}';
   }

   public String getUsername() {
      return username;
   }

   public int getId() {
      return id;

   }

   public UserData withId(int id) {
      this.id = id;
      return this;
   }

   public UserData withUsername(String username) {

      this.username = username;
      return this;
   }

   public String getPassword() {
      return password;
   }

   public UserData withPassword(String password) {
      this.password = password;
      return this;
   }

   public String getEmail() {
      return email;
   }

   public UserData withEmail(String email) {
      this.email = email;
      return this;
   }
}
