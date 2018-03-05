package ru.les.dav.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.les.dav.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {
    @BeforeMethod
    public void ensurePreConditions(){
        app.goTo().GroupPage();
        if (app.group().list().size() == 0){
            app.group().create(new GroupData("test1", "test2", "test3"));
        }
    }

    @Test
    public void testGroupDeletion() {
        List<GroupData> before = app.group().list();
        int index = before.size() -1;
        app.group().delete(index);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), index);
        before.remove(index);
        Assert.assertEquals(before, after);
    }


}
