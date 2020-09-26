package com.tce.reflect.sample2;

import javax.management.InstanceNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Weather_reflec {
    List<Object> ObjectList;//模拟Spring容器

    public Weather_reflec(){
        ObjectList = new ArrayList<>();
    }
    //更好的做好是先找出带有注解的类
    public void getRef(Object object) throws ClassNotFoundException,InstantiationException,IllegalAccessException{
        Class<?> clazz=object.getClass();
        if(clazz.isAnnotationPresent(Entity.class)){//判断是否带有Entity注解
            Field[] fields=clazz.getDeclaredFields();//获得属性字段
            for(Field field:fields){
                System.out.println(field);
                if(field.isAnnotationPresent(Resources.class)) {//判断是否需要注入
                    //将Rain类加载
                    Class<?> rainClass=Class.forName(field.getType().getName(),false,Thread.currentThread().getContextClassLoader());
                    field.set(object,rainClass.newInstance());//属性字段赋给rain
                    ObjectList.add(object);//保存到容器
                }
            }
        }
    }

    public List<Object> returnList(){
        return ObjectList;
    }

}
