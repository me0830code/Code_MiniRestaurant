package com.example.minirestaurant.Model ;

// UserInfo Model
public class UserInfo {

    private int uID ;
    private String userName ;
    private int userAge ;
    private String userGender ;

    // Setting uID & userName & userAge & userGender
    public void init(int uID, String name, int age, String gender) {
        this.uID = uID ;
        this.userName = name ;
        this.userAge = age ;
        this.userGender = gender ;
    }

    // Read userID
    public int GetUserID() {
        return this.uID ;
    }

    // Read userName
    public String GetUserName() {
        return this.userName ;
    }

    // Read userAge
    public int GetUserAge() {
        return this.userAge ;
    }

    // Read userGender
    public String GetUserGender() {
        return this.userGender ;
    }
}
