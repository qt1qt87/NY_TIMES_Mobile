package jhk.nytimes_mobile.Web;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by RG on 2017-04-11.
 */
public class CustomWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
    }
}