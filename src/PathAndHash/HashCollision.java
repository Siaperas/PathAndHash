/*
 * Copyright (C) 2017 Pavlos Siaperas.
 */
package PathAndHash;

import java.io.BufferedWriter;
import java.io.FileWriter;


/**
 *
 * @author Pavlos Siaperas
 */
public class HashCollision {

    public HashCollision() {

    }
    
    //Hash Collision
    public void collision(String salt, int number) {
        //initialize a the variable of the ouput with 10 X as unknowns characters
        String output = "XXXXXXXXXX";
        int i = 1;
        //create a String of zeros with the specified amount already stated
        String zerostring = "";
        for (int j = 0; j < number; j++) {
            zerostring += "0";
        }
        String md5 = "";
        //repeat until all 10 characters are found
        while (output.contains("X")) {
            String calchash = salt + i;
            md5 = MD5(calchash);
            try {
                int character2 = Character.getNumericValue(md5.charAt(number));
                //checks if amount of zeros matches and ignoes invalid positions
                if (md5.substring(0, number).equals(zerostring) && Character.isDigit(md5.charAt(number))) {
                    int position = i % 32;
                    char character = md5.charAt(position);
                    //inserts new character in the desired position
                    if (output.charAt(character2) == 'X') {
                        output = output.substring(0, character2) + character + output.substring(character2 + 1);
                    }
                }
            } catch (Exception exception) {
                System.err.print("Error found: "+exception);
            }
            i++;

        }
        //output the results in the console
        System.out.println("Input: '" + salt + "', " + number + " Output: " + output);
        
        //output the results in a file
        try {
            FileWriter fstream = new FileWriter("hash_collision.txt", true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write("Input: '" + salt + "', " + number + " Output: " + output + "\n");
            out.close();
        } catch (Exception e) {
            System.err.println("Error while writing to file: "
                    + e.getMessage());
        }
    }
    
    //method that returns the MD5 hash
    private String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
