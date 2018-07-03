package com.base.test.utils.test;

import com.snake.inter.model.Group;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by HP on 2016/12/8.
 */
public class HashSetTest {

    public Group createGroup(){
        String name = "group";
        Integer status = 12;
        String model = "Group";
        Group object = new Group();
        object.setName(name);
        object.setStatus(status);
        object.setModel(model);
        return object;
    }

    @Test
    public void addEqualsObjectTest(){
        Set<Group> set = new HashSet<Group>();
        set.add(createGroup());
        Assert.assertEquals(set.size(), 1);
        set.add(createGroup());
        Assert.assertEquals(set.size(), 2);
        set.add(createGroup());
        Assert.assertEquals(set.size(), 3);
        set.add(createGroup());
        Assert.assertEquals(set.size(), 4);
    }

}
