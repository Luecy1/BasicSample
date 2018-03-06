package com.github.luecy1.basicsample.db.entry;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.github.luecy1.basicsample.model.Product;

/**
 * Created by you on 2018/03/05.
 */
@Entity(tableName = "products")
public class ProductEntry implements Product {

    @PrimaryKey
    private int id;
    private String name;
    private String description;
    private int price;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public ProductEntry() {
    }

    public ProductEntry(int id, String name, String description, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ProductEntry(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }
}
