package ru.les.dav.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.security.acl.Group;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by saakovamr on 30.03.18.
 */
public class Groups extends ForwardingSet<GroupData>{

   private Set<GroupData> delegate;

   public Groups(Groups groups) {
      this.delegate = new HashSet<GroupData>(groups.delegate);
   }

   public Groups() {
      this.delegate = new HashSet<>();
   }

   @Override
   protected Set<GroupData> delegate() {
      return delegate;
   }

   public Groups withAdded(GroupData group){
      Groups groups = new Groups(this);
      groups.add(group);
      return groups;
   }

   public Groups withOut(GroupData group){
      Groups groups = new Groups(this);
      groups.remove(group);
      return groups;
   }
}
