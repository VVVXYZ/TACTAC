package com.trio.vmalogo.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Author: QiKai
 * Create Time: 14-2-2 下午1:49
 * Version: 1.0
 */
public class RandomStringGenerator {
    private int length;
    private String alphabet62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private String alphabet16 = "abcdef0123456789";
    private final Random rn = new Random();
//    private static int instanceNo = 1;
//    private static int instanceCount = 2;

//    private static RandomStringGenerator instance = null;
    private static Map<Integer, RandomStringGenerator> instanceMap = new HashMap<Integer, RandomStringGenerator>();

    private RandomStringGenerator() {
    }

    public static RandomStringGenerator getInstance(int length) {
        RandomStringGenerator randomStringGenerator = instanceMap.get(length);
        if( randomStringGenerator == null ){
            randomStringGenerator = new RandomStringGenerator(length);
            instanceMap.put(length, randomStringGenerator);
        }
        return randomStringGenerator;

//        if (instance == null)
//            instance = new RandomStringGenerator(length);
//        return instance;
    }

    private RandomStringGenerator(int length) {
        if (length <= 0)
            throw new IllegalArgumentException("Length cannot be less than or equal to 0");
        this.length = length;
    }

    public String getRandomString62() {
        StringBuilder sb = new StringBuilder(this.length);

        for (int i = 0; i < this.length; i++) {
            sb.append(alphabet62.charAt(rn.nextInt(alphabet62.length())));
        }

        return sb.toString();
    }

    public String getRandomString16() {
        StringBuilder sb = new StringBuilder(this.length);

        for (int i = 0; i < this.length; i++) {
            sb.append(alphabet16.charAt(rn.nextInt(alphabet16.length())));
        }

        return sb.toString();
    }
}