package com.example.models;

public class Account {
    private int UserID;
    private String Username;
    private String Password;
    private boolean SaveInfo;

    public Account() {
    }

    public Account(int userID, String username, String password,boolean saveInfo) {
        UserID = userID;
        Username = username;
        Password = password;
        SaveInfo = saveInfo;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean isSaveInfo() {
        return SaveInfo;
    }

    public void setSaveInfo(boolean saveInfo) {
        SaveInfo = saveInfo;
    }
}
