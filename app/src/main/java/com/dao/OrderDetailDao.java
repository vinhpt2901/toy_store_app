package com.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.model.OrderDetail;

import java.util.List;

@Dao
public interface OrderDetailDao {
    @Query("SELECT * FROM order_detail")
    List<OrderDetail> getAll();

    @Query("SELECT * FROM order_detail WHERE id = :id")
    OrderDetail getOne(int id);

    @Insert
    void add(OrderDetail orderDetail);

    @Update
    void update(OrderDetail orderDetail);

    @Delete
    void delete(OrderDetail orderDetail);
}
