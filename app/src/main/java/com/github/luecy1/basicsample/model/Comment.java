package com.github.luecy1.basicsample.model;

import java.util.Date;

/**
 * Created by you on 2018/03/06.
 */
public interface Comment {
    int getId();
    int getProductId();
    String getText();
    Date getPostedAt();
}
