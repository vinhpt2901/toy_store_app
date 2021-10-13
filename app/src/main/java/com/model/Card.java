package com.model;

import java.io.Serializable;

public class Card implements Serializable {
    private int id;
    private int productId;
    private String productName;
    private String imageLink;
    private double sellPrice;
    private double originPrice;
    private String color;
    private int productQuantity;
    private int quantity;
    private double totalPrice;
    private String createDate;

    public Card() {
    }

    public Card(int productId, String productName, String imageLink, double sellPrice, double originPrice, String color, int productQuantity, int quantity, double totalPrice) {
        this.productId = productId;
        this.productName = productName;
        this.imageLink = imageLink;
        this.sellPrice = sellPrice;
        this.originPrice = originPrice;
        this.color = color;
        this.productQuantity = productQuantity;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(double originPrice) {
        this.originPrice = originPrice;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", sellPrice=" + sellPrice +
                ", originPrice=" + originPrice +
                ", color='" + color + '\'' +
                ", productQuantity=" + productQuantity +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
