package ru.les.dav.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.GroupData;

import java.util.Set;

/**
 * Created by saakovamr on 14.02.18.
 */
public class GroupModificationTests extends TestBase{

   @BeforeMethod
   public void ensurePreConditions(){
      app.goTo().GroupPage();
      if (app.group().all().size() == 0){
         app.group().create(new GroupData().withName("test9"));
      }
   }

   @Test
   public void testGroupModification(){
      Set<GroupData> before = app.group().all();
      GroupData modifiedGroup = before.iterator().next();
      int index = before.size()-1;
      GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test2");
      app.group().modify(group);
      Set<GroupData> after = app.group().all();
      Assert.assertEquals(after.size(), before.size());
      before.remove(modifiedGroup);
      before.add(group);
      /*Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
      before.sort(byId);
      after.sort(byId);*/
      Assert.assertEquals(before, after);
   }
}
