package com.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.model.Image;

import java.util.List;

@Dao
public interface ImageDao {

    @Query("SELECT * FROM image")
    List<Image> getAll();

    @Query("select image_link from image,product where image.product_id= product.id and product.id=:productID and cover=1")
    String getImageByProductCoverTrue(int productID);

    @Query("select image.* from image,product where image.product_id= product.id and product.id=:productID")
    List<Image> getImageByProductCover(int productID);

    @Query("SELECT * FROM image WHERE id = :id")
    Image getOne(int id);

    @Insert
    void add(Image image);

    @Update
    void update(Image image);

    @Delete
    void delete(Image image);
}
