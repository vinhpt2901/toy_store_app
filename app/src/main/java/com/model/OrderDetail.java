package com.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "order_detail")
public class OrderDetail implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int id;

    @ForeignKey(entity = Order.class, parentColumns = {"id"}, childColumns = {"order_id"}, onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "order_id")
    private int orderID;

    @ForeignKey(entity = Product.class, parentColumns = {"id"}, childColumns = {"product_id"}, onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "product_id")
    private int productID;

    @ColumnInfo(name = "product_name")
    private String productName;

    @ColumnInfo(name = "origin_price", defaultValue = "0")
    private double originPrice;

    @ColumnInfo(name = "sell_price", defaultValue = "0")
    private double sellPrice;

    @ColumnInfo(defaultValue = "0")
    private int quantity;

    @ColumnInfo(name = "create_date", defaultValue = "CURRENT_TIMESTAMP")
    private String createDate;

    @ColumnInfo(name = "ship_date")
    @NonNull
    private String shipDate;

    public OrderDetail(int id, int orderID, int productID, String productName, double originPrice, double sellPrice, int quantity, String createDate, String shipDate) {
        this.id = id;
        this.orderID = orderID;
        this.productID = productID;
        this.productName = productName;
        this.originPrice = originPrice;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
        this.createDate = createDate;
        this.shipDate = shipDate;
    }

    public OrderDetail(int orderID, int productID, String productName, double originPrice, double sellPrice, int quantity, String createDate, String shipDate) {
        this.orderID = orderID;
        this.productID = productID;
        this.productName = productName;
        this.originPrice = originPrice;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
        this.createDate = createDate;
        this.shipDate = shipDate;
    }

    public OrderDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(double originPrice) {
        this.originPrice = originPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", orderID=" + orderID +
                ", productID=" + productID +
                ", productName='" + productName + '\'' +
                ", originPrice=" + originPrice +
                ", sellPrice=" + sellPrice +
                ", quantity=" + quantity +
                ", createDate='" + createDate + '\'' +
                ", shipDate='" + shipDate + '\'' +
                '}';
    }
}
