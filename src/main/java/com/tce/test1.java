package com.tce;

public class test1 {
    public static void main(String []args)
    {
        //int->char 需要强制类型转换
        int i=1;
        char c=(char)i;
        //隐式类型转换，需要兼容,只能低转高
        short s=1;
        long l=s;

        float f=1f;
        double d=f;

        byte b=1;
        int ib=b;
    }
}
