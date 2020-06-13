package com.example.minirestaurant.Model;

// ProductInfo Model
public class ProductInfo {

    private String pID ;
    private String mID ;
    private String productName ;
    private String productPrice ;
    private String productColdOrHot ;

    // Setting pID & mID & productName & productPrice & productColdOrHot
    public void init(String pID, String mID, String name, String price, String ColdOrHot) {
        this.pID = pID ;
        this.mID = mID ;
        this.productName = name ;
        this.productPrice = price ;
        this.productColdOrHot = ColdOrHot ;
    }

    // Read productID
    public String GetProductID() {
        return this.pID ;
    }

    // Read manufacturerID
    public String GetManufacturerID() {
        return this.mID ;
    }

    // Read productName
    public String GetProductName() {
        return this.productName ;
    }

    // Read productPrice
    public String GetProductPrice() {
        return this.productPrice ;
    }

    // Read productColdOrHot
    public String GetProductColdOrHot() {
        return this.productColdOrHot ;
    }
}
