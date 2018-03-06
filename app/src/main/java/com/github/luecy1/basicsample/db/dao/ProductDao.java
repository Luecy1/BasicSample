package com.github.luecy1.basicsample.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.github.luecy1.basicsample.db.entry.ProductEntry;

import java.util.List;

/**
 * Created by you on 2018/03/07.
 */
@Dao
public interface ProductDao {

    @Query("SELECT * FROM products")
    LiveData<List<ProductEntry>> loadAllProducts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ProductEntry> products);

    @Query("SELECT * FROM products where id = :productId")
    LiveData<ProductEntry> loadProduct(int productId);

    @Query("SELECT * FROM products where id = :productId")
    ProductEntry loadProductSync(int productId);
}
