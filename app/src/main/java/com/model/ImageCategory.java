package com.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "image_category")
public class ImageCategory  implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int id;

    @ForeignKey(entity = Category.class, parentColumns = {"id"}, childColumns = {"category_id"}, onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "category_id")
    private int categoryID;

    @ColumnInfo(name = "image_link")
    private String imageLink;

    public ImageCategory(int id, int categoryID, String imageLink) {
        this.id = id;
        this.categoryID = categoryID;
        this.imageLink = imageLink;
    }

    public ImageCategory() {
    }

    public ImageCategory(int categoryID, String imageLink) {
        this.categoryID = categoryID;
        this.imageLink = imageLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Override
    public String toString() {
        return "ImageCategory{" +
                "id=" + id +
                ", categoryID=" + categoryID +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }
}
