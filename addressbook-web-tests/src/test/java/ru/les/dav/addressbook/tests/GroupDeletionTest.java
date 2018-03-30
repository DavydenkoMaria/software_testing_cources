package ru.les.dav.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.GroupData;

import java.util.Set;

public class GroupDeletionTest extends TestBase {
    @BeforeMethod
    public void ensurePreConditions(){
        app.goTo().GroupPage();
        if (app.group().all().size() == 0){
            app.group().create(new GroupData().withName("test6"));
        }
    }

    @Test
    public void testGroupDeletion() {
        Set<GroupData> before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        int index = before.size() -1;
        app.group().delete(deletedGroup);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), index);
        before.remove(deletedGroup);
        Assert.assertEquals(before, after);
    }


}
