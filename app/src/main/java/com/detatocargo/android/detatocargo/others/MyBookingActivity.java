package com.detatocargo.android.detatocargo.others;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.detatocargo.android.detatocargo.R;
import com.detatocargo.android.detatocargo.activities.HomeActivity;
import com.detatocargo.android.detatocargo.adapters.BookingDbAdapter;
import com.detatocargo.android.detatocargo.adapters.BookingListAdapter;
import com.detatocargo.android.detatocargo.objects.BookingData;

import java.util.ArrayList;

public class MyBookingActivity extends AppCompatActivity {

    private TextView emptyTextView;
    private RecyclerView bookingRecyclerView;
    private BookingDbAdapter bookingDbAdapter;
    private BookingListAdapter bookingListAdapter;
    private ArrayList<BookingData> bookingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
    }

    private void init() {
        bookingRecyclerView = (RecyclerView) findViewById(R.id.booking_recycler_view);
        emptyTextView = (TextView) findViewById(R.id.empty_text_booking);

        bookingList = new ArrayList<>();

        bookingDbAdapter = new BookingDbAdapter(getApplicationContext());
        bookingListAdapter = new BookingListAdapter(getApplicationContext(), bookingList);
        bookingListAdapter.notifyDataSetChanged();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false);

        bookingRecyclerView.setLayoutManager(linearLayoutManager);
        bookingRecyclerView.setHasFixedSize(true);
        bookingRecyclerView.setAdapter(bookingListAdapter);

        retrieveAwbFromSQLite();
    }

    private void retrieveAwbFromSQLite() {
        try {
            bookingDbAdapter.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            bookingDbAdapter.getAllBooking(bookingList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!(bookingList.size() < 1)) {
            bookingRecyclerView.setVisibility(View.VISIBLE);
            emptyTextView.setVisibility(View.GONE);
        } else {
            bookingRecyclerView.setVisibility(View.GONE);
            emptyTextView.setVisibility(View.VISIBLE);
        }

        try {
            bookingDbAdapter.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handleBackButton();
    }

    private void handleBackButton() {
        Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
        finish();
    }
}
