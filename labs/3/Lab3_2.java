// Alex Shah
// MSCS 630
// 2/14/18

import java.util.Scanner;

public class Lab3_2 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    char s = input.nextLine().charAt(0);
    String p = input.nextLine();

    //pad input string with character s
    StringBuffer newP = new StringBuffer(p);
    while (newP.length() % 16 != 0) {
      newP.append(s);
    }

    //turn ascii text into hexadecimal
    StringBuffer hexP = new StringBuffer();
    for (int i = 0; i < newP.length(); i++) {
      hexP.append(Integer.toHexString(newP.charAt(i)).toUpperCase());
    }

    //break p into chunks, process one 4x4 at a time
    StringBuffer partP = new StringBuffer();
    for (int i = 0; i < hexP.length(); i++) {
      if (partP.length() % 32 == 0) {
        getHexMatP(s, partP.toString());
      } else {
        partP.append(hexP.charAt(i));
        partP.append(hexP.charAt(i+1));
      }
      partP = new StringBuffer();
    }
  }
  //iterate through 4x4, add to cipher, print
  static int[][] getHexMatP(char s, String p) {
    int[][] cipher = new int[4][4];
    for (int i = 0; i < p.length(); i++) {
      for()
    }
    return cipher;
  }
}