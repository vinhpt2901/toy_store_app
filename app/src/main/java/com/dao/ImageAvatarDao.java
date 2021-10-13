package com.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.model.ImageAvatar;

import java.util.List;

@Dao
public interface ImageAvatarDao {
    @Query("SELECT * FROM image_avatar")
    List<ImageAvatar> getAll();

    @Query("SELECT * FROM image_avatar WHERE id = :id")
    ImageAvatar getOne(int id);

    @Query("select image_avatar.* from image_avatar,account_detail where image_avatar.account_detail_id=account_detail.id and cover =1 and account_detail.id=:accountDetailID")
    ImageAvatar getOneByAccountDetail(int accountDetailID);

    @Query("select image_avatar.* from image_avatar,account_detail where image_avatar.account_detail_id=account_detail.id and cover =0 and account_detail.id=:accountDetailID")
    ImageAvatar getOneByAccountDetailCoverFalse(int accountDetailID);

    @Insert
    void add(ImageAvatar imageAvatar);

    @Update
    void update(ImageAvatar imageAvatar);

    @Delete
    void delete(ImageAvatar imageAvatar);

    @Query("SELECT * FROM image_avatar WHERE account_detail_id=:accountDetailID")
    List<ImageAvatar> getImageAvatarByAccountDetail(int accountDetailID);
}
