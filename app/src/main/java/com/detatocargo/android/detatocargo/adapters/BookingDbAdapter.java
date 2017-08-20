package com.detatocargo.android.detatocargo.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.detatocargo.android.detatocargo.others.BookingDbComponent;
import com.detatocargo.android.detatocargo.others.DbHelperSqlite;
import com.detatocargo.android.detatocargo.objects.BookingData;
import com.detatocargo.android.detatocargo.objects.UserSessionManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Aldyaz on 7/20/2016.
 */
public class BookingDbAdapter {

    private SQLiteDatabase database;
    private DbHelperSqlite helper;
    private Context context;

    public BookingDbAdapter(Context context) {
        this.context = context;
    }

    public BookingDbAdapter open() throws SQLException {
        helper = new DbHelperSqlite(context);
        database = helper.getWritableDatabase();
        return this;
    }

    public void close() {
        helper.close();
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy/MM/dd HH:mm", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void addBooking(
            String nameBooking,
            String emailBooking,
            String phoneBooking,
            String addressBooking,
            String awbBooking,
            String commodityBooking,
            String destinationBooking,
            String flightBooking,
            String pcsBooking,
            String weightBooking,
            String dimensionBooking,
            String shipperBooking,
            String consigneeBooking
    ) {
        ContentValues values = new ContentValues();

        values.put(BookingDbComponent.BOOKING_DATE, getDateTime()); //1
        values.put(BookingDbComponent.BOOKING_NAME, nameBooking); //2
        values.put(BookingDbComponent.BOOKING_EMAIL, emailBooking); //3
        values.put(BookingDbComponent.BOOKING_PHONE, phoneBooking); //4
        values.put(BookingDbComponent.BOOKING_ADDRESS, addressBooking); //5
        values.put(BookingDbComponent.BOOKING_AWB, awbBooking); //6
        values.put(BookingDbComponent.BOOKING_COMMODITY, commodityBooking); //7
        values.put(BookingDbComponent.BOOKING_DESTINATION, destinationBooking); //8
        values.put(BookingDbComponent.BOOKING_FLIGHT, flightBooking); //9
        values.put(BookingDbComponent.BOOKING_PCS, pcsBooking); //10
        values.put(BookingDbComponent.BOOKING_WEIGHT, weightBooking); //11
        values.put(BookingDbComponent.BOOKING_DIMENSION, dimensionBooking); //12
        values.put(BookingDbComponent.BOOKING_SHIPPER, shipperBooking); //13
        values.put(BookingDbComponent.BOOKING_CONSIGNEE, consigneeBooking); //14

        // Insert ke row
        database.insert(BookingDbComponent.TABLE_BOOKING, null, values);
        Log.e("DATABASE OPERATIONS", "One row inserted ...");
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
    }

    public List<BookingData> getAllBooking(ArrayList<BookingData> bookingList) {
        String selectQuery = "SELECT * FROM "
                + BookingDbComponent.TABLE_BOOKING + " WHERE "
                + BookingDbComponent.BOOKING_EMAIL + " = '" + UserSessionManager.getUsmInstance(context).getUserData().email + "'";

        database = helper.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        try {
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        BookingData bookingData = new BookingData();

                        bookingData.setIdBooking(Integer.parseInt(cursor.getString(0)));
                        bookingData.setDateTimeInputted(cursor.getString(1));
                        bookingData.setNameBooking(cursor.getString(2));
                        bookingData.setEmailBooking(cursor.getString(3));
                        bookingData.setPhoneBooking(cursor.getString(4));
                        bookingData.setAddressBooking(cursor.getString(5));
                        bookingData.setAwbBooking(cursor.getString(6));
                        bookingData.setCommodityBooking(cursor.getString(7));
                        bookingData.setDestinationBooking(cursor.getString(8));
                        bookingData.setFlightBooking(cursor.getString(9));
                        bookingData.setPcsBooking(cursor.getString(10));
                        bookingData.setWeightBooking(cursor.getString(11));
                        bookingData.setDimensionBooking(cursor.getString(12));
                        bookingData.setShipperBooking(cursor.getString(13));
                        bookingData.setConsigneeBooking(cursor.getString(14));

                        bookingList.add(0, bookingData);
                    } while (cursor.moveToNext());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookingList;
    }

    /**
     * Ambil beberapa data Booking
     */
    public Cursor getBookingInfo(String bookingId) {
        // Select Query
        String selectQuery = "SELECT * FROM "
                + BookingDbComponent.TABLE_BOOKING + " WHERE "
                + BookingDbComponent.BOOKING_ID + " = '" + bookingId + "'";
        return database.rawQuery(selectQuery, null);
    }

 }
