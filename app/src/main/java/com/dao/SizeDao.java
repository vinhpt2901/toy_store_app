package com.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.model.Size;

import java.util.List;

@Dao
public interface SizeDao {
    @Query("SELECT * FROM size")
    List<Size> getAll();

    @Query("SELECT * FROM size WHERE id = :id")
    Size getOne(int id);

    @Insert
    void add(Size size);

    @Update
    void update(Size size);

    @Delete
    void delete(Size size);
}
