package com.google.collections;

import com.google.Student;
import com.google.common.cache.*;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by HP on 2017/3/10.
 */
public class CacheTests {

    @Test
    public void Test() throws ExecutionException, InterruptedException {
        LoadingCache<Integer, Student> studentCache = CacheBuilder.newBuilder()
                .concurrencyLevel(8)
                .expireAfterWrite(1, TimeUnit.HOURS)
                .initialCapacity(1000)
                .maximumSize(100)
                .recordStats()
                .removalListener(new RemovalListener<Object, Object>() {
                    public void onRemoval(RemovalNotification<Object, Object> notification) {
                        System.out.println("remove key : " + notification.getKey());
                    }
                })
                .build(new CacheLoader<Integer, Student>() {
                    @Override
                    public Student load(Integer key) throws Exception {
                        System.out.println("load student " + key);
                        Student student = new Student();
                        student.setId(key);
                        student.setName("name" + key);
                        return student;
                    }
                });

        for (int i = 0; i < 20; i++) {
            studentCache.put(i, new Student(1, "student" + i));
        }

        for (int i = 0; i < 20; i++) {
            Student student = studentCache.get(i);
            System.out.println(student);
            TimeUnit.SECONDS.sleep(1);
            System.out.println("cache stats : " + studentCache.stats().toString());
        }
    }

}
