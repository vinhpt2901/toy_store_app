package com.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.model.Category;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("SELECT * FROM category")
    List<Category> getAll();

    @Query("SELECT * FROM category WHERE id = :id")
    Category getOne(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long add(Category accountDetail);

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);

    ;
}
