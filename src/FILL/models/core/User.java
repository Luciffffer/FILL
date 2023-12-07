package FILL.models.core;

import FILL.models.helpers.Cryptography;

public class User {
    private String username;
    private String password;


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
     */
    public User(String username, String password) throws Exception 
    {
        this.setUsername(username);
        this.setPassword(password);
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
    public User setPassword(String password) throws Exception, IllegalArgumentException 
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

    

}
