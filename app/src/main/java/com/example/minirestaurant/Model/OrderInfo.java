package com.example.minirestaurant.Model;

// OrderInfo Model
public class OrderInfo {

    private String oID ;
    private String uID ;
    private String pID ;
    private String productAmount ;
    private String totalPrice ;

    // Setting oID & uID & pID & productAmount & totalPrice
    public void init(String oID, String uID, String pID, String amount, String price) {
        this.oID = oID ;
        this.uID = uID ;
        this.pID = pID ;
        this.productAmount = amount ;
        this.totalPrice = price ;
    }

    // Read orderID
    public String GetOrderID() {
        return this.oID ;
    }

    // Read userID
    public String GetUserID() {
        return this.uID ;
    }

    // Read productID
    public String GetProductID() {
        return this.pID ;
    }

    // Read productAmount
    public String GetProductAmount() {
        return this.productAmount ;
    }

    // Read totalPrice
    public String GetTotalPrice() {
        return this.totalPrice ;
    }
}
