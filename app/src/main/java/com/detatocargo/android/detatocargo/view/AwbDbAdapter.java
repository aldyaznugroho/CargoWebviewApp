package com.detatocargo.android.detatocargo.view;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.detatocargo.android.detatocargo.others.AwbDbComponent;
import com.detatocargo.android.detatocargo.others.DbHelperSqlite;
import com.detatocargo.android.detatocargo.objects.Awb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Aldyaz on 7/11/2016.
 */
public class AwbDbAdapter {

    private SQLiteDatabase database;
    private DbHelperSqlite awbDbHelperSqlite;
    private Context context;

    public AwbDbAdapter(Context context) {
        this.context = context;
    }

    public AwbDbAdapter open() throws SQLException {
        awbDbHelperSqlite = new DbHelperSqlite(context);
        database = awbDbHelperSqlite.getWritableDatabase();
        return this;
    }

    public void close() {
        awbDbHelperSqlite.close();
    }

    /**
     * Menentukan format tanggal dan waktu
     */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy/MM/dd HH:mm", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * Tambah Awb
     */
    public void addAwb(String awbNumber) {
        ContentValues values = new ContentValues();

        values.put(AwbDbComponent.AWB_NUMBER, awbNumber); // Tambah nomor airwaybil ke database
        values.put(AwbDbComponent.AWB_DATE, getDateTime()); // Tambah tanggal input airwaybil ke database

        // Insert ke row
        database.insert(AwbDbComponent.TABLE_AWB, null, values);
        Log.e("DATABASE OPERATIONS", "One row inserted ...");
    }

    /**
     * Ambil data Awb
     */
    public List<Awb> getAllAirwaybill(ArrayList<Awb> awbList) {
        // Select Query
        String selectQuery = "SELECT * FROM " + AwbDbComponent.TABLE_AWB;
        database = awbDbHelperSqlite.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        try {
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Awb awb = new Awb();
                        awb.setAwbId(Integer.parseInt(cursor.getString(0)));
                        awb.setAwbNumber(cursor.getString(1));
                        awb.setDateTimeInputted(cursor.getString(2));

                        awbList.add(0, awb);
                    } while (cursor.moveToNext());
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return awbList;
    }

    /**
     * Hapus Awb
     */
    public void deleteAwb(Awb awb) {
        database.delete(AwbDbComponent.TABLE_AWB, AwbDbComponent.AWB_ID + " = ?",
                new String[] { String.valueOf(awb.getAwbId()) });
    }

}
