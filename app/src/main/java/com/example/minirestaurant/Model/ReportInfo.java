package com.example.minirestaurant.Model;

// ReportInfo Model
public class ReportInfo {

    private String cID ;
    private String uID ;
    private String reportDate ;

    // Setting cID & uID & reportDate
    public void init(String cID, String uID, String date) {
        this.cID = cID ;
        this.uID = uID ;
        this.reportDate = date ;
    }

    // Read commentID
    public String GetCommentID() {
        return this.cID ;
    }

    // Read userID
    public String GetUserID() {
        return this.uID ;
    }

    // Read reportDate
    public String GetReportDate() {
        return this.reportDate ;
    }
}
