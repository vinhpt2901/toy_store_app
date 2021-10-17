package com.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "image_subcate")
public class ImageSubCate implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int id;

    @ForeignKey(entity = SubCategory.class, parentColumns = {"id"}, childColumns = {"subcate_id"}, onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "subcate_id")
    private int subCateID;

    @ColumnInfo(name = "image_link")
    private String imageLink;

    public ImageSubCate(int id, int subCateID, String imageLink) {
        this.id = id;
        this.subCateID = subCateID;
        this.imageLink = imageLink;
    }

    public ImageSubCate() {
    }

    public ImageSubCate(int subCateID, String imageLink) {
        this.subCateID = subCateID;
        this.imageLink = imageLink;
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Override
    public String toString() {
        return "ImageSubCate{" +
                "id=" + id +
                ", subCateID=" + subCateID +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }
}
