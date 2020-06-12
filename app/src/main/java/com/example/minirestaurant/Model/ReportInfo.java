package com.example.minirestaurant.Model;

// ReportInfo Model
public class ReportInfo {

    private int cID ;
    private int uID ;
    private String reportDate ;

    // Setting cID & uID & reportDate
    public void init(int cID, int uID, String date) {
        this.cID = cID ;
        this.uID = uID ;
        this.reportDate = date ;
    }

    // Read commentID
    public int GetCommentID() {
        return this.cID ;
    }

    // Read userID
    public int GetUserID() {
        return this.uID ;
    }

    // Read reportDate
    public String GetReportDate() {
        return this.reportDate ;
    }
}
