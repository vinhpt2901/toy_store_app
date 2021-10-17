package com.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "size")
public class Size implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int id;

    @ForeignKey(entity = Product.class, parentColumns = {"id"}, childColumns = {"product_id"}, onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "product_id")

    private int productID;
    @ColumnInfo(name = "size_name")
    private String sizeName;

    public Size(int id, int productID, String sizeName) {
        this.id = id;
        this.productID = productID;
        this.sizeName = sizeName;
    }

    public Size() {
    }

    public Size(int productID, String sizeName) {
        this.productID = productID;
        this.sizeName = sizeName;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    @Override
    public String toString() {
        return "Size{" +
                "id=" + id +
                ", productID=" + productID +
                ", sizeName='" + sizeName + '\'' +
                '}';
    }
}
