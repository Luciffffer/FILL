package be.kdg.fill.models.core;

import java.util.HashMap;

import be.kdg.fill.models.helpers.Cryptography;
import be.kdg.fill.models.helpers.UserFile;

public class User {
    private String username;
    private String password;
    private HashMap<Integer, Integer> progress;
    private boolean isAdmin;
    private UserFile userFile;


    // CONSTRUCTORS

    /**
     * User
     * creates a new user
     * @param userFile
     */
    public User(UserFile userFile) 
    {
        this.username = null;
        this.password = null;
        this.progress = new HashMap<Integer, Integer>();
        this.isAdmin = false;
        this.userFile = userFile;
    }
    

    // GETTERS

    /**
     * getUsername
     * returns the username of the user
     * @return String
     */
    public String getUsername() 
    {
        return this.username;
    }

    /**
     * getPassword
     * returns the password of the user
     * @return String
     */
    public String getPassword()
    {
        return this.password;
    }

    /**
     * getProgress
     * returns the progress of the user. Formated as a string
     * @return String
     */
    public String getProgressString()
    {
        String progressString = "";

        for (int world: this.progress.keySet()) {
            progressString += world + "," + this.progress.get(world) + ":";
        }

        return progressString;
    }

    /**
     * getWorldProgress
     * returns the user's progress in a specific world
     * @param world
     * @return int
     */
    public int getWorldProgress(int world)
    {
        if (this.progress.containsKey(world)) {
            return this.progress.get(world);
        } else {
            return 0;
        }
    }

    /**
     * isAdmin
     * returns whether the user is an admin
     * @return boolean
     */
    public boolean isAdmin()
    {
        return this.isAdmin;
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
        } else if (username.matches(".*\\s.*")) {
            throw new IllegalArgumentException("Username cannot contain spaces");
        } else if (this.userFile.getUserDataByUsername(username) != null) {
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

    public User setAdmin(boolean isAdmin)
    {
        this.isAdmin = isAdmin;
        return this;
    }

    /**
     * setWorldProgress
     * sets the progress of the user
     * @param world
     * @param progress
     * @return User
     */
    public User setWorldProgress(int world, int progress) 
    {
        this.progress.put(world, progress);
        return this;
    }


    // METHODS

    /**
     * register
     * registers the user
     */
    public void register() throws RuntimeException
    {
        this.userFile.addUser(this);
    }

    /**
     * save
     * saves the user to the file
     * @return void
     */
    public void save()
    {
        this.userFile.updateUser(this);
    }

    /**
     * resetProgress
     * resets the progress of the user
     * @return void
     */
    public void resetProgress()
    {
        this.progress = new HashMap<Integer, Integer>();
    }

    /**
     * login
     * logs the user in
     * @param username
     * @param password
     * @return User
     * @throws illegalArgumentException
     */
    public void login(String username, String password) throws IllegalArgumentException
    {
        String userData = this.userFile.getUserDataByUsername(username);

        if (userData == null) {
            throw new IllegalArgumentException("Username or password is incorrect");
        }

        String[] userDataArray = userData.split("::");
        String[] passwordData = userDataArray[1].split(":");
        boolean isAdmin = userDataArray[2].equals("true") ? true : false;
        String[] progressData = userDataArray.length > 3 ? userDataArray[2].split(":") : new String[]{};

        String hashedPassword = Cryptography.hashStringPBKDF2(password, passwordData[1], Integer.parseInt(passwordData[3]), Integer.parseInt(passwordData[4]));

        if (!hashedPassword.equals(userDataArray[1])) {
            throw new IllegalArgumentException("Username or password is incorrect");
        }

        this.username = userDataArray[0];
        this.password = userDataArray[1];
        this.isAdmin = isAdmin;
        this.progress = new HashMap<Integer, Integer>();
        
        for (String worldProgress: progressData) {
            String[] worldProgressArray = worldProgress.split(",");
            progress.put(Integer.parseInt(worldProgressArray[0]), Integer.parseInt(worldProgressArray[1]));
        }
    }
}
