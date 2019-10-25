package com.zy.rest.redis.dao.impl;

import com.zy.rest.redis.dao.JedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisDaoImpl implements JedisDao {

    @Autowired
    JedisPool pool;

    @Override
    public String get(String key) {
        Jedis jedis = pool.getResource();
        String value = jedis.get(key);
        jedis.close();
        return value;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = pool.getResource();
        String set = jedis.set(key, value);
        jedis.close();
        return set;
    }

    @Override
    public String hget(String hkey, String key) {
        Jedis jedis = pool.getResource();
        String hget = jedis.hget(hkey, key);
        jedis.close();
        return hget;
    }

    @Override
    public long hset(String hkey, String key, String value) {
        Jedis jedis = pool.getResource();
        Long hset = jedis.hset(hkey, key, value);
        jedis.close();
        return hset;
    }

    @Override
    public long incr(String key) {
        Jedis jedis = pool.getResource();
        Long incr = jedis.incr(key);
        jedis.close();
        return incr;
    }

    @Override
    public long expire(String key, int second) {
        Jedis jedis = pool.getResource();
        Long expire = jedis.expire(key, second);
        jedis.close();
        return expire;
    }

    @Override
    public long ttl(String key) {
        Jedis jedis = pool.getResource();
        Long ttl = jedis.ttl(key);
        jedis.close();
        return ttl;
    }

    @Override
    public long del(String key) {
        Jedis jedis = pool.getResource();
        Long del = jedis.del(key);
        jedis.close();
        return del;
    }

    @Override
    public long hdel(String hkey, String key) {
        Jedis jedis = pool.getResource();
        Long hdel = jedis.hdel(hkey, key);
        jedis.close();
        return hdel;
    }
}
