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
        System.out.println(getHexMatP(s, newP)); //puts out garbage
        newP = "";
      }
    }
  }

  static byte[][] getHexMatP(char s, String p) {
    byte[][] cipher = new byte[4][4];
    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 4; col++) {
        for (int i = 0; i < p.length(); i++) {
          cipher[row][col] = (byte) p.charAt(i);
        }
      }
    }
    return cipher;
  }
}