package com.zy.rest;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisTest {

    @Test
    public void testSpringJedisSingle(){//测试jedis
        //加载配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jedis.xml");
        //获得连接池对象
        JedisPool pool = (JedisPool) context.getBean("redisClient");
        //从连接池中获得jedis对象
        Jedis jedis = pool.getResource();
        //操作
        String a = jedis.get("b");
        System.out.println(a);


        jedis.hset("hkey1","key1","value1");
        jedis.hset("hkey1","key2","value2");
        jedis.hset("hkey2","key1","value1");
        jedis.hset("hkey2","key2","value2");

        jedis.close();
        pool.close();

    }

    @Test
    public void test2(){
        //加载配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jedis.xml");
        //获得连接池对象
        JedisPool pool = (JedisPool) context.getBean("redisClient");
        //从连接池中获得jedis对象
        Jedis jedis = pool.getResource();

        jedis.hset("outter","inner1","v1");
        jedis.hset("outter","inner2","v2");
        jedis.hset("outter","inner3","v3");

        System.out.println(jedis.hget("outter","inner1"));

    }


}
