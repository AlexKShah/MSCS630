import java.util.Scanner;

/**
 * file: Lab3_2.java
 * author: Alex Shah
 * course: MSCS 630
 * assignment: Lab 3 part 2
 * due date: 3/19/18
 * version: 1.0
 *
 * Create a hexadecimal cipher text
 * matrix from a given plaintext,
 * in a padded 4x4 layout.
 */

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

    //send one 4x4 at a time to get a hex matrix
    for (int i = 0; i < newP.length() - 1; i++) {
      if (i % 16 == 0 || i == 0) {
        String[][] result = new String[4][4];
        result = getHexMatP(s, newP.toString().substring(i, i + 16));
        //do something with result here
      }
    }
  }

  static String[][] getHexMatP(char s, String p) {
    String[][] cipher = new String[4][4];

    //turn ascii text into hexadecimal
    StringBuffer hexP = new StringBuffer();
    for (int i = 0; i < p.length(); i++) {
      hexP.append(Integer.toHexString(p.charAt(i)).toUpperCase());
    }
    //for now keep it in a linear array
    String[] cipherString = new String[hexP.length()];

    //hexadecimal is two characters, add them both to one element
    for (int i = 0; i < hexP.length() - 1; i += 2) {
      String hex1 = Character.toString(hexP.charAt(i));
      String hex2 = Character.toString(hexP.charAt(i + 1));
      //make sure we start with, and incrementally add hexadecimal
      if (i == 0) {
        cipherString[i] = hex1.concat(hex2);
      } else {
        cipherString[i / 2] = hex1.concat(hex2);
      }
    }
    //iterate through 4x4 matrix cipher and add the hexadecimal
    //elements from our String array to the String Matrix
    for (int j = 0; j < 4; j++) {
      int count = j * 4;
      cipher[0][j] = cipherString[count];
      cipher[1][j] = cipherString[count + 1];
      cipher[2][j] = cipherString[count + 2];
      cipher[3][j] = cipherString[count + 3];
    }
    //print out the cipher text matrix for verification
    for (int k = 0; k < cipher.length; k++) {
      System.out.println(cipher[k][0] + " " + cipher[k][1] + " " + cipher[k][2] + " " + cipher[k][3]);
    }
    return cipher;
  }
}