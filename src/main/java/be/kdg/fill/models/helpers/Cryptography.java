package be.kdg.fill.models.helpers;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/*
* You might ask yourself why does this class exist when anyone can just change the data in user-data.bin?
* The answer is simply that I was bored and wanted to try out hashing and salting in Java.
* - xxx Tim
*/

public abstract class Cryptography {
    
    // STATIC METHODS

    /**
     * hashString
     * hashes a string using PBKDF2WithHmacSHA1
     * @param String content
     * @param byte[] salt
     * @return String hashedString
     * @throws runtimeException
     */
    public static String hashStringPBKDF2(String content, byte[] salt) throws RuntimeException 
    {
        return hashStringPBKDF2(content, salt, 65536, 128);
    }

    /**
     * hashString
     * hashes a string using PBKDF2WithHmacSHA1
     * @param string content
     * @param string salt
     * @param int iterationCount
     * @param int keyLength
     * @return String hashedString
     * @throws RuntimeException
     */
    public static String hashStringPBKDF2(String content, String salt, int iterationCount, int keyLength) throws RuntimeException
    {
        return hashStringPBKDF2(content, Base64.getDecoder().decode(salt), iterationCount, keyLength);
    }

    /**
     * hashStringPBKDF2
     * hashes a string using PBKDF2WithHmacSHA1
     * @param String content
     * @param byte[] salt
     * @param int iterationCount
     * @param int keyLength
     * @return String hashedString
     * @throws RuntimeException
     */
    public static String hashStringPBKDF2(String content, byte[] salt, int iterationCount, int keyLength) throws RuntimeException 
    {
        try {
            KeySpec spec = new PBEKeySpec(content.toCharArray(), salt, iterationCount, keyLength);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            byte[] hash = factory.generateSecret(spec).getEncoded();

            return Base64.getEncoder().encodeToString(hash) + ":" + Base64.getEncoder().encodeToString(salt) + ":PBDKF2WithHmacSHA1:" + iterationCount + ":" + keyLength;

        } catch (Exception e) {

            System.err.println(e);

            ErrorLog error = new ErrorLog(e);
            error.save();
            
            throw new RuntimeException("Something went wrong, please try again later.");
            
        }
    }

    /**
     * generateSalt
     * generates a salt for hashing
     * @return byte[] salt
     */
    public static byte[] generateSalt() 
    {
        SecureRandom random = new SecureRandom();

        byte[] salt = new byte[16];
        random.nextBytes(salt);

        return salt;
    }
}
