package com.example.woj.happynumbers.Rosetta.Experiments;

/**
 * Created by woj on 03/08/15.
 */
public class MatrixMult {

    public static int DEFAULT_NUMBER = 650;

    public static void execute(int sizeMatrix){
        createMatrix(sizeMatrix);
    }

    private static void createMatrix(int sizeMatrix) {
        double A[][] = new double[sizeMatrix][sizeMatrix];
        double B[][] = new double[sizeMatrix][sizeMatrix];

        for (int i = 0; i < A.length; i++) {

            for (int w = 0; w < A[i].length; w++) {

                A[i][w] = ((20 * w) + i);
            }
        }

        for (int i = 0; i < B.length; i++) {

            for (int w = 0; w < B[i].length; w++) {

                B[i][w] = ((25 * w) + i);
            }
        }
        mult(A,B);
    }

    public static double[][] mult(double a[][], double b[][]){//a[m][n], b[n][p]
        if(a.length == 0) return new double[0][0];
        if(a[0].length != b.length) return null; //invalid dims

        int n = a[0].length;
        int m = a.length;
        int p = b[0].length;

        double ans[][] = new double[m][p];

        for(int i = 0;i < m;i++){
            for(int j = 0;j < p;j++){
                for(int k = 0;k < n;k++){
                    ans[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return ans;
    }

//
//    public static int[][] execute(int matrixSize) {
//
//        int A[][] = new int[matrixSize][matrixSize];
//        int B[][] = new int[matrixSize][matrixSize];
//
//        for (int i = 0; i < A.length; i++) {
//
//            for (int w = 0; w < A[i].length; w++) {
//
//                A[i][w] = ((20 * w) + i);
//            }
//        }
//
//        for (int i = 0; i < B.length; i++) {
//
//            for (int w = 0; w < B[i].length; w++) {
//
//                B[i][w] = ((25 * w) + i);
//            }
//        }
//
//        if (A.length == 0) return new int[0][0];
//        if (A[0].length != B.length) return null; //invalid dims
//
//        int n = A[0].length;
//        int m = A.length;
//        int p = B[0].length;
//
//        int ans[][] = new int[m][p];
//
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < p; j++) {
//                for (int k = 0; k < n; k++) {
//                    ans[i][j] += A[i][k] * B[k][j];
//                }
//            }
//        }
//        return ans;
//    }
}
