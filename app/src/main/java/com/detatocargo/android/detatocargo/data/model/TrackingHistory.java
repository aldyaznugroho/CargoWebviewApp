package com.detatocargo.android.detatocargo.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Aldyaz on 8/12/2017.
 */

@Entity(tableName = "TrackingHistory")
public class TrackingHistory {

    @PrimaryKey(autoGenerate = true)
    private int trackingHistoryId;

    @ColumnInfo(name = "AirwaysName")
    private String airwaysName;

    @ColumnInfo(name = "Airwaybill")
    private String airwaybill;

    @ColumnInfo(name = "AirwaysColor")
    private Integer airwaysColor;

    @ColumnInfo(name = "Timestamp")
    private String timestamp;

    public int getTrackingHistoryId() {
        return trackingHistoryId;
    }

    public void setTrackingHistoryId(int trackingHistoryId) {
        this.trackingHistoryId = trackingHistoryId;
    }

    public String getAirwaybill() {
        return airwaybill;
    }

    public void setAirwaybill(String airwaybill) {
        this.airwaybill = airwaybill;
    }

    public String getAirwaysName() {
        return airwaysName;
    }

    public void setAirwaysName(String airwaysName) {
        this.airwaysName = airwaysName;
    }

    public Integer getAirwaysColor() {
        return airwaysColor;
    }

    public void setAirwaysColor(Integer airwaysColor) {
        this.airwaysColor = airwaysColor;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
