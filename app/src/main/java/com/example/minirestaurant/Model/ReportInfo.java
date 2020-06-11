package com.example.minirestaurant.Model;

// ReportInfo Model
public class ReportInfo {

    private int rID ;
    private int uID ;
    private int cID ;
    private String reportDate ;

    // Setting rID & uID & cID & reportDate
    public void init(int rID, int uID, int cID, String date) {
        this.rID = rID ;
        this.uID = uID ;
        this.cID = cID ;
        this.reportDate = date ;
    }

    // Read reportID
    public int GetReportID() {
        return this.rID ;
    }

    // Read userID
    public int GetUserID() {
        return this.uID ;
    }

    // Read commentID
    public int GetCommentID() {
        return this.cID ;
    }

    // Read reportDate
    public String GetReportDate() {
        return this.reportDate ;
    }
}
