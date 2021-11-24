package swu.edu.hzd;


import java.util.Dictionary;
import java.util.HashMap;

public class Users {
    private String name;
    private String password;
    private HashMap<String,String> userHashMap;

    public HashMap<String, String> getUserHashMap() {
        userHashMap.put(name,password);
        return userHashMap;
    }

    public void setUserHashMap(HashMap<String, String> userHashMap) {
        this.userHashMap = userHashMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
