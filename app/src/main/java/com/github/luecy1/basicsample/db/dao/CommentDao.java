package com.github.luecy1.basicsample.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.github.luecy1.basicsample.db.entry.CommentEntry;

import java.util.List;

/**
 * Created by you on 2018/03/07.
 */
@Dao
public interface CommentDao {

    @Query("SELECT * FROM comments where productId = :productId")
    LiveData<List<CommentEntry>> loadComments(int productId);

    @Query("SELECT * FROM comments WHERE productId = :productId")
    List<CommentEntry> loadCommentsSync(int productId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CommentEntry> comments);
}
