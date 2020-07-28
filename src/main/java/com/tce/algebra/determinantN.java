package com.tce.algebra;

/**
 * 求n阶行列式（勉强可用）
 */
public class determinantN {
    private double[][] arrangerMatrix;
    private double arrangerResult = 1.0;

    public static void main(String args[]) {
        //double[][] a = {{4, 1, 4, 8}, {1, 1, 3, 2}, {2, 2, 5, 1}, {2, 2, 1, 4}};
        double[][] a = {{6,-1,3,2}, {5,-3,3,2}, {3,-1,-1,2}, {4,-1,3,-1}};//
        determinantN ar = new determinantN();
        ar.arrFunction(a);//把行列式变成上三角行列式
        ar.displayMatrix();//显示得出的上三角行列式
        ar.displayResult();//显示行列式计算结果   
    }

    public void arrFunction(double[][] a) {//Guass 消去
        double k = 0;
        for (int p = 0; p < a[0].length - 1; p++) {
            for (int r = p + 1; r < a.length; r++) {
                k = a[r][p] / a[p][p];//除法会导致误差
                a[r][p] = 0;
                for (int c = p + 1; c < a[0].length; c++) {
                    a[r][c] = a[r][c] - k * a[p][c];
                }//u
            }//r
        }//c
        arrangerMatrix = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                arrangerMatrix[i][j] = a[i][j];
                if (i == j) {
                    arrangerResult = arrangerResult * a[i][j];
                }//计算主对角线相乘的结果
                //System.out.println (a[i][j]+" ");
            }//j
        }//i
    }


    public void displayMatrix() {
        for (int i = 0; i < arrangerMatrix.length; i++) {
            for (int j = 0; j < arrangerMatrix[0].length; j++) {
                System.out.print(arrangerMatrix[i][j] + " ");
            }//j
            System.out.println();
        }//i
    }


    public void displayResult() {
        System.out.println("The result is " + arrangerResult);
    }

}
