// Euclid Extended Algorithm
// Alex Shah
// MSCS 630
// 1/31/18

import java.util.Scanner;

class Euclid2 {
    // 2 inputs: a, b positive integers
    // 3 outputs: d = gcd(a,b); x; y;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            long a = input.nextInt();
            long b = input.nextInt();
            long temp;
            if (a < b) {
                temp = a;
                a = b;
                b = temp;
            }
            long[] res = euclidAlgExt(a, b);
            for (int i = 0; i < res.length; i++) {
                System.out.print(res[i] + " ");
            }
            System.out.println("\n");
        }
    }

    static long[] euclidAlgExt(long a, long b) {
        long[] U = new long[3];
        long[] V = new long[3];
        long[] W = new long[3];

        U[0] = a;
        U[1] = 1;
        U[2] = 0;

        V[0] = b;
        V[1] = 0;
        V[2] = 1;

        while (V[0] > 0) {
            long tmp = (U[0] / V[0]);
            for (int k = 0; k < W.length; k++) {
                W[k] = U[k] - (tmp * V[k]);
            }
            for (int i = 0; i < V.length; i++) {
                U[i] = V[i];
            }
            for (int i = 0; i < W.length; i++) {
                V[i] = W[i];
            }
        }

        long[] res = new long[3];
        res[0] = U[0];
        res[1] = U[1];
        res[2] = U[2];

        return res;
    }

}