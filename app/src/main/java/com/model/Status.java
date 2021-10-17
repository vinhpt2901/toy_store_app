package com.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "status")
public class Status implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int id;

    @ForeignKey(entity = Product.class, parentColumns = {"id"}, childColumns = {"product_id"}, onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "product_id")
    private int productID;

    @ColumnInfo(name = "description_status", defaultValue = "0")
    private int descriptionStatus;

    @ColumnInfo(name = "status_name")
    private String statusName;

    public Status(int productID, int descriptionStatus, String statusName) {
        this.productID = productID;
        this.descriptionStatus = descriptionStatus;
        this.statusName = statusName;
    }

    public Status(int id, int productID, int descriptionStatus, String statusName) {
        this.id = id;
        this.productID = productID;
        this.descriptionStatus = descriptionStatus;
        this.statusName = statusName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getDescriptionStatus() {
        return descriptionStatus;
    }

    public void setDescriptionStatus(int descriptionStatus) {
        this.descriptionStatus = descriptionStatus;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Status() {
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", productID=" + productID +
                ", descriptionStatus=" + descriptionStatus +
                ", statusName='" + statusName + '\'' +
                '}';
    }
}
