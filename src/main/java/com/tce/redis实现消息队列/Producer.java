package com.tce.redis实现消息队列;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;

public class Producer {

    private JedisClientPool jedis;

    public Producer(){

    }


    public void provide(String channel,Message message) throws IOException {
        String str1=MessageUtil.convertToString(channel,"UTF-8");
        String str2=MessageUtil.convertToString(message,"UTF-8");
        jedis.publish(str1, str2);
    }

}
