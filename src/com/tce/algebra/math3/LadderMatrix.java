package com.tce.algebra.math3;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

import java.util.Arrays;

public class LadderMatrix {
    /**
     * 经过行初等变换转化为行阶梯矩阵
     *
     * @param matrix 普通矩阵
     * @return 返回行阶梯矩阵
     */
    public static Array2DRowRealMatrix toLaddermatrix(Array2DRowRealMatrix matrix) {
        double[][] m = matrix.getData();
        return gaussElimination1(matrix);
    }

    /**
     * 高斯消去
     *
     * @param matrix
     * @return
     */
    private static Array2DRowRealMatrix gaussElimination(Array2DRowRealMatrix matrix) {
        final int rows = matrix.getRowDimension();
        int maxRow, j;
        for (int i = 0; i < rows; i++) {
            maxRow = findMax(matrix, i);//找到i行i列绝对值最大的元素的一行
            System.out.println("第i行i列最大元素所在行" + (maxRow + 1));
            if (maxRow > i)
                rowReduction2(matrix, i + 1, maxRow + 1);//交换
            System.out.println("交换：" + Arrays.deepToString(matrix.getData()));
            if (matrix.getEntry(i, i) != 0.0) {
                for (j = i + 1; j < rows + 1; j++) {
                    matrix.setEntry(i, j, matrix.getEntry(i, j) / matrix.getEntry(i, i));
                }
                matrix.setEntry(i, i, 1);//第一个不为0的数化为1
                System.out.println("化1：             " + Arrays.deepToString(matrix.getData()));


                for (int l = i + 1; l < rows; l++) {
                    double factor = -matrix.getEntry(l, i);
                    matrix = rowReduction1(matrix, factor, l + 1, i + 1);//把第i行乘以factor加到l行
                }
                reduceErro(matrix);
                System.out.println("把第i行倍数加到下几行:" + Arrays.deepToString(matrix.getData()));
            }

        }
        return matrix;
    }

    private static int findMax(Array2DRowRealMatrix matrix, int position) {
        int index = 0;
        int N = matrix.getRowDimension();
        double max = -1;
        for (int i = position; i < N; i++) {
            if (Math.abs(matrix.getEntry(i, position)) > Math.abs(max)) {
                max = matrix.getEntry(i, position);
                index = i;
                System.out.println("max:"+max);
            }
        }

        return index;
    }

    /**
     * double 精度计算存在误差，可以进一步优化
     *
     * @param m
     */
    private static void reduceErro(Array2DRowRealMatrix m) {
        int r = m.getRowDimension();
        int c = m.getColumnDimension();
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++) {
                double d = m.getEntry(i, j);
                d = (double) Math.round(d * 1000_000) / 1000_000;
                m.setEntry(i, j, d);
            }
    }

    /**
     * 高斯消去的优化版本,针对矩阵元素全是整数
     *
     * @param matrix，整数矩阵
     * @return
     */
    private static Array2DRowRealMatrix gaussElimination1(Array2DRowRealMatrix matrix) {
        final int rows = matrix.getRowDimension();
        Array2DRowRealMatrix res = new Array2DRowRealMatrix(matrix.getData());
        int maxRow, j;
        for (int i = 0; i < Math.min(matrix.getRowDimension(), matrix.getColumnDimension()); i++) {
            System.out.println("I:"+i);
            maxRow = findMax(res, i);//找到i行i列绝对值最大的元素的一行
            System.out.println("第i行i列最大元素所在行" + (maxRow + 1));
            if (maxRow > i)
                rowReduction2(res, i + 1, maxRow + 1);//交换
            System.out.println("交换：" + Arrays.deepToString(res.getData()));
            int tag = (int) res.getEntry(i, i);//此i行第一个不为0的数
            if (tag != 0)
                for (int l = i + 1; l < rows; l++) {
                    int tar = (int) res.getEntry(l, i);
                    if (tar != 0) {
                        int lcm_times_tag = lcm(tag, tar) / tag;
                        int lcm_times_tar= lcm(tag, tar) / tar;
                        rowReduction3(res, l+1, lcm_times_tar);
                        res = rowReduction1(res, -lcm_times_tag, l + 1, i + 1);//把第i行乘以factor加到l行
                    }
                }
            System.out.println("--：" + Arrays.deepToString(res.getData()));
        }
        return res;
    }

    /**
     * 欧几里得算法求最大公因数
     *
     * @param n1
     * @param n2
     * @return
     */
    private static int gcd(int n1, int n2) {
        int a = Math.max(n1, n2);
        int b = Math.min(n1, n2);
        if (a == 0 || b == 0) return 0;
        while (b != 0) {
            int c = a % b;
            a = b;
            b = c;
        }
        return a;
    }

    /**
     * 求最小公倍数
     *
     * @param a
     * @param b
     * @return
     */
    private static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }


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
    public static void rowReduction2(Array2DRowRealMatrix src, int i, int j) {
        double[] tmp = src.getRow(i - 1);
        src.setRow(i - 1, src.getRow(j - 1));
        src.setRow(j - 1, tmp);
    }

    /**
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

    public static void main(String[] args) {

        //double[][] c={{1,1,2,1,5},{1,1,2,6,10},{1,2,5,2,7}}; √
        //double[][] c={{1,1,1,1,1},{3,2,1,1,-3},{0,1,2,2,6},{5,4,3,3,-1}}; √
        //double[][] c={{1,0,3,1,2},{-1,3,0,-1,1},{2,1,7,2,5},{4,2,14,0,6}}; √ 精度问题
        //double[][] c={{1,2,0,-3,2,1},{1,-1,-3,1,-3,2},{2,-3,4,-5,2,7},{9,-9,6,-16,2,25}}; 精度
        //double[][] c = {{1, 3, 5, -4, 0, 1}, {1, 3, 2, -2, 1, -1}, {1, -2, 1, -1, -1, 3}, {1, -4, 1, 1, -1, 3}, {1, 2, 1, -1, 1, -1}};

        double[][] c = {{6, 1, 1, 7}, {4,0,4,1}, {1,2,-9,0}, {-1,3,-16,-1}, {2,-4,22,3}};
        System.out.println("原矩阵；" + Arrays.deepToString(new Array2DRowRealMatrix(c).getData()));
        System.out.println("阶梯矩阵：" + Arrays.deepToString(toLaddermatrix(new Array2DRowRealMatrix(c)).getData()));


    }
}
