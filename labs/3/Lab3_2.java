// Alex Shah
// MSCS 630
// 2/14/18

import java.util.Scanner;

public class Lab3_2 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    char s = input.nextLine().charAt(0);
    String p = input.nextLine();
    String newP = "";
    for (int i = 0; i <= p.length(); i++) {
      while (newP.length() < 16) {
        if (i < p.length()) {
          newP += p.charAt(i);
        } else if (i >= p.length()) {
          newP += s;
        }
        getHexMatP(s, newP);
      }
    }
  }

  static int[][] getHexMatP(char s, String p) {
    int[][] cipher = new int[4][4];
    for (int i = 0; i < p.length(); i++) {
      for (int row = 0; row < 4; row++) {
        for (int col = 0; col < 4; col++) {
          cipher[row][col] = (int) p.charAt(i);
          System.out.println(cipher[row][col]); //prints "97" a bunch, why?
        }
      }
    }
    return cipher;
  }
}