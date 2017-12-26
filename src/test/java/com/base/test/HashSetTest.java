package com.base.test;

import com.snake.system.model.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by HP on 2016/12/8.
 */
public class HashSetTest {

    public User createGroup(){
        String name = "group";
        Integer status = 12;
        String model = "Group";
        User object = new User();
        object.setUserName(name);
        object.setStatus(status);
        object.setLoginName(model);
        return object;
    }

    @Test
    public void addEqualsObjectTest(){
        Set<User> set = new HashSet<User>();
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
