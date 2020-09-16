package com.tce.redis实现消息队列;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class JedisUtil {

    /**
     * Jedis connection pool
     * @Title: config
     */
    public static JedisPool getJedisPool(){
        ResourceBundle bundle=ResourceBundle.getBundle("redis");
        String host=bundle.getString("host");
        int port=Integer.valueOf(bundle.getString("port"));
        int timeout=Integer.valueOf(bundle.getString("timeout"));
        //  String password=bundle.getString("password");

        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxTotal(Integer.valueOf(bundle.getString("maxActive")));
        config.setMaxWaitMillis(Integer.valueOf(bundle.getString("maxWait")));
        config.setTestOnBorrow(Boolean.valueOf(bundle.getString("testOnBorrow")));
        config.setTestOnReturn(Boolean.valueOf(bundle.getString("testOnReturn")));

        JedisPool pool=new JedisPool(config, host, port, timeout);

        return pool;
    }
}