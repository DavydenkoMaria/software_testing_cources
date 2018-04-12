package ru.les.dav.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.les.dav.mantis.model.MailMessage;
import ru.les.dav.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by saakovamr on 10.04.18.
 */
public class ChangePasswordTest extends TestBase{
   @BeforeMethod
   public void startMailServer() {
      app.mail().start();
   }

   @Test
   public void testPasswordChange() throws IOException, MessagingException, InterruptedException {
      String passwordNew = "passwordNewest";
      UserData admin = new UserData().withUsername("administrator").withPassword("root");
      //UserData user = new UserData().withUsername("user10").withEmail("user10@localhost.localhost");
      //app.changePassword().start(admin, user);
      UserData user = app.changePassword().start(admin);
      List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
      String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
      app.changePassword().finish(confirmationLink, passwordNew);
      //assertTrue(app.newSession().login(user.getUsername(), passwordNew));
      assertTrue(app.newSession().login(user.getUsername(), passwordNew));
   }

   private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
      MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
      VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
      return regex.getText(mailMessage.text);
   }

   @AfterMethod(alwaysRun = true)
   public void stopMailServer(){
      app.mail().stop();
   }
}
