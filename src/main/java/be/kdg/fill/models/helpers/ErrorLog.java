package be.kdg.fill.models.helpers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorLog {
    private String message;
    private String stackTrace;
    private LocalDateTime date;


    // CONSTRUCTORS

    /**
     * ErrorLog
     * creates a new error log
     */
    public ErrorLog() 
    {
        this.message = null;
        this.stackTrace = null;
        this.date = null;
    }

    /**
     * ErrorLog
     * creates a new error log
     * @param message
     * @param stackTrace
     * @param date
     */
    public ErrorLog(Exception error)
    {
        this.date = LocalDateTime.now();
        this.message = error.getMessage();
        
        this.setStackTrace(error);
    }


    // GETTERS

    /**
     * getMessage
     * returns the message of the error log
     * @return String message
     */
    public String getMessage()
    {
        return this.message;
    }

    /**
     * getStackTrace
     * returns the stack trace of the error log
     * @return String stackTrace
     */
    public String getStackTrace()
    {
        return this.stackTrace;
    }

    /**
     * getDate
     * returns the date of the error log
     * @return LocalDateTime date
     */
    public LocalDateTime getDate()
    {
        return this.date;
    }

    
    // SETTERS

    /**
     * setMessage
     * sets the message of the error log
     * @param message
     * @return ErrorLog
     */
    public ErrorLog setMessage(String message)
    {
        this.message = message;
        return this;
    }

    /**
     * setStackTrace
     * sets the stack trace of the error log
     * @param stackTrace
     * @return ErrorLog
     */
    public ErrorLog setStackTrace(Exception error)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        error.printStackTrace(pw);
        
        this.stackTrace = sw.toString();
        return this;
    }

    /**
     * setDate
     * @return ErrorLog
     */
    public ErrorLog setDate() {
        this.date = LocalDateTime.now();
        return this;
    }

    /**
     * setDate
     * sets the date of the error log
     * @param date
     * @return ErrorLog
     */
    public ErrorLog setDate(LocalDateTime date)
    {
        this.date = date;
        return this;
    }


    // METHODS

    /**
     * save
     * writes the error log to a file
     */
    public void save() 
    {
        try {
            File errorLog = new File("error.log");
            
            if (!errorLog.exists()) {
                errorLog.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(errorLog, true);

            fileWriter.write(this.toString());
            fileWriter.close();
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    /**
     * toString
     * returns the string representation of the error log
     * @return String
     */
    @Override
    public String toString()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDate = this.date.format(formatter);

        return String.format("%-20s : %s\n  %s", formatDate, this.message, this.stackTrace);
    }
}
