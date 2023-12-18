package FILL.models.helpers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import FILL.models.core.User;

public abstract class UserFile {

    private static File file;
    private static final String FILENAME = "data/user-data.bin";
    private static ArrayList<String> data;

    /**
     * static constructor
     */
    static 
    {
        getFile();
        getDataFromFile();
    }

    /**
     * getFile
     * gets the file
     * @return void
     * @throws RuntimeException
     */
    private static void getFile() throws RuntimeException
    {
        try {

            file = new File(FILENAME);

            if (!file.exists()) {

                file.createNewFile();

            }

        } catch (IOException e) {

            System.err.println(e);
            ErrorLog error = new ErrorLog(e);
            error.save();
            throw new RuntimeException("Something went wrong, please try again later.");

        }
    }

    /**
     * addUser
     * adds a user to the file
     * @param user
     * @return boolean
     */
    public static void addUser(User user) 
    {
        String dataString = user.getUsername() + "::" + user.getPassword() + "\n";
        data.add(dataString);

        save();
    }

    /**
     * save
     * saves the data to the file
     * @return void
     */
    public static void save()
    {
        try {

            FileOutputStream fos = new FileOutputStream(file, false);
            DataOutputStream dos = new DataOutputStream(fos);

            String dataString = String.join("\n", data);

            dos.writeUTF(dataString);

            dos.close();
            fos.close();

        } catch (IOException e) {

            System.err.println(e);
            ErrorLog error = new ErrorLog(e);
            error.save();
            throw new RuntimeException("Something went wrong, please try again later.");

        }
    }

    public static String getUserDataByUsername(String username)
    {
        for (String user : data) {
            String[] userData = user.split("::");

            if (userData[0].equals(username)) {
                return user;
            }
        }

        return null;
    }

    /**
     * getData
     * gets the data from the file
     * @return void
     */
    private static void getDataFromFile()
    {
        try {

            data = new ArrayList<String>();

            if (file.length() == 0) {
                return;
            }

            FileInputStream fis = new FileInputStream(file);
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
            throw new RuntimeException("Something went wrong, please try again later.");

        }
    }
}
