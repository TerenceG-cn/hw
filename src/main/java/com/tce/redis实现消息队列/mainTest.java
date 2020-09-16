package com.tce.redis实现消息队列;

import java.io.IOException;

public class mainTest {
    public static void main(String[] args){

        Message msg=new Message("hello!", "this is the first message!");

        Producer producer=new Producer();
        Consumer consumer=new Consumer();
        try {
            producer.provide("chn1",msg);
            consumer.consum("chn1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
