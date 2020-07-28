package com.tce.algebra;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

import javax.lang.model.type.ArrayType;

import java.util.Arrays;

import static com.tce.algebra.math3.MatrixUtils.*;

/**
 * 二次型化标准型
 */
public class QuadraticForms {
    /**
     * 获取二次型矩阵A的合同矩阵C，转换成标准型
     * @param a 原矩阵A
     * @return 合同矩阵C
     */
    public static Array2DRowRealMatrix getCongruenceMatrix(Array2DRowRealMatrix a){
        double[][] array=a.getData();
        int size=array.length;
        if(array[0][0]!=0){
            Array2DRowRealMatrix c1=getIdentityMatrix(size);//获得单位矩阵
            for(int i=1;i<size;i++){
                c1.setEntry(0, i,-array[0][i]/array[0][0]);
            }
            Array2DRowRealMatrix c1T=getTransposeMatrix(c1);//获得c1的转置

            return c1T.multiply(a.multiply(c1));//C1T*A*C1 暂时写成这样
            //return getCongruenceMatrix(c1T.multiply(a.multiply(c1)));这才是正常情况
        }
        for(int i=1;i<size;i++){
            if(array[i][i]!=0){
                Array2DRowRealMatrix swpA=rowReduction2(a,i+1,1);
                System.out.println(Arrays.deepToString(swpA.getData()));
                swpA=colReduction2(swpA, i+1, 1);
                System.out.println("nmd"+Arrays.deepToString(swpA.getData()));
                return getCongruenceMatrix(swpA);
            }
        }
        return null;
    }

    public static void main(String[] args){
        double[][] a={{0,2,1},{2,1,0},{1,0,1}};
        Array2DRowRealMatrix c=getCongruenceMatrix(new Array2DRowRealMatrix(a));
        assert c != null;
        System.out.println(Arrays.deepToString(c.getData()));

    }
}
