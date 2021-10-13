package com.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.model.Evaluate;

import java.util.List;

@Dao
public interface EvaluateDao {
    @Query("SELECT * FROM evaluate")
    List<Evaluate> getAll();

    @Query("SELECT * FROM evaluate WHERE id = :id")
    Evaluate getOne(int id);

    @Insert
    void add(Evaluate evaluate);

    @Update
    void update(Evaluate evaluate);

    @Delete
    void delete(Evaluate evaluate);
}
