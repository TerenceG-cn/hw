package com.tce.algebra.math3;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MatrixUtils {
    /**
     * 行约简类型1：用a乘以原矩阵的第j行再加到第i行上去
     *
     * @param src 原矩阵
     * @param a   数乘
     * @param i   i行
     * @param j   j行
     * @return
     */
    public static Array2DRowRealMatrix rowReduction1(Array2DRowRealMatrix src, double a, int i, int j) {
        int cols = src.getRowDimension();
        //构造初等矩阵E
        double[][] elementary = new double[cols][cols];
        for (int k = 0; k < cols; k++)
            elementary[k][k] = 1;
        elementary[i - 1][j - 1] = a;
        //左乘 EX
        return new Array2DRowRealMatrix(elementary).multiply(src);
    }

    /**
     * 行约简类型2：交换第i行和第j行
     *
     * @param src
     * @param i
     * @param j
     * @return
     */
    public static Array2DRowRealMatrix rowReduction2(Array2DRowRealMatrix src, int i, int j) {
        Array2DRowRealMatrix res=new Array2DRowRealMatrix(src.getData());
        double[] tmp = res.getRow(i - 1);
        res.setRow(i - 1, res.getRow(j - 1));
        res.setRow(j - 1, tmp);
        return res;
    }

    /**
     * 列约简类型2：交换第i列和第j列
     * @param src
     * @param i
     * @param j
     */
    public static Array2DRowRealMatrix colReduction2(Array2DRowRealMatrix src, int i, int j) {
        Array2DRowRealMatrix res=new Array2DRowRealMatrix(src.getData());
        double[] tmp = res.getColumn(i - 1);
        res.setColumn(i - 1, res.getColumn(j - 1));
        res.setColumn(j - 1, tmp);
        return res;
    }

    /**
     * 行约简类型2：第i行乘以某个数c
     * @param src
     * @param i   第i行
     * @param c   数乘
     */
    public static void rowReduction3(Array2DRowRealMatrix src, int i, int c) {
        int cols = src.getColumnDimension();
        for (int k = 0; k < cols; k++) {
            src.setEntry(i - 1, k, src.getEntry(i - 1, k) * c);
        }
    }

    /**
     * 得到指定大小的单位矩阵
     * @param size
     * @return
     */
    public static Array2DRowRealMatrix getIdentityMatrix(int size){
        double identity[][]=new double[size][size];
        for(int i=0;i<size;i++){
            identity[i][i]=1;
        }
        return new Array2DRowRealMatrix(identity);
    }

    /**
     * 获得转置矩阵
     * @param a
     * @return
     */
    public static Array2DRowRealMatrix getTransposeMatrix(Array2DRowRealMatrix a) {
        return (Array2DRowRealMatrix) a.transpose();
    }

    /**
     * 求逆矩阵
     * @param mat
     * @return
     */
    public static Array2DRowRealMatrix getReMatrix(Array2DRowRealMatrix mat) {
        //创建一个矩阵接收逆矩阵数据
        Array2DRowRealMatrix reMatrix = new Array2DRowRealMatrix(mat.getRowDimension(),mat.getColumnDimension());
        //得到原矩阵行列式的值
        double value = getMatrixValue(mat);
        System.out.println("原矩阵的值"+value);//1 ,行列式求错了
        //判断矩阵行列式的值是否为零
        if(Math.abs(value) <= 10e-6) {
            System.out.println("该矩阵不可逆！");
            return null;
        }
        //将原矩阵mat赋值除以原行列式的值value给逆矩阵
        for (int i = 0; i < reMatrix.getRowDimension(); i++) {
            for (int j = 0; j < reMatrix.getColumnDimension(); j++) {
                reMatrix.setEntry(i, j, getWithMatrix(mat).getEntry(i, j) / value);
            }
        }
        return reMatrix;

    }
    /**
     * 求矩阵的行列式的值
     * @param mat
     * @return
     */
    public static double getMatrixValue(Array2DRowRealMatrix mat) {
        if(mat.getRowDimension() != mat.getColumnDimension()) {
            System.out.println("该矩阵不是方阵，没有行列式");
            return Double.MIN_VALUE;
        }
        //若为1*1矩阵则直接返回
        if(mat.getRowDimension() == 1) return mat.getEntry(0, 0);
        //若为2*2矩阵则直接计算返回结果
        if(mat.getRowDimension() == 2) {
            return mat.getEntry(0, 0)*mat.getEntry(1, 1) - mat.getEntry(0, 1)*mat.getEntry(1, 0);
        }
        //行列式的值
        double matrixValue = 0;
        for (int i = 0; i < mat.getColumnDimension(); i++) {
            //获取0，i位置的余子式，即第一行的余子式
            Array2DRowRealMatrix m = getComplementMinor(mat, 0, i);
            //将第一行的代数余子式相加 ，递归下去
            matrixValue += Math.pow(-1, i) * getMatrixValue(m)*mat.getEntry(0, i);//这sb少*a1i

        }
        return matrixValue;
    }

    /**
     * 求矩阵的伴随矩阵
     * @param mat
     * @return
     */
    public static Array2DRowRealMatrix getWithMatrix(Array2DRowRealMatrix mat) {
        //创建一个矩阵存放伴随矩阵的值
        Array2DRowRealMatrix withMatrix = new Array2DRowRealMatrix(mat.getRowDimension(),mat.getColumnDimension());
        //遍历withMatrix存放对应的mat的值
        for (int i = 0; i < withMatrix.getRowDimension(); i++) {
            for (int j = 0; j < withMatrix.getColumnDimension(); j++) {
                double temp = Math.pow(-1, i+j) * getMatrixValue(getComplementMinor(mat, j, i));
                if(Math.abs(temp) <= 10e-6) temp = 0;
                withMatrix.setEntry(i, j,temp);
            }
        }
        //返回结果
        return withMatrix;
    }

    /**
     * 求矩阵在i,j处余子式
     * @param mat
     * @param i
     * @param j
     * @return
     */
    public static Array2DRowRealMatrix getComplementMinor(Array2DRowRealMatrix mat, int i, int j) {
        //创建一个新的矩阵用于接收表示该余子式，需删除本行本列的数值
        Array2DRowRealMatrix m = new Array2DRowRealMatrix(mat.getRowDimension()-1,mat.getColumnDimension()-1);
        //用于遍历新矩阵m的变量
        int row =0 ,col=0;
        /*
         * 遍历原矩阵的数据，j2表示行,k表示列
         */
        for (int j2 = 0; j2 < mat.getRowDimension(); j2++) {
            //在第i行除的数据省略
            if(j2 == i) continue;
            for (int k = 0; k < mat.getColumnDimension(); k++) {
                //在第j列的数据省略
                if(k == j) continue;
                //赋值
                m.setEntry(row, col,mat.getEntry(j2, k));
                //遍历新矩阵的变量
                col++;
                if(col >= m.getColumnDimension() ) {
                    col = 0;
                    row++;
                }
            }
        }
        return m;
    }

    public static void main(String[] args){
        /**求逆矩阵测试
        //double[][] a={{1,0},{2,4}}; √
        //double[][] a={{4,0,1},{0,1,0},{0,1,1}}; √
        double[][] a={{0,1,2},{1,1,4},{2,-1,0}};
        Array2DRowRealMatrix reM =getReMatrix(new Array2DRowRealMatrix(a));
        assert reM != null;
        System.out.println(Arrays.deepToString(reM.getData()));

        System.out.println(Arrays.deepToString(new Array2DRowRealMatrix(a).multiply(reM).getData()));
        System.out.println(Arrays.deepToString(reM.multiply(new Array2DRowRealMatrix(a)).getData()));
         */
        double[][] a={{1,0,3},{2,1,4},{3,2,1}};
        Array2DRowRealMatrix ar = new Array2DRowRealMatrix(a);
        ar=colReduction2(ar, 3, 1);
        System.out.println(Arrays.deepToString(ar.getData()));
    }
}
