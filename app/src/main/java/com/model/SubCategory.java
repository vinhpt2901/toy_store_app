package com.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "sub_category")
public class SubCategory implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int id;

    @ForeignKey(entity = Category.class, parentColumns = {"id"}, childColumns = {"category_id"}, onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "category_id")
    private int categoryID;

    @ColumnInfo(name = "sub_cate_code")
    private String subCategoryCode;

    @ColumnInfo(name = "sub_cate_name")
    private String subCategoryName;

    public SubCategory(int categoryID, String subCategoryCode, String subCategoryName) {
        this.categoryID = categoryID;
        this.subCategoryCode = subCategoryCode;
        this.subCategoryName = subCategoryName;
    }

    public SubCategory(int id, int categoryID, String subCategoryCode, String subCategoryName) {
        this.id = id;
        this.categoryID = categoryID;
        this.subCategoryCode = subCategoryCode;
        this.subCategoryName = subCategoryName;
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

    public String getSubCategoryCode() {
        return subCategoryCode;
    }

    public void setSubCategoryCode(String subCategoryCode) {
        this.subCategoryCode = subCategoryCode;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public SubCategory() {
    }

    @Override
    public String toString() {
        return subCategoryName;
    }
}
