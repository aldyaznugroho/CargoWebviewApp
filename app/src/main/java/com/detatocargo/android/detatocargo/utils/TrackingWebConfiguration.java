package com.detatocargo.android.detatocargo.utils;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Aldyaz on 7/11/2016.
 */
public class TrackingWebConfiguration {

    public void cathayAirways(WebView webView, String prefix, String awbNumber) {
        final String cathayLink = "http://www.cathaypacificcargo.com/ManageYourShipment/TrackYourShipment/tabid/108/SingleAWBNo/" + prefix + "-" + awbNumber + "/language/en-US/Default.aspx";
        webView.loadUrl(cathayLink);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });
    }

    public void thaiAirways(WebView webView, String prefix, String awbNumber) {
        final String thaiLink = "http://chorus.thaicargo.com/skychain/app?PID=WEB01-10&doc_typ=AWB&awb_pre=" + prefix + "&awb_no=" + awbNumber;
        webView.loadUrl(thaiLink);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });
    }

    public void airAsia(WebView webView, String prefix, String awbNumber) {
        final String airAsiaLink = "http://cargo.airasia.com/track-trace-wt-721/";
        final String airAsiaJs = "javascript:document.getElementById('ctl00_ctl00_ctl00_ctl00_middleWidgetsAndContent_cpMiddleArea_txtAWB1').value = '" + prefix + "';" +
                "document.getElementById('ctl00_ctl00_ctl00_ctl00_middleWidgetsAndContent_cpMiddleArea_txtAWB2').value = '" + awbNumber + "';" +
                "document.getElementById('ctl00_ctl00_ctl00_ctl00_middleWidgetsAndContent_cpMiddleArea_btnAWBGo').click();";
        webView.loadUrl(airAsiaLink);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl(airAsiaJs);
            }
        });
    }

    public void etihadAirways(WebView webView, String prefix, String awbNumber) {
        final String etihadLink = "http://www.etihadcargo.com/manageshipment/pages/shipmenttracking.aspx";
        final String etihadJs = "javascript:document.getElementById('ctl00_ctl25_g_d72b52fd_7e00_4e3e_977a_bb76e11132b8_ctl00_txt_AWBPrefix').value = '" + prefix + "';" +
                "document.getElementById('ctl00_ctl25_g_d72b52fd_7e00_4e3e_977a_bb76e11132b8_ctl00_txt_AWBNumber').value = '" + awbNumber + "';" +
                "document.getElementById('ctl00_ctl25_g_d72b52fd_7e00_4e3e_977a_bb76e11132b8_ctl00_btn_Submit').click();";
        webView.loadUrl(etihadLink);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                    }
                });
                view.loadUrl(etihadJs);
            }
        });
    }

    public void qantasAirlines(WebView webView, String prefix, String awbNumber) {
        final String qantasAirlinesLink = "https://www.qantas.com.au/qfreight/qfe/online-tracking/global/en";
        final String qantasJs = "javascript:document.getElementById('notificationMasterForm:singleTable:0:txt_singlePrefix').value = '" + prefix + "';" +
                "document.getElementById('notificationMasterForm:singleTable:0:txt_singleMasterDocumentNumber').value = '" + awbNumber + "';" +
                "document.getElementById('notificationMasterForm:searchButton').click();";
        webView.loadUrl(qantasAirlinesLink);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                    }
                });
                view.loadUrl(qantasJs);
            }
        });
    }

    public void turkishAirlines(WebView webView, String prefix, String awbNumber) {
        final String turkishAirlinesLink = "https://comisportal.thy.com/public/shipment-tracking-public";
        final String turkishJs = "javascript:document.getElementById('shipmentPrefix').value = '" + prefix + "';" +
                "document.getElementById('masterDocumentNumber').value = '" + awbNumber + "';" +
                "trackingFormSubmit();";
        webView.loadUrl(turkishAirlinesLink);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                    }
                });
                view.loadUrl(turkishJs);
            }
        });
    }

    public void americanAirlines(WebView webView, String prefix, String awbNumber) {
        final String americanAirlinesLink = "https://www.aacargo.com/AACargo/tracking";
        final String americanJs = "javascript:document.getElementById('airwayBills0.awbCode').value = '" + prefix + "';" +
                "document.getElementById('airwayBills0.awbNumber').value = '" + awbNumber + "';" +
                "document.getElementById('awbTrackingForm').submit();";
        webView.loadUrl(americanAirlinesLink);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                    }
                });
                view.loadUrl(americanJs);
            }
        });
    }

    public void southAfricanAirways(WebView webView, String prefix, String awbNumber) {
        final String southAfricanAirwaysLink = "http://m.flysaa.com/en/cargo.html";
        final String southAfricanJs = "javascript:document.getElementById('awbCode').value = '" + prefix + "';" +
                "document.getElementById('awbNum').value = '" + awbNumber + "';" +
                "void(0);" +
                "$('.ui-btn-hidden').click();";
        webView.loadUrl(southAfricanAirwaysLink);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                    }
                });
                view.loadUrl(southAfricanJs);
            }
        });
    }

}
