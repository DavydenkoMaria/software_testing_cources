package ru.les.dav.mantis.model;

/**
 * Created by saakovamr on 10.04.18.
 */
public class Project {
   private int id;
   private String name;

   public int getId() {
      return id;
   }

   public Project withId(int id) {
      this.id = id;
      return this;
   }

   public String getName() {
      return name;
   }

   public Project withName(String name) {
      this.name = name;
      return this;
   }
}
