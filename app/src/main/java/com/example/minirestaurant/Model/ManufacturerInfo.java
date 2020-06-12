package com.example.minirestaurant.Model;

// ManufacturerInfo Model
public class ManufacturerInfo {

    private int mID ;
    private String manufacturerName ;
    private String manufacturerCountry ;
    private int manufacturerPeopleNum ;

    // Setting mID & manufacturerName & manufacturerCountry & manufacturerPeopleNum
    public void init(int mID, String name, String country, int peopleNum) {
        this.mID = mID ;
        this.manufacturerName = name ;
        this.manufacturerCountry = country ;
        this.manufacturerPeopleNum = peopleNum ;
    }

    // Read manufacturerID
    public int GetManufacturerID() {
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
    public int GetManufacturerPeopleNum() {
        return this.manufacturerPeopleNum ;
    }
}
