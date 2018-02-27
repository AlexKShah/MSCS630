// Alex Shah
// MSCS 630
// 2/14/18

import java.util.Scanner;

public class Lab3_2 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    char s = input.nextLine().charAt(0);
    String p = input.nextLine();

    splitPSoup(s, p);

  }

  static String splitPSoup(char s, String p) {
    String newP = "";
    while (newP.length() < 16) {
      for (int i = 0; i <= p.length(); i++) {
        if (i < p.length()) {
          newP += p.charAt(i);
        } else if (i >= p.length()) {
          newP += s;
        }
      }
    }
  }

  static int[][] getHexMatP(char s, String p) {
    //TODO
  }
}