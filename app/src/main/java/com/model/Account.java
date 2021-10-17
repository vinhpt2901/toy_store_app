package com.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "account")
public class Account implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int id;

    @ForeignKey(entity = AccountDetail.class, parentColumns = {"id"}, childColumns = {"account_detail_id"}, onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "account_detail_id")
    private int accountDetailID;

    @ForeignKey(entity = Role.class, parentColumns = {"id"}, childColumns = {"role_id"}, onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "role_id", defaultValue = "2")
    private int roleID;

    @ColumnInfo
    private String mobile;

    @ColumnInfo
    private String password;

    public Account() {
    }

    public Account(int accountDetailID, int roleID, String mobile, String password) {
        this.accountDetailID = accountDetailID;
        this.roleID = roleID;
        this.mobile = mobile;
        this.password = password;
    }

    public Account(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public Account(int accountDetailID, String mobile, String password) {
        this.accountDetailID = accountDetailID;
        this.mobile = mobile;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountDetailID() {
        return accountDetailID;
    }

    public void setAccountDetailID(int accountDetailID) {
        this.accountDetailID = accountDetailID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountDetailID=" + accountDetailID +
                ", roleID=" + roleID +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
