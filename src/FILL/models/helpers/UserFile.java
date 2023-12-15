package FILL.models.helpers;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import FILL.models.core.User;

public abstract class UserFile {

    private static File file;
    private static final String FILENAME = "data/user-data.bin";

    /**
     * getFile
     * gets the file
     * @return File
     * @throws IOException
     */
    private static File getFile() throws IOException
    {
        if (file == null) {
            file = new File(FILENAME);

            if (!file.exists()) {

                file.createNewFile();

            }
        }

        return file;
    }

    /**
     * addUser
     * adds a user to the file
     * @param user
     * @return boolean
     */
    public static void addUser(User user) 
    {
        try {
     
            File file = getFile();

            FileOutputStream fos = new FileOutputStream(file, true);
            DataOutputStream dos = new DataOutputStream(fos);

            String dataString = user.getUsername() + "::" + user.getPassword() + "\n";
            byte[] data = Base64.getDecoder().decode(dataString);

            dos.write(data);

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
     * getData
     * gets the data from the file
     * @return String
     */
    public static String getData()
    {
        try {

            file = getFile();

            return new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(FILENAME)));

        } catch (IOException e) {

            System.err.println(e);
            ErrorLog error = new ErrorLog(e);
            error.save();
            throw new RuntimeException("Something went wrong, please try again later.");

        }
    }
}
