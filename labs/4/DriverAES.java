import java.util.Scanner;

/**
 * file: DriverAES.java
 * author: Alex Shah
 * course: MSCS 630
 * assignment: Lab 4
 * due date: 4/4/18
 * version: 1.0
 * <p>
 * Receive an inputted key to generate and print AES round keys
 */

public class DriverAES {
  public static void main(String[] args) {
    //input: some key
    Scanner input = new Scanner(System.in);
    String key = input.next().toUpperCase();
    //print initial key
    System.out.println(key);
    //Get and print 10 generated keys
    String keys = AESCipher.aesRoundKeys(key);
    System.out.println(keys);
  }
}