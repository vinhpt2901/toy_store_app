package com.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.model.Product;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM product")
    List<Product> getAll();

    @Query("SELECT * FROM product WHERE sub_cate_id=:subcateID")
    List<Product> getProductBySubCate(int subcateID);

    @Query("SELECT * FROM product WHERE id = :id")
    Product getOne(int id);

    @Query("SELECT * FROM product  ORDER BY id DESC  LIMIT 6")
    List<Product> getNewProduct();

    @Query("SELECT * FROM product  ORDER BY id DESC  LIMIT 3")
    List<Product> getNewProductMain();

    @Query("SELECT DISTINCT product.* FROM product,order_detail WHERE product.id=order_detail.product_id AND order_detail.quantity>=3")
    List<Product> getHotProduct();

    @Query("SELECT DISTINCT product.* FROM product,order_detail WHERE product.id=order_detail.product_id AND order_detail.quantity>=3 LIMIT 4")
    List<Product> getHotProductMain();

    @Query("SELECT * FROM product LIMIT 3")
    List<Product> getAllMain();

    @Query("SELECT * FROM product WHERE product_name like '%' || :productName || '%'")
    List<Product> searchProductByProductName(String productName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long add(Product accountDetail);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);

}
