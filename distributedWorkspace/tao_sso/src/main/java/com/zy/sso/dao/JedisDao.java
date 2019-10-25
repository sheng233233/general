package com.zy.sso.dao;

public interface JedisDao {

    public String get(String key);

    public String set(String key, String value) ;

    public String hget(String hkey, String key) ;
    //新增一个值为键值对的键值对 (hkey,(key,value))
    public long hset(String hkey, String key, String value) ;
    //对键自增
    public long incr(String key);
    //设置生存时间
    public long expire(String key, int second);
    //查看剩余生存时间
    public long ttl(String key);

    long del(String key);

    long hdel(String hkey, String key);

}

