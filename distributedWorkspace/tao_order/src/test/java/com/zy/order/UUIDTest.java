package com.zy.order;

import org.junit.Test;

import java.util.HashSet;
import java.util.UUID;

public class UUIDTest {

    @Test
    public void test(){
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < 1000; i++) {
            set.add(UUID.randomUUID().toString().substring(0,4));
        }
        System.out.println(set.size());
    }

}
