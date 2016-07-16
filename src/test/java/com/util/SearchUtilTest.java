package com.util;

import java.util.*;

/**
 * @author loser
 * @ClassName SearchUtilTest
 * @Description
 * @Date 2016/02/24 16:17
 * @History :
 * <author>
 * <time>
 * <desc>
 */

public class SearchUtilTest {
    @org.junit.Test
    public void test(){
        int[] array = new int[3];
        LinkedList linkedList = new LinkedList();
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        Deque deque = new ArrayDeque();
        HashSet hashSet = new HashSet();

        System.out.println(array.getClass().isArray());
        System.out.println(Collection.class.isAssignableFrom(linkedList.getClass()));
        System.out.println(Collection.class.isAssignableFrom(arrayList.getClass()));
        System.out.println(Collection.class.isAssignableFrom(hashMap.getClass()));
        System.out.println(Collection.class.isAssignableFrom(deque.getClass()));
        System.out.println(Collection.class.isAssignableFrom(hashSet.getClass()));
    }
}
