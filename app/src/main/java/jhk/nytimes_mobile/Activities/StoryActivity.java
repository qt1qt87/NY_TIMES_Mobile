package jhk.nytimes_mobile.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

import jhk.nytimes_mobile.Log.Logger;
import jhk.nytimes_mobile.R;
import jhk.nytimes_mobile.Web.CustomWebViewClient;

/**
 * Story Activity
 * Story ListActivity 에서 선택한 기사에 대한 자세한 내용을 보는 화면
 */
public class StoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        init();
    }

    /**
     * 초기화 함수
     * Intent의 Extra 데이터 중 URL 이 있는지 체크하여 있을 경우 해당 페이지로 없을 경우 Times 메인 페이지로 이동한다.
     */
    private void init() {
        Logger.Log_D("Initialize Story Activity.");
        Intent intent = getIntent();
        WebView webView = (WebView) findViewById(R.id.story_view_web_view);
        webView.setWebViewClient(new CustomWebViewClient());

        if (intent == null || intent.hasExtra(getString(R.string.intent_extra_key_url)) == false)
            webView.loadUrl(getString(R.string.ny_times_default_url));
        else {
            String url = intent.getStringExtra(getString(R.string.intent_extra_key_url));
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(url);
        }
    }
}
