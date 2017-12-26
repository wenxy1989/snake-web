package com.snake;


import java.util.*;

/**
 * Created by wen on 2015/8/10.
 */
public class ArrayTools {


    public static <T> boolean contains(Collection<T> collection, T obj) {
        boolean result = false;
        if(null != collection) {
            for (T s : collection) {
                if (s.equals(obj)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /*public static <T> boolean contains(Collection<T> collection, T value) {
        boolean result = false;
        if (null != collection && collection.size() > 0) {
            Set<T> set = new HashSet<T>();
            for(T obj:collection){
                set.add(obj);
            }
            result = set.contains(value);
        }
        return result;
    }*/

    public static <T> boolean contains(T[] array, T value) {
        boolean result = false;
        if (null != array && array.length > 0) {
            Set<T> set = new HashSet<T>();
            for(T obj:array){
                set.add(obj);
            }
            result = set.contains(value);
        }
        return result;
    }

    public static <T>  List<T> union(Collection<T> array1, Collection<T> array2) {
        List array = null;
        if (null != array1 && array1.size() > 0 && null != array2 && array2.size() > 0) {
            array = new ArrayList();
            for(Object obj1:array1){
                for(Object obj2:array2){
                    if(obj1 == obj2){
                        array.add(obj1);
                    }
                }
            }
        }
        return array;
    }

    public static <T> List<T> union(T[] array1, T[] array2) {
        List<T> array = null;
        if (null != array1 && array1.length > 0 && null != array2 && array2.length > 0) {
            array = new ArrayList();
            for(T obj1:array1){
                for(T obj2:array2){
                    if(obj1 == obj2){
                        array.add(obj1);
                    }
                }
            }
        }
        return array;
    }
}
