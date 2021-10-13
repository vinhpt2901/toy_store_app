package com.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "status_order")
public class StatusOrder implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int id;

    @ColumnInfo(name = "status_order_name")
    private String statusOrderName;

    public StatusOrder(int id, String statusOrderName) {
        this.id = id;
        this.statusOrderName = statusOrderName;
    }

    public StatusOrder(String statusOrderName) {
        this.statusOrderName = statusOrderName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusOrderName() {
        return statusOrderName;
    }

    public void setStatusOrderName(String statusOrderName) {
        this.statusOrderName = statusOrderName;
    }

    public StatusOrder() {
    }

    @Override
    public String toString() {
        return this.statusOrderName;
    }
}
