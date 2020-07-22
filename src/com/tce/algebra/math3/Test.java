package com.tce.algebra.math3;

import org.apache.commons.math3.linear.*;

/*
 *博客地址:http://blog.csdn.net/qy20115549/
 */
public class Test {
    public static void main(String args[]){
        double a [][] = new double[5][5];
        for(int i = 0; i < a.length; i++) {
            a[i][i] = 3;
        }
        double b [][] = new double[5][5];
        for(int i = 0; i < b.length; i++) {
            b[i][i] = 1;
        }
        //将数组转化为矩阵
        RealMatrix matrix = new Array2DRowRealMatrix(b);
        System.out.println("创建的数组为：\t"+matrix);
        //获取矩阵的列数 getColumnDimension()
        System.out.println("矩阵的列数为:\t"+matrix.getColumnDimension());
        //获取矩阵的行数
        System.out.println("矩阵的行数为:\t"+matrix.getRowDimension());
        //获取矩阵的某一行,返回,仍然为矩阵
        System.out.println("矩阵的第一行为:\t"+ matrix.getRowMatrix(0));
        //获取矩阵的某一行,返回,转化为向量
        System.out.println("矩阵的第一行向量表示为:\t"+ matrix.getRowVector(1) );

        //矩阵的加法
        matrix=matrix.add(new Array2DRowRealMatrix(a));
        //指定某行某列的一个元素加val
        matrix.addToEntry(0, 0, 7);
        //得到某行某列的一个元素
        System.out.println(matrix.getEntry(0, 0));
        //矩阵某行某列的一个元素*val
        matrix.multiplyEntry(0, 0, 8);
        //设置某行某列的一个元素
        matrix.setEntry(0, 0, 99);
        System.out.println(matrix.getEntry(0, 0));

        //按列顺序访问（不改变原值）所有矩阵条目。
        matrix.walkInColumnOrder(new RealMatrixPreservingVisitor() {
            @Override
            public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
                System.out.println(String.format("start: %d, %d, %d, %d, %d, %d", rows, columns, startRow, endRow, startColumn, endColumn));
            }

            @Override
            public void visit(int row, int column, double value) {
                System.out.println(String.format("visit: %d, %d, %f", row, column, value));
            }

            @Override
            public double end() {
                return 0;
            }
        });
        //按列顺序访问（并可能更改）所有矩阵条目。
        matrix.walkInColumnOrder(new RealMatrixChangingVisitor() {
            @Override
            public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
                System.out.println(String.format("start: %d, %d, %d, %d, %d, %d", rows, columns, startRow, endRow, startColumn, endColumn));
            }

            @Override
            public double visit(int row, int column, double value) {
                System.out.println(String.format("visit: %d, %d, %f", row, column, value));
                return 0;
            }

            @Override
            public double end() {
                return 0;
            }
        });



//        //矩阵的乘法
//        double testmatrix[][] = new double[2][2];
//        testmatrix[0][0] = 1;
//        testmatrix[0][1] = 2;
//        testmatrix[1][0] = 3;
//        testmatrix[1][1] = 4;
//        RealMatrix testmatrix1 = new Array2DRowRealMatrix(testmatrix);
//        System.out.println("两个矩阵相乘后的结果为：\t"+testmatrix1.multiply(testmatrix1) );
//        //矩阵的转置
//        System.out.println("转置后的矩阵为：\t"+testmatrix1.transpose());
//        //矩阵求逆
//        RealMatrix inversetestMatrix = inverseMatrix(testmatrix1);
//        System.out.println("逆矩阵为：\t"+inversetestMatrix);
//        //矩阵转化为数组 getdata
//        double matrixtoarray[][]=inversetestMatrix.getData();
//        System.out.println("数组中的某一个数字为：\t"+matrixtoarray[0][0]);
    }
    //求逆函数
    public static RealMatrix inverseMatrix(RealMatrix A) {
        RealMatrix result = new LUDecomposition(A).getSolver().getInverse();
        return result;
    }
}
