package ru.les.dav.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by saakovamr on 30.03.18.
 */
public class Contacts extends ForwardingSet<ContactShortData> {

   private Set<ContactShortData> delegate;

   public Contacts(Contacts contacts) {
      this.delegate = new HashSet<ContactShortData>(contacts.delegate);
   }
   public Contacts() {
      this.delegate = new HashSet<>();
   }

   public Contacts(Collection<ContactShortData> contacts) {
      this.delegate = new HashSet<ContactShortData>(contacts);
   }

   @Override
   protected Set<ContactShortData> delegate() {
      return delegate;
   }

   public Contacts withAdded(ContactShortData contact){
      Contacts contacts = new Contacts(this);
      contacts.add(contact);
      return contacts;
   }
   public Contacts withOut(ContactShortData contact){
      Contacts contacts = new Contacts(this);
      contacts.remove(contact);
      return contacts;
   }
}
