package com.example.minirestaurant.Model;

// OrderInfo Model
public class OrderInfo {

    private int oID ;
    private int uID ;
    private int pID ;
    private int productAmount ;
    private int totalPrice ;

    // Setting oID & uID & pID & productAmount & totalPrice
    public void init(int oID, int uID, int pID, int amount, int price) {
        this.oID = oID ;
        this.uID = uID ;
        this.pID = pID ;
        this.productAmount = amount ;
        this.totalPrice = price ;
    }

    // Read orderID
    public int GerOrderID() {
        return this.oID ;
    }

    // Read userID
    public int GetUserID() {
        return this.uID ;
    }

    // Read productID
    public int GetProductID() {
        return this.pID ;
    }

    // Read productAmount
    public int GetProductAmount() {
        return this.productAmount ;
    }

    // Read totalPrice
    public int GetTotalPrice() {
        return this.totalPrice ;
    }
}
