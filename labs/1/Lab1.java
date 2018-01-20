// Alex Shah
// MSCS 630
// Lab 1
// plainText to number psuedo cipher

import java.util.Scanner;

// Class Lab 1 to contain Lab 1 functions
class Lab1 {

  // Input: text in plaintext to encrypt
  // Encrypts by converting english characters and spaces to numbers
  // Output: Numerical pseudo cipher representation of input
  public static void main(String args[]) {
    Scanner input = new Scanner(System.in);
    while (input.hasNext()) {
      String plainText = input.nextLine();
      plainText = plainText.toUpperCase();
      int[] o = str2int(plainText);
      for (int i = 0; i < o.length; i++) {
        System.out.print(o[i] + " ");
      }
      System.out.println();
    }
  }

  // Input: plaintext String
  // Encrypt with pseudo cipher:
  // type cast char to int,
  // then subtract value of ascii A (65)
  // if it's a space, print 26
  // Output: int list of numerical values
  static int[] str2int(String plainText){
    int[] output = new int[plainText.length()];
    for (int i = 0; i < plainText.length(); i++){
      char c = plainText.charAt(i);
      if (c == ' ') {
        output[i] = 26;
      } else {
        output[i] = ((int) c - 65);
      }
    }
    return output;
  }
}
