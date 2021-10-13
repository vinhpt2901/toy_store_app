package com.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.model.ImageSubCate;

import java.util.List;

@Dao
public interface ImageSubcateDao {
    @Query("SELECT * FROM image_subcate")
    List<ImageSubCate> getAll();

    @Query("SELECT * FROM image_subcate WHERE id = :id")
    ImageSubCate getOne(int id);

    @Query("SELECT image_subcate.image_link FROM category,sub_category,image_subcate where category.id=sub_category.category_id and sub_category.id=image_subcate.subcate_id and category.id=:categoryID")
    List<String> getImageByProductCoverTrue(int categoryID);

    @Insert
    void add(ImageSubCate imageSubCate);

    @Update
    void update(ImageSubCate imageSubCate);

    @Delete
    void delete(ImageSubCate imageSubCate);
}
