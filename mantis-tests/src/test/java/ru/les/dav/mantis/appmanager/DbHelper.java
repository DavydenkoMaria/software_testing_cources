package ru.les.dav.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.les.dav.mantis.model.UserData;

import java.util.List;
import java.util.Objects;

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

   public UserData findUserByName(UserData user){
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<UserData> result = session.createQuery( "from UserData").list();
      session.getTransaction().commit();
      session.close();
      for (UserData userFind : result){
         if (Objects.equals(userFind, user)){
            return userFind;
         }
      }
      return null;
   }
}
