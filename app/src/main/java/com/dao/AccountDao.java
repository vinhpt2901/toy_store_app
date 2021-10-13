package com.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.model.Account;

import java.util.List;

@Dao
public interface AccountDao {
    @Query("SELECT * FROM account WHERE role_id=2")
    List<Account> getAll();

    @Query("SELECT * FROM account WHERE id = :id")
    Account getOne(int id);

    @Insert
    void add(Account account);

    @Update
    void update(Account account);

    @Delete
    void delete(Account account);

    @Query("SELECT * FROM account WHERE mobile=:mobile and password=:password")
    Account login(String mobile, String password);

    @Query("SELECT * FROM account WHERE account_detail_id = :id")
    Account getAccountByAccountDetail(int id);

    @Query("SELECT * FROM account WHERE mobile=:phone")
    Account getAccountByPhone(String phone);
}
