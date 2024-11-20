package model;

import java.util.Date;

public class Cart {
    private int cardID;
    private int userID;
    private Date CreationDate;
    private String status;

    public Cart() {
    }

    public Cart(int cardID, int userID, Date CreationDate, String status) {
        this.cardID = cardID;
        this.userID = userID;
        this.CreationDate = CreationDate;
        this.status = status;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date CreationDate) {
        this.CreationDate = CreationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
