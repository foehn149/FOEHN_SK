package inc.thenewpirates.foehn;

import android.content.Context;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PrintStoreActivity extends AppCompatActivity {
    WebView mywebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeprint);
        mywebView = (WebView) findViewById(R.id.webView);

        String htmlDocument =
                "<html><head><body>" +
                        "<br><br>" +
                        "<div><h2><b> F. O. E. H. N.</b></h2><h3> A Global Charity Trust.</h3><br><hr>" +
                        "<h2> I N V O I C E </h2><hr><br>" +
                        "<p> Purchase Details :- <br>" +
                        "<p> Name >> <a> Swapnil </a><a> Kamane </a><br>" +
                        "<p> Product Name >> <a> T-Shirt </a>" +
                        "<p> Price >> <a> Rs.100 </a>" +
                        "<p> Sub Total >> <a> Rs.100 </a>" +
                        "<p> Tax 5% >> <a> Rs.5 </a>" +
                        "<br><br> Grand Total >> <a> Rs.105 </a><br>" +
                        "<br> Thank You ! </p><br>" +
                        "</div></body></html>";

        startWebView("http://index.html");
        mywebView.getSettings().setLoadWithOverviewMode(true);
        mywebView.getSettings().setUseWideViewPort(true);
        mywebView.getSettings().setLoadsImagesAutomatically(true);
        mywebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mywebView.setScrollbarFadingEnabled(false);
        mywebView.getSettings().setBuiltInZoomControls(true);

        mywebView.loadDataWithBaseURL(null, htmlDocument, "text/HTML", "UTF-8", null);
    }

    private void startWebView(String url) {
        mywebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return true;
            }
        });
        mywebView.loadUrl(url);
        mywebView.canGoBack();
    }


    public void createWebPrintJobStore(View view) {
        PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
        PrintDocumentAdapter printAdapter = mywebView.createPrintDocumentAdapter();
        String jobName = " FOEHN Print Test ";
        printManager.print(jobName, printAdapter, new PrintAttributes.Builder().build());
    }
}
