package com.detatocargo.android.detatocargo.activities;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.detatocargo.android.detatocargo.view.AwbDbAdapter;
import com.detatocargo.android.detatocargo.R;
import com.detatocargo.android.detatocargo.utils.TrackingWebConfiguration;
import com.detatocargo.android.detatocargo.data.DbHelper;
import com.detatocargo.android.detatocargo.data.model.TrackingHistory;
import com.detatocargo.android.detatocargo.objects.Awb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

public class TrackingActivity extends AppCompatActivity
        implements SearchView.OnQueryTextListener {

    public static final String AWB_EXTRA_KEY = "awb_extra_key";

    private Toolbar toolbar;

    private MenuItem searchItem;
    private SearchView searchView;
    private SearchManager searchManager;

    private WebView webView;
    private TextView infoText, notFoundText;
    private ProgressBar progressBar;

    private String[] airlinesPrefix;

    private TrackingWebConfiguration trackingWebConfig;

    private AwbDbAdapter awbDbAdapter;
    private String awbNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        toolbar = (Toolbar) findViewById(R.id.tracking_toolbar);
        webView = (WebView) findViewById(R.id.cargo_webview);
        infoText = (TextView) findViewById(R.id.info_textview);
        notFoundText = (TextView) findViewById(R.id.not_found_textview);
        progressBar = (ProgressBar) findViewById(R.id.web_progressbar);
        setSupportActionBar(toolbar);
        init();
        setTitle("Tracking History");
    }

    private void init() {
        initToolbar();
        initWebView();

        searchNotStartView();
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

    private void initWebView() {
        Awb awb = new Awb();
        awbNum = awb.getAwbNumber();
        awbNum = getIntent().getStringExtra(AWB_EXTRA_KEY);

        trackingWebConfig = new TrackingWebConfiguration();
        airlinesPrefix = getResources().getStringArray(R.array.airlines_prefix);

        awbDbAdapter = new AwbDbAdapter(getApplicationContext());

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setBuiltInZoomControls(true);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100 && progressBar.getVisibility() == ProgressBar.GONE) {
                    progressBar.setVisibility(View.VISIBLE);
                }
                progressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void searchNotStartView() {
        webView.setVisibility(View.GONE);
        infoText.setVisibility(View.VISIBLE);
        notFoundText.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search_awb, menu);
        setupSearchView(menu);
        return true;
    }

    private void setupSearchView(Menu menu) {
        searchItem = menu.findItem(R.id.search_awb);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setIconified(false);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setQuery(awbNum, true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        searchView.clearFocus();
        trackingSetup(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    private void trackingSetup(String query) {
        final String setAwbPrefix = query.substring(0, 3);
        final String setAwbNumber = query.substring(3);

        infoText.setVisibility(View.GONE);
        notFoundText.setVisibility(View.GONE);

        if (query.length() < 3 || query.length() > 11) {
            Toast.makeText(getApplicationContext(), "Awb must contain 11 number!", Toast.LENGTH_SHORT).show();
        } else {
            if (setAwbPrefix.equals(airlinesPrefix[0])) {
                // Cathay Airways
                webView.setVisibility(View.VISIBLE);
                trackingWebConfig.cathayAirways(webView, airlinesPrefix[0], setAwbNumber);
                insertAwbData(query, "Cathay Pacific Airlines", ContextCompat.getColor(TrackingActivity.this, R.color.color_cathay_pacific));
            } else if (setAwbPrefix.equals(airlinesPrefix[1])) {
                // Thai Airways
                webView.setVisibility(View.VISIBLE);
                trackingWebConfig.thaiAirways(webView, airlinesPrefix[1], setAwbNumber);
                insertAwbData(query, "Thai Airways", ContextCompat.getColor(TrackingActivity.this, R.color.color_thai_airways));
            } else if (setAwbPrefix.equals(airlinesPrefix[2])) {
                // AirAsia Airways
                webView.setVisibility(View.VISIBLE);
                trackingWebConfig.airAsia(webView, airlinesPrefix[2], setAwbNumber);
                insertAwbData(query, "Air Asia", ContextCompat.getColor(TrackingActivity.this, R.color.color_airasia_airways));
            } else if (setAwbPrefix.equals(airlinesPrefix[3])) {
                // Etihad Airways
                webView.setVisibility(View.VISIBLE);
                trackingWebConfig.etihadAirways(webView, airlinesPrefix[3], setAwbNumber);
                insertAwbData(query, "Etihad Airways", ContextCompat.getColor(TrackingActivity.this, R.color.color_etihad_airways));
            } else if (setAwbPrefix.equals(airlinesPrefix[5])) {
                // Qantas Airways
                webView.setVisibility(View.VISIBLE);
                trackingWebConfig.qantasAirlines(webView, airlinesPrefix[5], setAwbNumber);
                insertAwbData(query, "Qantas Airways", ContextCompat.getColor(TrackingActivity.this, R.color.color_qantas_airways));
            } else if (setAwbPrefix.equals(airlinesPrefix[6])) {
                // Turkish Airways
                webView.setVisibility(View.VISIBLE);
                trackingWebConfig.turkishAirlines(webView, airlinesPrefix[6], setAwbNumber);
                insertAwbData(query, "Turkish Airlines", ContextCompat.getColor(TrackingActivity.this, R.color.color_turkish_airways));
            } else if (setAwbPrefix.equals(airlinesPrefix[7])) {
                // American Airways
                webView.setVisibility(View.VISIBLE);
                trackingWebConfig.americanAirlines(webView, airlinesPrefix[7], setAwbNumber);
                insertAwbData(query, "American Airways", ContextCompat.getColor(TrackingActivity.this, R.color.color_american_airways));
            } else if (setAwbPrefix.equals(airlinesPrefix[8])) {
                // South African Airways
                webView.setVisibility(View.VISIBLE);
                trackingWebConfig.southAfricanAirways(webView, airlinesPrefix[8], setAwbNumber);
                insertAwbData(query, "South African Airways", ContextCompat.getColor(TrackingActivity.this, R.color.color_south_african_airways));
            } else {
                Toast.makeText(getApplicationContext(), "Airline Not Found!", Toast.LENGTH_SHORT).show();
                webView.setVisibility(View.GONE);
                notFoundText.setVisibility(View.VISIBLE);
            }
        }
    }

    private void insertAwbData(String query, String airwaysName, Integer airwaysColor) {
        Integer existingCount = DbHelper.with(this).trackingHistoryDao().checkExistingAwbCount(query).size();

        if (existingCount > 0) {
            Timber.e("AWB Already Exists");
        } else {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());

            TrackingHistory trackingHistory = new TrackingHistory();
            trackingHistory.setAirwaysName(airwaysName);
            trackingHistory.setAirwaysColor(airwaysColor);
            trackingHistory.setAirwaybill(query);
            trackingHistory.setTimestamp(dateFormat.format(new Date()));

            DbHelper.with(this).trackingHistoryDao().insertSingleHistory(trackingHistory);
        }
    }
}
