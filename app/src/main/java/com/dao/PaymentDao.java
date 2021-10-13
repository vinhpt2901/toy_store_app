package com.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.model.Payment;

import java.util.List;

@Dao
public interface PaymentDao {
    @Query("SELECT * FROM payment")
    List<Payment> getAll();

    @Query("SELECT * FROM payment WHERE id = :id")
    Payment getOne(int id);

    @Insert
    void add(Payment payment);

    @Update
    void update(Payment payment);

    @Delete
    void delete(Payment payment);
}
