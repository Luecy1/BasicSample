package com.github.luecy1.basicsample.db;

import com.github.luecy1.basicsample.db.entry.CommentEntry;
import com.github.luecy1.basicsample.db.entry.ProductEntry;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by you on 2018/03/12.
 */
public class TestData {

    static final ProductEntry PRODUCT_ENTRY = new ProductEntry(1, "name", "desc", 3);

    static final ProductEntry PRODUCT_ENTRY2 = new ProductEntry(2, "name2", "desc2", 20);

    static final List<ProductEntry> PRODUCTS = Arrays.asList(PRODUCT_ENTRY, PRODUCT_ENTRY2);

    static final CommentEntry COMMENT_ENTRY = new CommentEntry(1, PRODUCT_ENTRY.getId(), "desc",
            new Date());

    static final CommentEntry COMMENT_ENTRY2 = new CommentEntry(2, PRODUCT_ENTRY2.getId(), "desc2", new Date());

    static final List<CommentEntry> COMMENTS = Arrays.asList(COMMENT_ENTRY, COMMENT_ENTRY2);
}
