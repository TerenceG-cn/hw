package com.tce;

import sun.reflect.generics.tree.Tree;

import java.util.*;

public class Main {

    public static void main(String[] args) {

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

        //arrayList.sort();
       HashMap<String,Integer> hp=new HashMap<>();

        int hash=402348261;
        int len=16;
        System.out.println((hash)%(len));
        System.out.println((len-1)&hash);
    }

}
