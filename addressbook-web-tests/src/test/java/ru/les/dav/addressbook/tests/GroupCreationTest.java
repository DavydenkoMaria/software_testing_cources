package ru.les.dav.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().GroupPage();
        List<GroupData> before = app.group().list();
        GroupData group = new GroupData().withName("test2").withFooter("test3").withFooter("test5");
        app.group().create(group);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() +1);
        //int max = after.stream().max((groupData, t1) -> Integer.compare(groupData.getId(), t1.getId())).get().getId();
        //group.setId(max);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }
}
