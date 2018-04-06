package ru.les.dav.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.GroupData;
import ru.les.dav.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by saakovamr on 14.02.18.
 */
public class GroupModificationTests extends TestBase{

   @BeforeMethod
   public void ensurePreConditions(){
      app.goTo().GroupPage();
      if (app.db().groups().size() == 0){
         app.goTo().GroupPage();
         app.group().create(new GroupData().withName("test9"));
      }
   }

   @Test
   public void testGroupModification(){
      Groups before = app.db().groups();
      GroupData modifiedGroup = before.iterator().next();
      GroupData group = new GroupData().withId(modifiedGroup.getId())
              .withName("test2").withHeader("test9").withFooter("test7");
      app.group().modify(group);
      assertThat(app.group().count(), equalTo(before.size()));
      Groups after = app.db().groups();
      assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));
      verifyGroupListInUI();
   }
}
