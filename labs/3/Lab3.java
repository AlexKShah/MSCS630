// Determinant Algorithm
// Alex Shah
// MSCS 630
// 2/14/18

import java.util.Scanner;

public class Lab3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //pick 32 bit type
        int m = input.nextInt();
        int n = input.nextInt();
        int[][] A = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = input.nextInt();
            }
        }
        int det = cofModDet(m, A);
    }
    static int cofModDet(int m, int[][] a) {
        int det = 0;
        int n = A[0].length; //square
        if (n==1){
            //1x1 = A
            det = A[0][0];
        } else if (n==2) {
            //2x2 = AD-CB
            det = (A[0][0]*A[1][1] - A[0][1]*A[1][0]);
        } else if (n==3) {
            //3x3 =
        } else {

        }
        return det;
    }
}