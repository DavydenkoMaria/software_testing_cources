package ru.les.dav.addressbook.tests;

import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.GroupData;

/**
 * Created by saakovamr on 14.02.18.
 */
public class GroupModificationTests extends TestBase{

   @Test
   public void testGroupModification(){
      app.getNavigationHelper().gotoGroupPage();
      app.getGroupHelper().selectGroup();
      app.getGroupHelper().initGroupModification();
      app.getGroupHelper().fillGroupForm(new GroupData("test2", "test3", "test4"));
      app.getGroupHelper().submitGroupModification();
      app.getGroupHelper().returnToGroupPage();
   }
}
