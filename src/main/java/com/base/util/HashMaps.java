package com.base.util;

import java.util.HashMap;
import java.util.Map;

public class HashMaps<K,V> extends HashMap<K,V> implements Map<K,V>{

    public static <K,V> HashMaps<K,V> build(Class<K> cl, Class<V> cs){
        return new HashMaps<K, V>();
    }

    public HashMaps<K,V> add(K key,V value){
        super.put(key,value);
        return this;
    }

}
