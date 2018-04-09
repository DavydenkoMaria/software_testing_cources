package ru.les.dav.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.les.dav.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by saakovamr on 09.04.18.
 */
public class LoginTests extends TestBase{

   @Test
   public void testLogin() throws IOException {
      HttpSession session = app.newSession();
      assertTrue(session.login("administrator", "root"));
      assertTrue(session.isLoggedInAs("administrator"));
   }
}
