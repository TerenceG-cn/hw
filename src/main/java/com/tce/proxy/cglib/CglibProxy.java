package com.tce.proxy.cglib;


import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor
{
    // 根据一个类型产生代理类，此方法不要求一定放在MethodInterceptor中
    public Object CreatProxyedObj(Class<?> clazz)
    {
        //增强
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(clazz);

        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    //拦截
    public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable
    {
        // 这里增强
        System.out.println("收钱");

        return arg3.invokeSuper(arg0, arg2);
    }

    public static void main(String[] args)
    {
        int times = 1000000;

        CglibProxy proxy2 = new CglibProxy();
        long time5 = System.currentTimeMillis();
        Star star2 = (Star)proxy2.CreatProxyedObj(LiuDeHua.class);
        long time6 = System.currentTimeMillis();
        System.out.println("cglib创建时间：" + (time6 - time5));


        long time7 = System.currentTimeMillis();
        for (int i = 1; i <= times; i++)
        {
            star2.sing("ss");

            star2.dance("ss");
        }

        long time8 = System.currentTimeMillis();

        System.out.println("cglib执行时间" + (time8 - time7));
    }
}