package com.tce.reflect.sample1;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class ReflectTest {
    public static Object iocFunction(Class cls) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, FileNotFoundException, IOException{
        //0.0:加载配置文件
        Properties pro = new Properties();
        pro.load(new FileInputStream("src/main/resources/instance.properties"));
        System.out.println(pro.getProperty("name"));
        //0.1:获取方法形参的对象实例
        Object obj = cls.newInstance();
        //1、利用反射机制获取这个类的所有属性
        Field fs[] = cls.getDeclaredFields();
        for(int i=0;i<fs.length;i++)
            System.out.println(fs[i].getName());
        //2、类中的属性与配置文件中保存的属性能够对应起来时，我们就将配置文件中保存的这个属性对应的值赋给类中的这个属性
        for(Field f : fs){
            //看配置文件中是否存在与属性名对应的值，如果没有，则isHere为null
            Object isHere = pro.getProperty(f.getName());
            //3、利用反射，动态调用obj对象中的所有setXXX()方法，为类中的属性赋值
            if (isHere != null){
                //3.1：获取方法名“setXXX”
                String methodName = "set" + f.getName().substring(0,1).toUpperCase() + f.getName().substring(1);
                //3.2：获取setXXX()方法的参数类型
                Class<?> paramType = Class.forName(f.getType().getName());
                //3.3：根据方法名及该方法具有的参数即可利用反射执行此方法
                //3.3.1：获得此方法
                Method method = cls.getDeclaredMethod(methodName, paramType);

                //3.3.2：执行此方法
                //3.3.2.1：获得执行此方法的实参
                Object paramExecute = new Object();
                paramExecute = isHere;
                //3.3.2.2：执行方法
                method.invoke(obj, paramExecute);
            }
        }
        return obj;
    }
}
