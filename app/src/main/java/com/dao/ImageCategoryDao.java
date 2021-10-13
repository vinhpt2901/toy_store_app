package com.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.model.ImageCategory;

import java.util.List;

@Dao
public interface ImageCategoryDao {
    @Query("SELECT * FROM image_category")
    List<ImageCategory> getAll();

    @Query("SELECT * FROM image_category WHERE id = :id")
    ImageCategory getOne(int id);

    @Query("select image_link from image_category,category where image_category.category_id= category.id and category.id=:categoryID")
    String getImageByProductCoverTrue(int categoryID);

    @Query("select image_category.* from image_category,category where image_category.category_id= category.id and category.id=:categoryID")
    ImageCategory getImageByCategory(int categoryID);

    @Insert
    void add(ImageCategory imageCategory);

    @Update
    void update(ImageCategory imageCategory);

    @Delete
    void delete(ImageCategory imageCategory);
}
