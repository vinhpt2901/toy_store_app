package com.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "payment")
public class Payment implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int id;

    @ColumnInfo(name = "payment_name")
    private String paymentName;

    public Payment(String paymentName) {
        this.paymentName = paymentName;
    }

    public Payment() {
    }

    public Payment(int id, String paymentName) {
        this.id = id;
        this.paymentName = paymentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", paymentName='" + paymentName + '\'' +
                '}';
    }
}
