package com.tce.Singleton;

/**
 * 线程安全懒汉模式（双重检查加锁）
 */
public class Singleton1 {
    private volatile static Singleton1 singleton1=null;//volatile修饰实例对象
    private Singleton1(){}

    private static Singleton1 getInstance(){
        if(singleton1==null){//第一次检查实例
            synchronized (Singleton1.class){//对Singleton1类加锁
                if(singleton1==null){//第二次检查实例
                    singleton1=new Singleton1();
                }
            }
        }
        return singleton1;
    }
}
