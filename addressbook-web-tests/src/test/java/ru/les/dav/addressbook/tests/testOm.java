package ru.les.dav.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.ContactShortData;

import java.util.List;

/**
 * Created by saakovamr on 06.04.18.
 */
public class testOm {
   private SessionFactory sessionFactory;
   @BeforeClass
   protected void setUp() throws Exception {
      // A SessionFactory is set up once for an application!
      final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
              .configure() // configures settings from hibernate.cfg.xml
              .build();
      try {
         sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
      }
      catch (Exception e) {
         e.printStackTrace();
         // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
         // so destroy it manually.
         StandardServiceRegistryBuilder.destroy( registry );
      }
   }

   @Test
   public void testOmCon(){
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<ContactShortData> result = session.createQuery( "from ContactShortData where deprecated = '0000-00-00'" ).list();
      for ( ContactShortData contact : result ) {
         System.out.println(contact);
         System.out.println(contact.getGroups());
      }
      session.getTransaction().commit();
      session.close();
   }
}
