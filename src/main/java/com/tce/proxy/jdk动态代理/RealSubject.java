package com.tce.proxy.jdk动态代理;

public class RealSubject implements Subject
{

    /**
     * 你好
     *
     * @param name
     * @return
     */
    public String sayHello(String name)
    {
        return "hello " + name;
    }

    /**
     * 再见
     *
     * @return
     */
    public String sayGoodBye()
    {
        return " good bye ";
    }
}