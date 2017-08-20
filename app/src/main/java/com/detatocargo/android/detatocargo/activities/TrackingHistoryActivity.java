package com.detatocargo.android.detatocargo.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.detatocargo.android.detatocargo.R;
import com.detatocargo.android.detatocargo.data.DbHelper;
import com.detatocargo.android.detatocargo.data.model.TrackingHistory;
import com.detatocargo.android.detatocargo.view.CustomRecyclerAdapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import timber.log.Timber;

public class TrackingHistoryActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView noDataText;
    private RecyclerView recyclerView;

    private CustomRecyclerAdapter<TrackingHistory> historyAdapter;
    private List<TrackingHistory> historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_history);
        toolbar = (Toolbar) findViewById(R.id.tracking_history_toolbar);
        noDataText = (TextView) findViewById(R.id.history_no_data_text);
        recyclerView = (RecyclerView) findViewById(R.id.history_recycler_view);
        setSupportActionBar(toolbar);
        init();
        setTitle("History Tracking");
    }

    private void init() {
        initToolbar();
        initRecyclerView();
        getHistoryData();
    }

    private void initToolbar() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Drawable navigationIcon = toolbar.getNavigationIcon();
        if (navigationIcon != null) {
            navigationIcon.mutate();
            navigationIcon.setColorFilter(
                    ContextCompat.getColor(this, R.color.color_white),
                    PorterDuff.Mode.SRC_ATOP);
        }
    }

    private void initRecyclerView() {
        historyAdapter = new CustomRecyclerAdapter<>(this, R.layout.item_history_holder, new CustomRecyclerAdapter.ViewHolderBinder<TrackingHistory>() {
            @Override
            public void bind(CustomRecyclerAdapter.CustomViewHolder<TrackingHistory> holder, final TrackingHistory item) {
                CardView historyCard = (CardView) holder.itemView.findViewById(R.id.history_item_card);
                TextView historyAwbText = (TextView) holder.itemView.findViewById(R.id.history_awb);
                TextView historyDateText = (TextView) holder.itemView.findViewById(R.id.history_date);
                TextView historyAirwaysText = (TextView) holder.itemView.findViewById(R.id.history_airways_name);
                View airwaysColorView = holder.itemView.findViewById(R.id.history_airways_color);

                airwaysColorView.setBackgroundColor(Color.parseColor("#" + Integer.toHexString(item.getAirwaysColor())));
                historyAwbText.setText(item.getAirwaybill());
                historyAirwaysText.setText(item.getAirwaysName());

                try {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
                    Date baseDate = dateFormat.parse(item.getTimestamp());
                    DateFormat dateFormatPengerjaan = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());

                    historyDateText.setText(dateFormatPengerjaan.format(baseDate));
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }

                historyCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(TrackingHistoryActivity.this, TrackingActivity.class);
                        intent.putExtra(TrackingActivity.AWB_EXTRA_KEY, item.getAirwaybill());
                        startActivity(intent);
                    }
                });
            }
        });

        LinearLayoutManager linearLayoutMgr = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutMgr);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(historyAdapter);
    }

    private void getHistoryData() {
        historyList = DbHelper.with(this).trackingHistoryDao().getAllHistory();

        if (historyList.size() == 0) {
            renderNoData();
        } else {
            historyAdapter.addAll(historyList);
            renderDataAvailable();
        }
    }

    private void renderDataAvailable() {
        noDataText.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void renderNoData() {
        noDataText.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tracking_history, menu);

        for (int i = 0; i < menu.size(); i++) {
            Drawable menuIcon = menu.getItem(i).getIcon();
            if (menuIcon != null) {
                menuIcon.mutate();
                menuIcon.setColorFilter(
                        ContextCompat.getColor(this, R.color.color_white),
                        PorterDuff.Mode.SRC_ATOP);
            }
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_history:
                showDeleteHistoryDialog();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void showDeleteHistoryDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        AlertDialog dialog;

        dialogBuilder.setMessage("Hapus semua history?");
        dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DbHelper.with(TrackingHistoryActivity.this).trackingHistoryDao().deleteAllHistory();
                historyAdapter.notifyDataSetChanged();
                renderNoData();
            }
        });
        dialogBuilder.setNegativeButton("No", null);
        dialog = dialogBuilder.create();
        dialog.show();
    }
}
