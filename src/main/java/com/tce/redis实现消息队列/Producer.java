package com.tce.redis实现消息队列;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;

public class Producer {

    private JedisClientPool jedis;

    public Producer(JedisClientPool jedis){
        this.jedis=jedis;
    }


    public void provide(String channel,String message) throws IOException {

        String str1=channel;
        String str2=message;
        jedis.publish(str1, str2);
        System.out.println("消息发布成功！");
    }

}
