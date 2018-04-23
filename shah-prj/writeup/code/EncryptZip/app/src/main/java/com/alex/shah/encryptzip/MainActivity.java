package com.alex.shah.encryptzip;
/**
 * file: MainActivity.java
 * author: Alex Shah
 * course: MSCS 630
 * assignment: Project - EncryptZip
 * due date: 5/4/18
 * version: 1.0
 * Android app to zip a data folder
 * and encrypt the resulting zip
 * with a password. Zip can be
 * decrypted with the same password
 * then unzipped.
 */

import android.os.Environment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;

public class MainActivity extends AppCompatActivity {
    private String SDPath = Environment.getExternalStorageDirectory().getAbsolutePath();
    private String dataPath = SDPath + "/encryptzip/data/";
    private String zipPath = SDPath + "/encryptzip/zip/";
    final static String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // test file
        File file = new File(dataPath, "test.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(dataPath + "test.txt", "UTF-8");
            writer.println("Super Secret Stuff");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // identify password field to extract password
        final EditText passwordField = (EditText) findViewById(R.id.passwordField);

        // UNZIP BUTTON
        Button unzipbut = (Button) findViewById(R.id.unzipbut);
        unzipbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Zipper.unzip(zipPath + "container.zip", dataPath)) {
                    Toast.makeText(MainActivity.this, "Unzipped successfully.", Toast.LENGTH_LONG).show();
                }
            }
        });

        // ZIP BUTTON
        Button zipbut = (Button) findViewById(R.id.zipbut);
        zipbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Zipper.zip(dataPath, zipPath, "container.zip", false)) {
                    Toast.makeText(MainActivity.this, "Zipped successfully.", Toast.LENGTH_LONG).show();
                }
            }
        });

        // ENCRYPT BUTTON
        Button encbut = (Button) findViewById(R.id.encbut);
        encbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] data = new byte[0];
                try {
                    // Read in zip and password field
                    data = Files.readAllBytes(new File(zipPath + "container.zip").toPath());
                    String currentPass = passwordField.getText().toString();

                    // Generate a key from password, encrypt zip with it
                    SecretKey key = generateKey(currentPass.toCharArray());
                    byte[] cipherzip = encrypt(key, data);

                    // Write out encrypted zip
                    FileOutputStream stream = new FileOutputStream(zipPath + "container.zip");

                    try {
                        stream.write(cipherzip);
                    } finally {
                        stream.flush();
                        stream.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    // Exception prints failure
                    Toast.makeText(MainActivity.this, "Encryption Failed!", Toast.LENGTH_LONG).show();
                }
            }
        });

        // DECRYPT BUTTON
        Button decbut = (Button) findViewById(R.id.decbut);
        decbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] data = new byte[0];
                try {
                    // Read in zip and password field
                    data = Files.readAllBytes(new File(zipPath + "container.zip").toPath());
                    String currentPass = passwordField.getText().toString();

                    // Generate a key from password, decrypt zip with it
                    SecretKey key = generateKey(currentPass.toCharArray());
                    byte[] plainzip = decrypt(key, data);

                    // Write out encrypted zip
                    FileOutputStream stream = new FileOutputStream(zipPath + "container.zip");

                    try {
                        stream.write(plainzip);
                    } finally {
                        stream.flush();
                        stream.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    // Exception prints failure
                    Toast.makeText(MainActivity.this, "Decrypted Failed!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // generate key from password using AES
    public static SecretKey generateKey(char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        final int iterations = 1000;

        // Generate a 256-bit key with SHA256
        final int outputKeyLength = 256;
        byte[] salt = new byte[20];

        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2withHmacSHA256");
        KeySpec keySpec = new PBEKeySpec(password, salt, iterations, outputKeyLength);
        SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
        return secretKey;
    }

    // encrypt with AES
    public static byte[] encrypt(SecretKey key, byte[] fileData) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(fileData);
        return encrypted;
    }

    // decrypt with AES
    public static byte[] decrypt(SecretKey key, byte[] fileData) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(fileData);
        return decrypted;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}