package com.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "evaluate")
public class Evaluate  implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int id;

    @ForeignKey(entity = AccountDetail.class, parentColumns = {"id"}, childColumns = {"account_detail_id"}, onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "account_detail_id")
    private int accountDetailID;

    @ForeignKey(entity = Product.class, parentColumns = {"id"}, childColumns = {"product_id"}, onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "product_id")
    private int productID;

    @ColumnInfo
    private String description;

    @ColumnInfo(defaultValue = "0")
    private int star;

    public Evaluate(int accountDetailID, int productID, String description, int star) {
        this.accountDetailID = accountDetailID;
        this.productID = productID;
        this.description = description;
        this.star = star;
    }

    public Evaluate(int id, int accountDetailID, int productID, String description, int star) {
        this.id = id;
        this.accountDetailID = accountDetailID;
        this.productID = productID;
        this.description = description;
        this.star = star;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountDetailID() {
        return accountDetailID;
    }

    public void setAccountDetailID(int accountDetailID) {
        this.accountDetailID = accountDetailID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public Evaluate() {
    }

    @Override
    public String toString() {
        return "Evaluate{" +
                "id=" + id +
                ", accountDetailID=" + accountDetailID +
                ", productID=" + productID +
                ", description='" + description + '\'' +
                ", star=" + star +
                '}';
    }
}
