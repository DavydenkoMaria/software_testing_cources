package ru.les.dav.mantis.appmanager;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import com.thoughtworks.xstream.core.util.SelfStreamingInstanceChecker;
import org.openqa.selenium.By;
import ru.les.dav.mantis.model.UserData;

/**
 * Created by saakovamr on 10.04.18.
 */
public class ChangePasswordHelper extends BaseHelper{
   public ChangePasswordHelper(ApplicationManager app) {
      super(app);
   }

   public void start(UserData admin, UserData user) {
      wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
      type(By.name("username"), admin.getUsername());
      type(By.name("password"), admin.getPassword());
      click(By.cssSelector("input[value='Login'"));
      UserData user1 = app.dbGet().findUserByName(user);
      String link = "/manage_user_edit_page.php?user_id=" + user1.getId();
      wd.get(app.getProperty("web.baseUrl") + link);
      click(By.cssSelector("input[value='Reset Password'"));
   }
   public UserData start(UserData admin) {
      wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
      type(By.name("username"), admin.getUsername());
      type(By.name("password"), admin.getPassword());
      click(By.cssSelector("input[value='Login'"));
      UserData user1 = app.dbGet().chooseUser();
      String link = "/manage_user_edit_page.php?user_id=" + user1.getId();
      wd.get(app.getProperty("web.baseUrl") + link);
      click(By.cssSelector("input[value='Reset Password'"));
      return user1;
   }

   public void finish(String confirmationLink, String password) {
      wd.get(confirmationLink);
      type(By.name("password"), password);
      type(By.name("password_confirm"), password);
      click(By.cssSelector("input[value='Update User'"));
   }

}
