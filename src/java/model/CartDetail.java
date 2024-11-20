/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class CartDetail {
    private int CartdetailID;
    private int userID;
    private int productId;
    private String productName;
    private String productImage;
    private double price;
    private int quantity;
    private int cartID;

    public CartDetail( int CartdetailID ,int userID, int productId, String productName, String productImage, double price, int quantity) {
        this.CartdetailID = CartdetailID;
        this.userID = userID;
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.price = price;
        this.quantity = quantity;
    }

    public CartDetail(int userID, int productId, String productName, String productImage, double price, int quantity, int cartID) {
        this.userID = userID;
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.price = price;
        this.quantity = quantity;
        this.cartID = cartID;
    }
    
    public CartDetail(int userID, int productId, String productName, String productImage, double price, int quantity) {
        this.userID = userID;
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.price = price;
        this.quantity = quantity;
    }

    public int getCartdetailID() {
        return CartdetailID;
    }

    public void setCartdetailID(int CartdetailID) {
        this.CartdetailID = CartdetailID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }
    
}
