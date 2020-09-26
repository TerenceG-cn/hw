package com.tce.reflect.sample1;

import com.tce.reflect.sample1.ReflectTest;
import com.tce.reflect.sample1.Student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
public class Test {
    /**
     * @param args
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IllegalArgumentException
     * @throws SecurityException
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws SecurityException, IllegalArgumentException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, FileNotFoundException, IOException {
        Student stu = (Student) ReflectTest.iocFunction(Student.class);
        System.out.println(stu);
    }
}
