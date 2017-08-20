package com.detatocargo.android.detatocargo.others;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Aldyaz on 7/11/2016.
 */
public class DbHelperSqlite extends SQLiteOpenHelper {

    // Versi database
    private static final int DB_VERSION = 1;

    // Nama database
    private static final String DB_NAME = "LocalData";

    // Create Awb Table Query
    public static final String CREATE_AWB_TABLE = "CREATE TABLE "
            + AwbDbComponent.TABLE_AWB + " ("
            + AwbDbComponent.AWB_ID + " INTEGER PRIMARY KEY, "
            + AwbDbComponent.AWB_NUMBER + " TEXT UNIQUE, "
            + AwbDbComponent.AWB_DATE + " DEFAULT CURRENT_TIMESTAMP" + ")";

    // Create Booking Table Query
    public static final String CREATE_BOOKING_TABLE = "CREATE TABLE "
            + BookingDbComponent.TABLE_BOOKING + " ("
            + BookingDbComponent.BOOKING_ID + " INTEGER PRIMARY KEY, "
            + BookingDbComponent.BOOKING_DATE + " DEFAULT CURRENT_TIMESTAMP, "
            + BookingDbComponent.BOOKING_NAME + " TEXT, "
            + BookingDbComponent.BOOKING_EMAIL + " VARCHAR(30), "
            + BookingDbComponent.BOOKING_PHONE + " TEXT, "
            + BookingDbComponent.BOOKING_ADDRESS + " TEXT, "
            + BookingDbComponent.BOOKING_AWB + " TEXT, "
            + BookingDbComponent.BOOKING_COMMODITY + " TEXT, "
            + BookingDbComponent.BOOKING_DESTINATION + " TEXT, "
            + BookingDbComponent.BOOKING_FLIGHT + " TEXT, "
            + BookingDbComponent.BOOKING_PCS + " INTEGER, "
            + BookingDbComponent.BOOKING_WEIGHT + " INTEGER, "
            + BookingDbComponent.BOOKING_DIMENSION + " INTEGER, "
            + BookingDbComponent.BOOKING_SHIPPER + " TEXT, "
            + BookingDbComponent.BOOKING_CONSIGNEE + " TEXT" + ")";

    // Drop Awb Table Query
    public static final String DROP_AWB_TABLE = "DROP TABLE IF EXISTS " + AwbDbComponent.TABLE_AWB;

    // Drop Booking Table Query
    public static final String DROP_BOOKING_TABLE = "DROP TABLE IF EXISTS " + BookingDbComponent.TABLE_BOOKING;

    public DbHelperSqlite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.e("DATABASE OPERATIONS", "Database created ...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Execute Create Awb Table Query
        db.execSQL(CREATE_AWB_TABLE);

        // Execute Create Booking Table Query
        db.execSQL(CREATE_BOOKING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Execute Drop AWB Table Query jika sebelumnya tabel sudah dibuat
        db.execSQL(DROP_AWB_TABLE);

        // Execute Drop BOOKING Table Query jika sebelumnya tabel sudah dibuat
        db.execSQL(DROP_BOOKING_TABLE);

        // Create tabelnya lagi
        onCreate(db);
    }

}
