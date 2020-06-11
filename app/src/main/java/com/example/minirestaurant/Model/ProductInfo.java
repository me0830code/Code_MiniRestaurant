package com.example.minirestaurant.Model;

// ProductInfo Model
public class ProductInfo {

    private int pID ;
    private String productName ;
    private int productPrice ;
    private String productColdOrHot ;

    // Setting pID & productName & productPrice & productColdOrHot
    public void init(int id, String name, int price, String ColdOrHot) {
        this.pID = id ;
        this.productName = name ;
        this.productPrice = price ;
        this.productColdOrHot = ColdOrHot ;
    }

    // Read productID
    public int GetProductID() {
        return this.pID ;
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
