package ru.les.dav.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by saakovamr on 09.04.18.
 */
public class RegistrationTests extends TestBase{

   @Test
   public void testRegistration(){
      app.reqistration().start("user1", "user1@localhost.localhost");
   }
}
