package com.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "image")
public class Image  implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int id;

    @ForeignKey(entity = Product.class, parentColumns = {"id"}, childColumns = {"product_id"}, onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "product_id")
    private int productID;

    @ColumnInfo(name = "image_link")
    private String imageLink;

    @ColumnInfo(defaultValue = "0")
    private boolean cover;

    public Image(int id, int productID, String imageLink, boolean cover) {
        this.id = id;
        this.productID = productID;
        this.imageLink = imageLink;
        this.cover = cover;
    }

    public Image(int productID, String imageLink, boolean cover) {
        this.productID = productID;
        this.imageLink = imageLink;
        this.cover = cover;
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public boolean isCover() {
        return cover;
    }

    public void setCover(boolean cover) {
        this.cover = cover;
    }

    public Image() {
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", productID=" + productID +
                ", imageLink='" + imageLink + '\'' +
                ", cover=" + cover +
                '}';
    }
}
