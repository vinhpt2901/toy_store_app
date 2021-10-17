package com.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "payment_detail")
public class PaymentDetail implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int id;

    @ForeignKey(entity = Payment.class, parentColumns = {"id"}, childColumns = {"payment_id"}, onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "payment_id")
    private int paymentID;

    @ColumnInfo(name = "payment_detail_name")
    private String paymentDetailName;

    public PaymentDetail(int paymentID, String paymentDetailName) {
        this.paymentID = paymentID;
        this.paymentDetailName = paymentDetailName;
    }

    public PaymentDetail(int id, int paymentID, String paymentDetailName) {
        this.id = id;
        this.paymentID = paymentID;
        this.paymentDetailName = paymentDetailName;
    }

    public PaymentDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public String getPaymentDetailName() {
        return paymentDetailName;
    }

    public void setPaymentDetailName(String paymentDetailName) {
        this.paymentDetailName = paymentDetailName;
    }

    @Override
    public String toString() {
        return "PaymentDetail{" +
                "id=" + id +
                ", paymentID=" + paymentID +
                ", paymentDetailName='" + paymentDetailName + '\'' +
                '}';
    }
}
