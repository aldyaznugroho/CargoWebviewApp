package com.detatocargo.android.detatocargo.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.detatocargo.android.detatocargo.data.model.TrackingHistory;

import java.util.List;

/**
 * Created by Aldyaz on 8/12/2017.
 */

@Dao
public interface TrackingHistoryDao {

    @Insert
    void insertSingleHistory(TrackingHistory trackingHistory);

    @Query("SELECT * FROM TrackingHistory WHERE airwaybill LIKE :awb")
    List<TrackingHistory> checkExistingAwbCount(String awb);

    @Query("SELECT * FROM TrackingHistory ORDER BY DATETIME(Timestamp) DESC")
    List<TrackingHistory> getAllHistory();

    @Query("DELETE FROM TrackingHistory")
    void deleteAllHistory();

}
