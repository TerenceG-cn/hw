package com.tce.redis实现消息队列;

import java.io.IOException;

public class mainTest {
    private static JedisClientPool jedisClientPool=new JedisClientPool();
    private static String msg="this is the first message!";

    public static class pThread implements Runnable{
        @Override
        public void run(){
            Producer producer=new Producer(jedisClientPool);
            while(true) {
                try {
                    producer.provide("chn1", msg);
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
        }
    }
    public static class cThread implements Runnable{
        @Override
        public void run(){
            Consumer consumer=new Consumer(jedisClientPool);
            while(true) {
                try {
                    consumer.consum("chn1");
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
        }
    }

    public static void main(String[] args){

        //Message msg=new Message("hello!", "this is the first message!");
        new Thread(new pThread()).start();
        new Thread(new cThread()).start();
    }
}
