// Euclid Algorithm
// Alex Shah
// MSCS 630
// 1/31/18

import java.util.Scanner;

public class Euclid1 {
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
            System.out.println(euclidAlg(a, b));
        }
    }

    private static long euclidAlg(long a, long b) {
        if (b == 0) {
            return a;
        }
        long b2 = a - (Math.floorDiv(a, b) * b);
        return euclidAlg(b,b2);
    }
}

