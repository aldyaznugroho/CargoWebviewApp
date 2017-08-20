package com.detatocargo.android.detatocargo.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.detatocargo.android.detatocargo.R;
import com.detatocargo.android.detatocargo.adapters.HomeMenuGridAdapter;

public class HomeActivity extends AppCompatActivity {

    GridView gridView;
    HomeMenuGridAdapter menuGridAdapter;
    int[] menuName = {
            R.string.tracking,
            R.string.history,
            R.string.about_us,
            R.string.help
    };
    int[] menuImage = {
            R.drawable.ic_find_in_page_white_48dp,
            R.drawable.ic_assignment_turned_in_white_48dp,
            R.drawable.ic_info_white_48dp,
            R.drawable.ic_help_white_48dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        gridView = (GridView) findViewById(R.id.home_menu_grid);
        init();
    }

    private void init() {
        menuGridAdapter = new HomeMenuGridAdapter(HomeActivity.this, menuName, menuImage);
        if (gridView != null) {
            gridView.setAdapter(menuGridAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
                            Intent trackingIntent = new Intent(HomeActivity.this, TrackingActivity.class);
                            startActivity(trackingIntent);
                            break;
                        case 1:
                            Intent bookingIntent = new Intent(HomeActivity.this, TrackingHistoryActivity.class);
                            startActivity(bookingIntent);
                            break;
                        case 2:
                            Uri aboutUsUri = Uri.parse(getString(R.string.about_us_url));
                            Intent aboutIntent = new Intent(Intent.ACTION_VIEW, aboutUsUri);
                            startActivity(aboutIntent);
                            break;
                        case 3:
                            Uri contactUsUri = Uri.parse(getString(R.string.contact_us_url));
                            Intent contactIntent = new Intent(Intent.ACTION_VIEW, contactUsUri);
                            startActivity(contactIntent);
                            break;
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

}
