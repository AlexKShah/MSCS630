// Determinant Algorithm
// Alex Shah
// MSCS 630
// 2/14/18

import java.util.Scanner;

public class Lab3 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int m = input.nextInt();
    int n = input.nextInt();
    int[][] A = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        A[i][j] = input.nextInt();
      }
    }
    int det = cofModDet(m, A);
    System.out.println(det);
  }

  static int cofModDet(int m, int[][] A) {
    int det = 0;
    if (A.length == 1) {
      return (A[0][0] % m);
    } else if (A.length == 2) {
      return (A[0][0]*A[1][1] - A[0][1]*A[1][0]); //m?
    }
    for (int i = 0; i < A.length; i++) {
      int[][] newA = new int[A.length - 1][A.length - 1];
      for (int j = 1; j < A.length; j++) {
        for (int k = 0; k < A.length; k++) {
          if (k < i) {
            newA[j - 1][k] = A[j][k];
          } else if (k > i) {
            newA[j - 1][k - 1] = A[j][k];
          }
        }
      }
      int n;
      if (i % 2 == 0) {
        n = 1;
      } else {
        n = -1;
      }
      det += n * A[0][i] * (cofModDet(m, newA));
    }
    return det;
  }
}