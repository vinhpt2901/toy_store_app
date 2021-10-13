package com.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.model.CustomInfo;

import java.util.List;

@Dao
public interface CustomInfoDao {
    @Query("SELECT * FROM custom_info")
    List<CustomInfo> getAll();

    @Query("SELECT * FROM custom_info WHERE id = :id")
    CustomInfo getOne(int id);

    @Query("SELECT custom_info.* FROM custom_info,`order` WHERE custom_info.id=`order`.custom_info_id and `order`.id=:orderID")
    CustomInfo getOneByOrderID(int orderID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long add(CustomInfo accountDetail);

    @Update
    void update(CustomInfo customInfo);

    @Delete
    void delete(CustomInfo customInfo);
}
