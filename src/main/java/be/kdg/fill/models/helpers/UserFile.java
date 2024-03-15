package be.kdg.fill.models.helpers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import be.kdg.fill.FillApplication;
import be.kdg.fill.models.core.User;

public class UserFile {

    private File file;
    private ArrayList<String> data;

    /**
     * UserFile
     * constructor
     * @param fileName
     */
    public UserFile(String fileName)
    {
        this.getFile(fileName);
        this.getDataFromFile();
    }

    /**
     * getFile
     * gets the file
     * if the file does not exist, it creates it
     * @param fileName
     * @return void
     * @throws RuntimeException in case of a URISyntaxException for display to the user. Check the error log for more details.
     */
    private void getFile(String fileName) throws RuntimeException
    {
        try {
            
            // gets file URL
            URL url = FillApplication.class.getResource("data/" + fileName);
            
            if (url == null) {
                // creates file
                URL dirUrl = FillApplication.class.getResource("data");
                File dir = new File(dirUrl.toURI());
                this.file = new File(dir, fileName);
            } else {
                // gets file from URL
                this.file = new File(url.toURI());
            } 

        } catch (URISyntaxException e) {

            System.err.println(e);
            ErrorLog error = new ErrorLog(e);
            error.save();
            throw new RuntimeException("Something went wrong loading the user file. Please check the error log.");

        }
    }

    /**
     * getDataFromFile
     * gets a local copy of data from file
     * @return void
     * @throws RuntimeException
     */
    private void getDataFromFile() throws RuntimeException
    {
        try {

            data = new ArrayList<String>();

            if (this.file.length() == 0) {
                return;
            }

            FileInputStream fis = new FileInputStream(this.file);
            DataInputStream dis = new DataInputStream(fis);

            String[] users = dis.readUTF().split("\n");

            for (String user : users) {
                data.add(user);
            }

            dis.close();
            fis.close();

        } catch (IOException e) {

            System.err.println(e);
            ErrorLog error = new ErrorLog(e);
            error.save();
            throw new RuntimeException("Something went wrong, please check the error log.");

        }
    }

    /**
     * addUser
     * adds a user to the file
     * @param user
     * @return void
     */
    public void addUser(User user) 
    {
        String dataString = user.getUsername() + "::" + user.getPassword() + "::" + String.valueOf(user.isAdmin()) + "::" + user.getProgressString();
        data.add(dataString);

        this.save();
    }

    /**
     * updateUser
     * updates the user in the file
     * @param user
     * @return void
     */
    public void updateUser(User user)
    {
        String dataString = user.getUsername() + "::" + user.getPassword() + "::" + String.valueOf(user.isAdmin()) + "::" + user.getProgressString();

        for (int i = 0; i < data.size(); i++) {
            String[] userData = data.get(i).split("::");

            if (userData[0].equals(user.getUsername())) {
                data.set(i, dataString);
                break;
            }
        }

        this.save();
    }

    /**
     * save
     * saves the local copy of data to fil, overwriting anything that was there.
     * @return void
     */
    public void save()
    {
        try {

            FileOutputStream fos = new FileOutputStream(this.file, false);
            DataOutputStream dos = new DataOutputStream(fos);

            String dataString = String.join("\n", data);

            dos.writeUTF(dataString);

            dos.close();
            fos.close();

        } catch (IOException e) {

            System.err.println(e);
            ErrorLog error = new ErrorLog(e);
            error.save();
            throw new RuntimeException("Something went wrong saving the user data. Please check the error log.");

        }
    }

    public String getUserDataByUsername(String username)
    {
        for (String user : this.data) {
            String[] userData = user.split("::");

            if (userData[0].equals(username)) {
                return user;
            }
        }

        return null;
    }
}
