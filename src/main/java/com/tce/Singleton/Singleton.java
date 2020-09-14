package com.tce.Singleton;


/**
 * 饱汉模式/懒汉模式
 * 时间换空间 延时加载
 */
public class Singleton {
    private static Singleton singleton=null;//内部静态对象实例
    private Singleton(){}//构造方法私有
    public static Singleton getInstance(){//对外提供公共静态方法，返回实例对象
        if(singleton==null)
            singleton=new Singleton();
        return singleton;
    }
}
/**
 * 优点：懒加载启动快，资源占用小，使用时才实例化，无锁。
 *
 * 缺点：非线程安全。
 */
