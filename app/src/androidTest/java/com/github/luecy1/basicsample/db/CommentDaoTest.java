package com.github.luecy1.basicsample.db;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.database.sqlite.SQLiteConstraintException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.github.luecy1.basicsample.LiveDataTestUtil;
import com.github.luecy1.basicsample.db.dao.CommentDao;
import com.github.luecy1.basicsample.db.dao.ProductDao;
import com.github.luecy1.basicsample.db.entry.CommentEntry;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.github.luecy1.basicsample.db.TestData.COMMENTS;
import static com.github.luecy1.basicsample.db.TestData.COMMENT_ENTRY;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Created by you on 2018/03/14.
 */
// TODO
@RunWith(AndroidJUnit4.class)
public class CommentDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private AppDatabase mDatabase;

    private CommentDao mCommentDao;

    private ProductDao mProductDao;

    @Before
    public void initDb() throws Exception {
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                AppDatabase.class)
                .allowMainThreadQueries()
                .build();
        mCommentDao = mDatabase.commentDao();
        mProductDao = mDatabase.productDao();
    }

    @After
    public void closeDb() throws Exception {
        mDatabase.close();
    }

    @Test
    public void getCommentsWhenNoCommentInserted() throws InterruptedException {
        List<CommentEntry> comments = LiveDataTestUtil.getValue(mCommentDao.loadComments
                (COMMENT_ENTRY.getProductId()));

        assertTrue(comments.isEmpty());
    }

    @Test
    public void canInsertCommentWithoutProduct() throws InterruptedException {
        try {
            mCommentDao.insertAll(COMMENTS);
            fail("SQLiteConstraintException expected");
        } catch (SQLiteConstraintException ignored) {

        }
    }

}
