package com.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "custom_info")
public class CustomInfo  implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int id;

    @ColumnInfo(name = "custom_name")
    private String customerName;

    @ColumnInfo
    private String mobile;

    @ColumnInfo
    private String address;

    public CustomInfo(int id, String customerName, String mobile, String address) {
        this.id = id;
        this.customerName = customerName;
        this.mobile = mobile;
        this.address = address;
    }

    public CustomInfo(String customerName, String mobile, String address) {
        this.customerName = customerName;
        this.mobile = mobile;
        this.address = address;
    }

    public CustomInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CustomInfo{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
