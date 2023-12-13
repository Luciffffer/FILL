package FILL.models.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import FILL.models.helpers.Cryptography;
import FILL.models.helpers.ErrorLog;

public class User {
    private String username;
    private String password;
    private Map<World, Level> progress;

    private static final String FILENAME = "data/user-data.bin";


    // CONSTRUCTORS

    /**
     * User
     * creates a new user
     */
    public User() 
    {
        this.username = null;
        this.password = null;
    }

    /**
     * User
     * creates a new user
     * @param username
     * @param password
     * @throws Exception
     * @throws IllegalArgumentException
     */
    public User(String username, String password) throws Exception, IllegalArgumentException 
    {
        this.setUsername(username);
        this.setPassword(password);
    }


    /**
     * User
     * creates a new user from inside class
     * @param username
     * @param password
     * @param progress
     */
    private User(String username, String password, Map<World, Level> progress)
    {
        this.username = username;
        this.password = password;
        this.progress = progress;
    }
    

    // GETTERS

    /**
     * getUsername
     * returns the username of the user
     * @return String username
     */
    public String getUsername() 
    {
        return this.username;
    }


    // SETTERS
    
    /**
     * setUsername
     * sets the username of the user
     * @param username
     * @return User
     * @throws IllegalArgumentException
     */
    public User setUsername(String username) throws IllegalArgumentException
    {
        if (username == null) {
            throw new IllegalArgumentException("Username cannot be null");
        } else if (username.length() < 3 && username.length() > 20) {
            throw new IllegalArgumentException("Username must be between 3 and 20 characters long");
        }

        this.username = username;
        return this;
    }

    /**
     * setPassword
     * sets the password of the user
     * @param password
     * @return User
     * @throws Exception
     * @throws IllegalArgumentException
     */
    public User setPassword(String password) throws RuntimeException, IllegalArgumentException 
    {
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        } else if (password.length() < 8 && password.length() > 20) {
            throw new IllegalArgumentException("Password must be between 8 and 20 characters long");
        }

        this.password = Cryptography.hashStringPBKDF2(password, Cryptography.generateSalt());
        return this;
    }


    // METHODS

    /**
     * register
     * registers the user
     */
    public void register() throws RuntimeException
    {
        File file = new File(FILENAME);

        try {

            if (!file.exists()) {
                file.createNewFile();
            }
            
            FileOutputStream fos = new FileOutputStream(file);
            DataOutputStream dos = new DataOutputStream(fos);

            String data = this.username + "::" + this.password + "\n";
            dos.writeUTF(data);

            dos.close();
            fos.close();

        } catch (IOException e) {

            System.err.println(e);
            ErrorLog error = new ErrorLog(e);
            error.save();
            throw new RuntimeException("Something went wrong, please try again later.");

        }
    }

    /**
     * login
     * logs the user in
     * @param username
     * @param password
     * @return User
     * @throws RuntimeException
     */
    public static User login(String username, String password) throws RuntimeException
    {
        User user = getUserByUsername(username);

        if (user.canLogin(username, password)) {
            return user;
        } else {
            throw new RuntimeException("Username or password is incorrect");
        }
    }

    /**
     * canLogin
     * checks if the user can login
     * @param username
     * @param password
     * @return boolean
     */
    public boolean canLogin(String username, String password)
    {
        String[] passwordDetails = this.password.split(":");
        String hashedPassword = Cryptography.hashStringPBKDF2(password, passwordDetails[1], Integer.parseInt(passwordDetails[3]), Integer.parseInt(passwordDetails[4]));

        if (this.username.equals(username) && this.password.equals(hashedPassword)) {
            return true;
        } else {
            return false;
        }
    }

   /**
    * getUserByUsername
    * returns a user by username
    * @param String username
    * @return User
    */
    public static User getUserByUsername(String username)
    {
        File file = new File(FILENAME);

        try (FileInputStream fis = new FileInputStream(file)) {

            DataInputStream dis = new DataInputStream(fis);
            String[] users = dis.readUTF().split("\n");

            for (String user : users) {
                String[] userDetails = user.split("::");

                if (userDetails[0].equals(username)) {
                    return new User(userDetails[0], userDetails[1], Map.of(new World(), new Level()));
                }
            }

            throw new RuntimeException("User not found");

        } catch (IOException e) {

            System.err.println(e);
            ErrorLog error = new ErrorLog(e);
            error.save();
            throw new RuntimeException("Something went wrong, please try again later.");

        }
    }

}
