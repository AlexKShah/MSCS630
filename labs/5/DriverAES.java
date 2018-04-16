import java.util.Scanner;

/**
 * file: DriverAES.java
 * author: Alex Shah
 * course: MSCS 630
 * assignment: Lab 5
 * due date: 4/18/18
 * version: 1.0
 * <p>
 * Receive an inputted key to generate and use AES round keys
 * to encrypt entered plaintext with AES
 */

public class DriverAES {
  public static void main(String[] args) {
    //input: some key
    Scanner input = new Scanner(System.in);
    String key = input.next().toUpperCase();
    String plaintext = input.next().toUpperCase();

    System.out.println("Initial key: \n" + key + "\n");
    System.out.println("Plaintext: \n" + plaintext + "\n");

    //Generate keys
    String keys = AESCipher.aesRoundKeys(key);
    //Encrypt
    String ciphertext = AESCipher.AES(key, plaintext);

    //Print keys
    System.out.println("Round keys: \n");
    System.out.println(keys);
    //Print result
    System.out.println("Ciphertext: \n");
    System.out.println(ciphertext);
  }
}