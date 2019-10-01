package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class min5aDataHandler {
    private User user;
    private static min5aDataHandler instance = null;

    protected min5aDataHandler(){}

    public static min5aDataHandler getInstance(){
        if (instance == null){
            instance = new min5aDataHandler();
            instance.init();

        }
        return instance;
    }

    private void init() {
        this.user = new User();
        //this.loadUser();
    }



    // Used to load the previous User
/*    private void loadUser() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.userFile()), "ISO-8859-1"));
            System.out.println("loadUser, starting...");
            String line = reader.readLine();
            if (line != null) {
                this.user.setUserName(line);
                line = reader.readLine();
                if (line != null) {
                    this.user.setPassword(line);
                }
            }

            reader.close();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    private String userFile() {
    }*/


}