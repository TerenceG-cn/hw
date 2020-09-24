package com.tce;

import sun.reflect.generics.tree.Tree;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    public static void main(String[] args) {

        int x=5,y=5;
        y=++x*--y;
        System.out.println(y);
        System.out.println(x);
        //System.out.println("nmsl");
//        Thread thread=new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
//        thread.start();

        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("das");
        arrayList.add("dase34");
        arrayList.add("daswe");
        arrayList.add("das");
        Iterator iterator=arrayList.iterator();
        System.out.println(arrayList.toString());
        while(iterator.hasNext()){
            iterator.next();
            iterator.remove();//不会抛出快速失败的异常
        }
        System.out.println(arrayList.toString());
//
//        //arrayList.sort();
        Map<String,Integer> hp=new ConcurrentHashMap<>();
        Map<String,Integer> hp2=new HashMap<>();
//
//        int hash=402348261;
//        int len=16;
//        System.out.println((hash)%(len));
//        System.out.println((len-1)&hash);
//
//        Set<Integer> set=new HashSet<>();
//        Set<Integer> set1=new TreeSet<>();
//        set.add(1);
//        set.add(4);
//        int i=1;
//        String str1="abc";
//        String str2=new String("abc");
//        System.out.println(str1.equals(str2));
//        System.out.println(str1==str2);
//        //String str3=str2;
//        String str3=str1;
//        System.out.println(str1.equals(str3));
//        System.out.println(str1==str3);
//        System.out.println(str2.equals(str3));
//        System.out.println(str2==str3);
    }

}
