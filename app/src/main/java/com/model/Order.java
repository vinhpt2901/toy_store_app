package com.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "order")
public class Order  implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int id;

    @ForeignKey(entity = Payment.class, parentColumns = {"id"}, childColumns = {"payment_detail_id"}, onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "payment_detail_id")
    private int paymentDetailID;

    @ForeignKey(entity = StatusOrder.class, parentColumns = {"id"}, childColumns = {"status_order_id"}, onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "status_order_id")
    private int statusOrderID;

    @ForeignKey(entity = CustomInfo.class, parentColumns = {"id"}, childColumns = {"custom_info_id"}, onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "custom_info_id")
    private int customInfoID;

    @ForeignKey(entity = AccountDetail.class, parentColumns = {"id"}, childColumns = {"account_detail_id"}, onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "account_detail_id")
    private int accountDetailID;

    @ColumnInfo(name = "total_price")
    private double totalPrice;

    @ColumnInfo(name = "note")
    private String note;

    @ColumnInfo(name = "create_date", defaultValue = "CURRENT_TIMESTAMP")
    private String createDate;

    public Order(int id, int paymentDetailID, int statusOrderID, int customInfoID, int accountDetailID, double totalPrice, String note, String createDate) {
        this.id = id;
        this.paymentDetailID = paymentDetailID;
        this.statusOrderID = statusOrderID;
        this.customInfoID = customInfoID;
        this.accountDetailID = accountDetailID;
        this.totalPrice = totalPrice;
        this.note = note;
        this.createDate = createDate;
    }

    public Order(int paymentDetailID, int statusOrderID, int customInfoID, int accountDetailID, double totalPrice, String note, String createDate) {
        this.paymentDetailID = paymentDetailID;
        this.statusOrderID = statusOrderID;
        this.customInfoID = customInfoID;
        this.accountDetailID = accountDetailID;
        this.totalPrice = totalPrice;
        this.note = note;
        this.createDate = createDate;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaymentDetailID() {
        return paymentDetailID;
    }

    public void setPaymentDetailID(int paymentDetailID) {
        this.paymentDetailID = paymentDetailID;
    }

    public int getStatusOrderID() {
        return statusOrderID;
    }

    public void setStatusOrderID(int statusOrderID) {
        this.statusOrderID = statusOrderID;
    }

    public int getCustomInfoID() {
        return customInfoID;
    }

    public void setCustomInfoID(int customInfoID) {
        this.customInfoID = customInfoID;
    }

    public int getAccountDetailID() {
        return accountDetailID;
    }

    public void setAccountDetailID(int accountDetailID) {
        this.accountDetailID = accountDetailID;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", paymentDetailID=" + paymentDetailID +
                ", statusOrderID=" + statusOrderID +
                ", customInfoID=" + customInfoID +
                ", accountDetailID=" + accountDetailID +
                ", totalPrice=" + totalPrice +
                ", note='" + note + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
