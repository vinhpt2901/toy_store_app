package com.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.model.SubCategory;

import java.util.List;

@Dao
public interface SubCategoryDao {
    @Query("SELECT * FROM sub_category")
    List<SubCategory> getAll();

    @Query("SELECT * FROM sub_category WHERE category_id=:categoryID")
    List<SubCategory> getAllByCategory(int categoryID);

    @Query("SELECT * FROM sub_category WHERE id = :id")
    SubCategory getOne(int id);

    @Query("SELECT sub_category.* FROM sub_category,product WHERE sub_category.id=product.sub_cate_id and product.id= :id")
    SubCategory getOneByProduct(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long add(SubCategory accountDetail);

    @Update
    void update(SubCategory subCategory);

    @Delete
    void delete(SubCategory subCategory);
}
