package com.base.utils;

import java.util.Collection;

/**
 * Created by wenxy on 2017/1/11.
 */
public class ArrayTools {

    public static <T> boolean contains(Collection<T> collection, T object) {
        boolean result = null == object || (null != collection && collection.contains(object));
        if (null != collection && collection.size() > 0) {
            boolean contains = false;
            for (T each : collection) {
                if (each.equals(object)) {
                    contains = true;
                    break;
                } else {
                    continue;
                }
            }
            result = result || contains;
        }
        return result;
    }

    public static <T> boolean containsAll(Collection<T> parent, Collection<T> children) {
        boolean result = parent == children || null == children || (null != parent && parent.containsAll(children));
        if (null != parent && parent.size() > 0) {
            boolean containsAll = true;
            for (T each : children) {
                containsAll = containsAll && contains(parent, each);
            }
            result = result || containsAll;
        }
        return result;
    }

}
