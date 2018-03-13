package com.github.luecy1.basicsample.db;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.github.luecy1.basicsample.LiveDataTestUtil;
import com.github.luecy1.basicsample.db.dao.ProductDao;
import com.github.luecy1.basicsample.db.entry.ProductEntry;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.github.luecy1.basicsample.db.TestData.PRODUCTS;
import static com.github.luecy1.basicsample.db.TestData.PRODUCT_ENTRY;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by you on 2018/03/13.
 */
@RunWith(AndroidJUnit4.class)
public class ProductDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private AppDatabase mDatabase;

    private ProductDao mProductDao;

    @Before
    public void initDb() throws Exception {
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                AppDatabase.class)
                .allowMainThreadQueries()
                .build();

        mProductDao = mDatabase.productDao();
    }

    @After
    public void closeDb() throws Exception {
        mDatabase.close();
    }

    @Test
    public void getProDuctsWhenNoProductInserted() throws InterruptedException {
        List<ProductEntry> products = LiveDataTestUtil.getValue(mProductDao.loadAllProducts());

        assertTrue(products.isEmpty());

    }

    @Test
    public void getProductsAfterInserted() throws InterruptedException {
        mProductDao.insertAll(PRODUCTS);

        List<ProductEntry> products = LiveDataTestUtil.getValue(mProductDao.loadAllProducts());

        assertThat(products.size(), is(PRODUCTS.size()));
    }

    @Test
    public void getProductById() throws InterruptedException {
        mProductDao.insertAll(PRODUCTS);

        ProductEntry product = LiveDataTestUtil.getValue(mProductDao.loadProduct(PRODUCT_ENTRY.getId()));

        assertThat(product.getId(), is(PRODUCT_ENTRY.getId()));
        assertThat(product.getName(), is(PRODUCT_ENTRY.getName()));
        assertThat(product.getDescription(), is(PRODUCT_ENTRY.getDescription()));
        assertThat(product.getPrice(), is(PRODUCT_ENTRY.getPrice()));
    }
}
