package com.tce.redis实现消息队列;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

public class JedisClientPool implements JedisClient {
    private JedisPool jedisPool;

    public JedisClientPool(){
        jedisPool=new JedisPool();
    }



    @Override
    public String set(String key, String value) {
        Jedis jedis=jedisPool.getResource();
        String set=jedis.set(key,value);
        jedis.close();
        return set;
    }

    @Override
    public String get(String key) {
        Jedis jedis=jedisPool.getResource();
        String result=jedis.get(key);
        jedis.close();
        return result;
    }

    @Override
    public Boolean exists(String key) {
        Jedis jedis = jedisPool.getResource();
        Boolean result = jedis.exists(key);
        jedis.close();
        return result;
    }

    @Override
    public Long expire(String key, int seconds) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.expire(key, seconds);
        jedis.close();
        return result;
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.ttl(key);
        jedis.close();
        return result;
    }

    @Override
    public Long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }

    @Override
    public Long hset(String key, String field, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hset(key, field, value);
        jedis.close();
        return result;
    }

    @Override
    public String hget(String key, String field) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.hget(key, field);
        jedis.close();
        return result;
    }

    @Override
    public Long del(String key, String... field) {//String...代表String类型的可变长度的数组
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hdel(key, field);
        jedis.close();
        return result;
    }

    @Override
    public Long publish(String channel, String message){
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.publish(channel, message);
        jedis.close();
        return result;
    }

    public void subscribe(JedisPubSub var1, String... var2) {
        Jedis jedis = jedisPool.getResource();
        jedis.subscribe(var1, var2);
        jedis.close();
    }
}
