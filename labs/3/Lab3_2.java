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

    //send one 4x4 at a time to get a hex matrix
    for (int i = 0; i < newP.length() - 1; i++) {
      if (i % 16 == 0 || i == 0) {
        String[][] result = new String[4][4];
        result = getHexMatP(s, newP.toString().substring(i, i + 16));
        //do something with result, but it's already printed
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

    //hexadecimal is two characters, make sure both get added
    for (int i = 0; i < hexP.length() - 1; i += 2) {
      String hex1 = Character.toString(hexP.charAt(i));
      String hex2 = Character.toString(hexP.charAt(i + 1));
      for (int col = 0; col < 4; col++) {
        for (int row = 0; row < 4; row++) {
          cipher[row][col] = hex1.concat(hex2);
          //printing cipher[row][col] gives a lot of dupes?
        }
      }
    }
    return cipher;
  }
}