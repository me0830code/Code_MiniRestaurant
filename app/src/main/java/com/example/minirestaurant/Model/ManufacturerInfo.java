package com.example.minirestaurant.Model;

// ManufacturerInfo Model
public class ManufacturerInfo {

    private String mID ;
    private String manufacturerName ;
    private String manufacturerCountry ;
    private String manufacturerPeopleNum ;

    // Setting mID & manufacturerName & manufacturerCountry & manufacturerPeopleNum
    public void init(String mID, String name, String country, String peopleNum) {
        this.mID = mID ;
        this.manufacturerName = name ;
        this.manufacturerCountry = country ;
        this.manufacturerPeopleNum = peopleNum ;
    }

    // Read manufacturerID
    public String GetManufacturerID() {
        return this.mID ;
    }

    // Read manufacturerName
    public String GetManufacturerName() {
        return this.manufacturerName ;
    }

    // Read manufacturerCountry
    public String GetManufacturerCountry() {
        return this.manufacturerCountry ;
    }

    // Read manufacturerPeopleNum
    public String GetManufacturerPeopleNum() {
        return this.manufacturerPeopleNum ;
    }
}
