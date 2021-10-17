package com.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "image_avatar")
public class ImageAvatar  implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int id;

    @ForeignKey(entity = AccountDetail.class, parentColumns = {"id"}, childColumns = {"account_detail_id"}, onDelete = ForeignKey.CASCADE)
    @ColumnInfo(name = "account_detail_id")
    private int accountDetailID;

    @ColumnInfo(name = "image_link")
    private String imageLink;

    @ColumnInfo(defaultValue = "0")
    private boolean cover;

    public ImageAvatar(int id, int accountDetailID, String imageLink) {
        this.id = id;
        this.accountDetailID = accountDetailID;
        this.imageLink = imageLink;
    }

    public ImageAvatar(int accountDetailID, String imageLink) {
        this.accountDetailID = accountDetailID;
        this.imageLink = imageLink;
    }

    public boolean isCover() {
        return cover;
    }

    public void setCover(boolean cover) {
        this.cover = cover;
    }

    public ImageAvatar(int accountDetailID, String imageLink, boolean cover) {
        this.accountDetailID = accountDetailID;
        this.imageLink = imageLink;
        this.cover = cover;
    }

    public ImageAvatar(int id, int accountDetailID, String imageLink, boolean cover) {
        this.id = id;
        this.accountDetailID = accountDetailID;
        this.imageLink = imageLink;
        this.cover = cover;
    }

    public ImageAvatar() {
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Override
    public String toString() {
        return "ImageAvatar{" +
                "id=" + id +
                ", accountDetailID=" + accountDetailID +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }
}
