package com.github.luecy1.basicsample.db.converter;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by you on 2018/03/06.
 */
public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
