package cn.edu.nuaa.launcher.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Meteor on 2018/3/21.
 */

public class ContainerFactory {
    public static<K,V> Map<K, V> newMap() {
        return new HashMap<K,V>();
    }

    public static<T> ArrayList<T> newList() {
        return new ArrayList<T>();
    }
}
