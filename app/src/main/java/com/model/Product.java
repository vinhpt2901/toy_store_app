package com.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "product")
public class Product implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int id;

    @ForeignKey(entity = SubCategory.class, parentColumns = {"id"}, childColumns = {"sub_cate_id"}, onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "sub_cate_id")
    private int subCateID;

    @ForeignKey(entity = Brand.class, parentColumns = {"id"}, childColumns = {"brand_id"}, onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "brand_id")
    private int brandID;

    @ColumnInfo(name = "product_code")
    private String productCode;

    @ColumnInfo(name = "product_name")
    private String productName;

    @ColumnInfo(defaultValue = "0")
    private int quantity;

    @ColumnInfo(name = "sell_price", defaultValue = "0")
    private double sellPrice;

    @ColumnInfo(name = "origin_price", defaultValue = "0")
    private int originPrice;

    @ColumnInfo
    private String color;

    @ColumnInfo
    private String description;

    @ColumnInfo(name = "create_date", defaultValue = "CURRENT_TIMESTAMP")
    private String createDate;

    public Product() {
    }

    public Product(int subCateID, int brandID, String productCode, String productName, int quantity, double sellPrice, int originPrice, String color, String description, String createDate) {
        this.subCateID = subCateID;
        this.brandID = brandID;
        this.productCode = productCode;
        this.productName = productName;
        this.quantity = quantity;
        this.sellPrice = sellPrice;
        this.originPrice = originPrice;
        this.color = color;
        this.description = description;
        this.createDate = createDate;
    }

    public Product(int id, int subCateID, int brandID, String productCode, String productName, int quantity, double sellPrice, int originPrice, String color, String description, String createDate) {
        this.id = id;
        this.subCateID = subCateID;
        this.brandID = brandID;
        this.productCode = productCode;
        this.productName = productName;
        this.quantity = quantity;
        this.sellPrice = sellPrice;
        this.originPrice = originPrice;
        this.color = color;
        this.description = description;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubCateID() {
        return subCateID;
    }

    public void setSubCateID(int subCateID) {
        this.subCateID = subCateID;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(int originPrice) {
        this.originPrice = originPrice;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", subCateID=" + subCateID +
                ", brandID=" + brandID +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", sellPrice=" + sellPrice +
                ", originPrice=" + originPrice +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
