package com.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.model.PaymentDetail;

import java.util.List;

@Dao
public interface PaymentDetailDao {
    @Query("SELECT * FROM payment_detail")
    List<PaymentDetail> getAll();

    @Query("SELECT * FROM payment_detail WHERE id = :id")
    PaymentDetail getOne(int id);

    @Insert
    void add(PaymentDetail paymentDetail);

    @Update
    void update(PaymentDetail paymentDetail);

    @Delete
    void delete(PaymentDetail paymentDetail);
}
