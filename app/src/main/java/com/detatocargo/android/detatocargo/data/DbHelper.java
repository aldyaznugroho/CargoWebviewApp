package com.detatocargo.android.detatocargo.data;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.detatocargo.android.detatocargo.data.dao.TrackingHistoryDao;
import com.detatocargo.android.detatocargo.data.model.TrackingHistory;

/**
 * Created by Aldyaz on 8/12/2017.
 */

@Database(entities = {TrackingHistory.class}, version = 4)
public abstract class DbHelper extends RoomDatabase {

    private static DbHelper instance;

    public abstract TrackingHistoryDao trackingHistoryDao();

    public static DbHelper with(Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(context.getApplicationContext(), DbHelper.class, "detatocargo_db")
                    .allowMainThreadQueries()
                    .build();

        }
        return instance;
    }
}
