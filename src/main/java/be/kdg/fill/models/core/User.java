package be.kdg.fill.models.core;

import java.util.Map;

import be.kdg.fill.models.helpers.Cryptography;
import be.kdg.fill.models.helpers.UserFile;

public class User {
    private String username;
    private String password;
    private Map<World, Level> progress;


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

    /**
     * getPassword
     * returns the password of the user
     * @return String password
     */
    public String getPassword()
    {
        return this.password;
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
        } else if (username.length() < 3 || username.length() > 20) {
            throw new IllegalArgumentException("Username must be between 3 and 20 characters long");
        } else if (UserFile.getUserDataByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists");
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
        } else if (password.length() < 8 || password.length() > 20) {
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
        UserFile.addUser(this);
    }

    /**
     * login
     * logs the user in
     * @param username
     * @param password
     * @return User
     * @throws illegalArgumentException
     */
    public static User login(String username, String password) throws IllegalArgumentException
    {
        String userData = UserFile.getUserDataByUsername(username);

        if (userData == null) {
            throw new IllegalArgumentException("Username or password is incorrect");
        }

        String[] userDataArray = userData.split("::");
        String[] passwordData = userDataArray[1].split(":");

        String hashedPassword = Cryptography.hashStringPBKDF2(password, passwordData[1], Integer.parseInt(passwordData[3]), Integer.parseInt(passwordData[4]));

        if (!hashedPassword.equals(userDataArray[1])) {
            throw new IllegalArgumentException("Username or password is incorrect");
        }

        return new User(userDataArray[0], userDataArray[1], null);
        
    }
}
