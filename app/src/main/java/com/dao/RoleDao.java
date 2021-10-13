package com.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.model.Role;

import java.util.List;

@Dao
public interface RoleDao {
    @Query("SELECT * FROM role")
    List<Role> getAll();

    @Query("SELECT * FROM role WHERE id = :id")
    Role getOne(int id);

    @Query("SELECT role.* FROM role,account WHERE role.id=account.role_id  and account.account_detail_id=:id")
    Role getRoleByAccountDetial(int id);

    @Insert
    void add(Role role);

    @Update
    void update(Role role);

    @Delete
    void delete(Role role);
}
