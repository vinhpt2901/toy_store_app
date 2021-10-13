package com.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.model.Status;

import java.util.List;

@Dao
public interface StatusDao {
    @Query("SELECT * FROM status")
    List<Status> getAll();

    @Query("select status.* from status,product where status.product_id=product.id and product.id=:id")
    Status getOne(int id);

    @Insert
    void add(Status status);

    @Update
    void update(Status status);

    @Delete
    void delete(Status status);
}
