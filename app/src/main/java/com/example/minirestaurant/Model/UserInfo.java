package com.example.minirestaurant.Model ;

// UserInfo Model
public class UserInfo {

    private String uID ;
    private String userName ;
    private String userAge ;
    private String userGender ;

    // Setting uID & userName & userAge & userGender
    public void init(String uID, String name, String age, String gender) {
        this.uID = uID ;
        this.userName = name ;
        this.userAge = age ;
        this.userGender = gender ;
    }

    // Read userID
    public String GetUserID() {
        return this.uID ;
    }

    // Read userName
    public String GetUserName() {
        return this.userName ;
    }

    // Read userAge
    public String GetUserAge() {
        return this.userAge ;
    }

    // Read userGender
    public String GetUserGender() {
        return this.userGender ;
    }
}
