package FILL.models.helpers;

import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Cryptography {

    // STATIC METHODS

    /**
     * hashString
     * hashes a string using PBKDF2WithHmacSHA1
     * @param string
     * @param salt
     * @return String hashedString
     * @throws Exception
     */
    public static String hashStringPBKDF2(String content, String salt) throws Exception 
    {
        try {

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(content.toCharArray(), salt.getBytes(), 65536, 128);

            byte[] hash = factory.generateSecret(spec).getEncoded();

            return hash.toString() + ":" + salt.toString() + ":PBDKF2WithHmacSHA1:" + 65536 + ":" + 128;

        } catch (Exception e) {

            System.err.println(e);

            ErrorLog error = new ErrorLog(e);
            error.save();

            throw new Exception("Something went wrong, please try again later.");
            
        }
    }

    /**
     * generateSalt
     * generates a salt for hashing
     * @return String salt
     */
    public static String generateSalt() 
    {
        SecureRandom random = new SecureRandom();

        byte[] salt = new byte[16];
        random.nextBytes(salt);

        return salt.toString();
    }
}
