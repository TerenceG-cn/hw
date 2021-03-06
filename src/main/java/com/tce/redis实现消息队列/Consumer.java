package com.tce.redis实现消息队列;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

import java.io.IOException;

public class Consumer {

    private JedisClientPool jedis;

    public Consumer(JedisClientPool jedis){
        this.jedis=jedis;
    }


    public void consum(String channel) throws IOException {
        JedisPubSub jedisPubSub = new JedisPubSub() {
            // 取得订阅的消息后的处理
            public void onMessage(String channel, String message) {
                System.out.println("onMessage");
                System.out.println("Channel:"+channel);
                System.out.println("Message:"+message);
            }

            // 初始化订阅时候的处理
            public void onSubscribe(String channel, int subscribedChannels) {
                System.out.println("onSubscribe:"+channel);
            }

            // 取消订阅时候的处理
            public void onUnsubscribe(String channel, int subscribedChannels) {
                System.out.println("onUnsubscribe:"+channel);
            }

            // 初始化按表达式的方式订阅时候的处理
            public void onPSubscribe(String pattern, int subscribedChannels) {
                System.out.println(pattern + "=" + subscribedChannels);
            }

            // 取消按表达式的方式订阅时候的处理
            public void onPUnsubscribe(String pattern, int subscribedChannels) {
                System.out.println(pattern + "=" + subscribedChannels);
            }

            // 取得按表达式的方式订阅的消息后的处理
            public void onPMessage(String pattern, String channel, String message) {
                System.out.println(pattern + "=" + channel + "=" + message);
            }
        };
        jedis.subscribe(jedisPubSub, channel);
        System.out.println("订阅处理完成！");
    }

}