package com.tce.algebra.math3;


import org.ujmp.core.Matrix;

public class UJMPTest {
    public static void main(String[] args) {
//        Matrix mat = Matrix.Factory.zeros(4, 4);
//        mat.setAsDouble(2,0,0);mat.setAsDouble(0,0,1);mat.setAsDouble(5,0,2);mat.setAsDouble(6,0,3);
//        mat.setAsDouble(1,1,0);mat.setAsDouble(3,1,1);mat.setAsDouble(3,1,2);mat.setAsDouble(6,1,3);
//        mat.setAsDouble(-1,2,0);mat.setAsDouble(1,2,1);mat.setAsDouble(2,2,2);mat.setAsDouble(1,2,3);
//        mat.setAsDouble(1,3,0);mat.setAsDouble(0,3,1);mat.setAsDouble(1,3,2);mat.setAsDouble(3,3,3);
//
//        System.out.println(mat);
//        mat.getRowCount(); // mat的行数
//        mat.getColumnCount(); // mat的列数
//
//        Matrix transpose = mat.transpose(); // 矩阵转置
//
//        Matrix plus = mat.plus(transpose); // 矩阵加法
//        Matrix minus = mat.minus(transpose); // 矩阵减法
//        Matrix mtimes = mat.mtimes(transpose); // 矩阵乘法
//        Matrix times = mat.times(2); // 矩阵elementwise乘法
//        System.out.println(mat.inv()); // 矩阵求逆
//
//        mat.det(); // 矩阵的determinant
//
//        Matrix[] eig = mat.eig(); // 输出的eig内有两个元素，分别是特征向量和特征值

        Matrix mat = Matrix.Factory.zeros(4, 4);
        mat.setAsDouble(2,0,0);mat.setAsDouble(0,0,1);mat.setAsDouble(-2,0,2);mat.setAsDouble(1,0,3);
        mat.setAsDouble(1,1,0);mat.setAsDouble(1,1,1);mat.setAsDouble(1,1,2);mat.setAsDouble(3,1,3);
        mat.setAsDouble(0,2,0);mat.setAsDouble(2,2,1);mat.setAsDouble(1,2,2);mat.setAsDouble(1,2,3);
        mat.setAsDouble(1,3,0);mat.setAsDouble(2,3,1);mat.setAsDouble(2,3,2);mat.setAsDouble(2,3,3);

        System.out.println(mat);
        Matrix transpose = Matrix.Factory.zeros(4, 4);
        transpose.setAsDouble(1,0,0);transpose.setAsDouble(1,0,1);transpose.setAsDouble(-1,0,2);transpose.setAsDouble(-1,0,3);
        transpose.setAsDouble(2,1,0);transpose.setAsDouble(-1,1,1);transpose.setAsDouble(2,1,2);transpose.setAsDouble(-1,1,3);
        transpose.setAsDouble(-1,2,0);transpose.setAsDouble(1,2,1);transpose.setAsDouble(1,2,2);transpose.setAsDouble(0,2,3);
        transpose.setAsDouble(0,3,0);transpose.setAsDouble(1,3,1);transpose.setAsDouble(1,3,2);transpose.setAsDouble(1,3,3);
        System.out.println(transpose);

        Matrix trans=transpose.inv();
        System.out.println(trans);


        Matrix mtimes = trans.mtimes(mat); // 矩阵乘法
        System.out.println(mtimes);

        Matrix x=Matrix.Factory.zeros(4, 1);
        x.setAsDouble(1,0,0);
        x.setAsDouble(0,1,0);
        x.setAsDouble(0,2,0);
        x.setAsDouble(0,3,0);

        Matrix mtimes1=trans.mtimes(x);
        System.out.println(mtimes1);

        Matrix mtimes2=mat.inv().mtimes(x);
        System.out.println(mtimes2);

        System.out.println(mtimes.mtimes(mtimes1));
    }
}
