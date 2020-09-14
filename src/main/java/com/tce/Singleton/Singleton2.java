package com.tce.Singleton;

/**
 * 饿汉模式
 * 空间换时间
 */
public class Singleton2 {
    private static Singleton2 singleton2 = new Singleton2();//静态实例对象已被创建
    private Singleton2() {}//私有构造方法
    public static Singleton2 getInstance() {
        return singleton2;
    }

}
