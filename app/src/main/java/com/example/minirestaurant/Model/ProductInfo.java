package com.example.minirestaurant.Model;

// ProductInfo Model
public class ProductInfo {

    private int pID ;
    private int mID ;
    private String productName ;
    private int productPrice ;
    private String productColdOrHot ;

    // Setting pID & mID & productName & productPrice & productColdOrHot
    public void init(int pID, int mID, String name, int price, String ColdOrHot) {
        this.pID = pID ;
        this.mID = mID ;
        this.productName = name ;
        this.productPrice = price ;
        this.productColdOrHot = ColdOrHot ;
    }

    // Read productID
    public int GetProductID() {
        return this.pID ;
    }

    // Read manufacturerID
    public int GetManufacturerID() {
        return this.mID ;
    }

    // Read productName
    public String GetProductName() {
        return this.productName ;
    }

    // Read productPrice
    public int GetProductPrice() {
        return this.productPrice ;
    }

    // Read productColdOrHot
    public String GetProductColdOrHot() {
        return this.productColdOrHot ;
    }
}
