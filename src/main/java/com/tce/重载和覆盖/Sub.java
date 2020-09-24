package com.tce.重载和覆盖;

public class Sub extends Super {
    public Sub(){
        super();
    }
    /*覆盖*/
    public float get(int n){
        return 7.0f;
    }

    /*重载参数数量不同*/
    public float get(int var1,int var2){
        return var1;
    }
    /*重载参数类型不同*/
    public float get(float var1){
        return var1;
    }
    /*
     * 返回类型不同不构成重载
     * public int get(int var1){
     *      return var1;
     * }
     */

}
