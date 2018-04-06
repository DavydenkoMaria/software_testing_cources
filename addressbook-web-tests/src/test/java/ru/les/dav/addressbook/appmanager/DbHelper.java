package ru.les.dav.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.les.dav.addressbook.model.ContactShortData;
import ru.les.dav.addressbook.model.Contacts;
import ru.les.dav.addressbook.model.GroupData;
import ru.les.dav.addressbook.model.Groups;

import java.util.List;

/**
 * Created by saakovamr on 06.04.18.
 */
public class DbHelper {

   private final SessionFactory sessionFactory;

   public DbHelper(){
      final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
              .configure()
              .build();
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
   }

   public Groups groups(){
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<GroupData> result = session.createQuery( "from GroupData" ).list();
      session.getTransaction().commit();
      session.close();
      return new Groups(result);
   }

   public Contacts contacts(){
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<ContactShortData> result = session.createQuery( "from ContactShortData where deprecated = '0000-00-00'" ).list();
      session.getTransaction().commit();
      session.close();
      //System.out.println(result);
      //System.out.println(new Contacts(result));
      return new Contacts(result);
   }
}
