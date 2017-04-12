package jhk.nytimes_mobile.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;

import java.net.MalformedURLException;
import java.net.URL;

import jhk.nytimes_mobile.Adapters.StoryList_Adapter;
import jhk.nytimes_mobile.Interfaces.AsyncTaskCallBack;
import jhk.nytimes_mobile.Log.Logger;
import jhk.nytimes_mobile.Objects.API_Result;
import jhk.nytimes_mobile.Objects.MultiMediaData;
import jhk.nytimes_mobile.Objects.StoryData;
import jhk.nytimes_mobile.R;
import jhk.nytimes_mobile.Tasks.LoadStoryListTask;
import jhk.nytimes_mobile.Utils.NetWork_Util;

/**
 * Story List Activity
 */
public class StoryListActivity extends AppCompatActivity implements AsyncTaskCallBack,View.OnClickListener{

    StoryList_Adapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storylist);
        init();
    }

    /**
     * 초기화 함수
     */
    private void init() {
        Logger.Log_D("Initialize Story List Activity.");
        adapter = new StoryList_Adapter(this,R.id.grid_view_item);
        adapter.setOnClickListener(this);
        ((StaggeredGridView)findViewById(R.id.main_list_view)).setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNews();
    }

    private void loadNews() {
        // TODO : 네트워크 상태 체크 로직 강화 필요.
        if(NetWork_Util.CanUserNetwork(this) == false){
            Toast.makeText(this,"네트워크 상태를 체크해 주시기 바랍니다.",Toast.LENGTH_SHORT).show();
            return;
        }

        adapter.clear();
        URL url = null;
        try {
            url = new URL(getString(R.string.ny_times_api_url));
        } catch (MalformedURLException e) {
            // Resource에 지정된 URL을 사용함으로 MalformedURL Exception이 발생할수 없다.
            e.printStackTrace();
        }

        LoadStoryListTask task = new LoadStoryListTask(this);
        try {
            task.execute(url);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void asyncTaskCallBack(Object result) {

        if(result == null) {
            Toast.makeText(this, "뉴스 목록 조회 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        API_Result apiResult = (API_Result)result;

        // 뉴스 조회 실패
        if(apiResult.status.equals("OK") == false){
            Toast.makeText(this,"뉴스 목록 조회에 실패했습니다.",Toast.LENGTH_SHORT).show();
            return;
        }

        // 조회된 뉴스 없음
        if(apiResult.num_results == 0){
            Toast.makeText(this,"새로운 뉴스가 없습니다.",Toast.LENGTH_SHORT).show();
            return;
        }

        // Add StoryData to Adapter
        for(StoryData data : apiResult.results){
            // multimedia size check
            if(data.multimedia.size() != 0)
                adapter.add(data);
        }
    }

    /**
     * News Card Click CallBack
     * Story Activity를 호출한다.
     * 파라메터로 전달된 View에 Tag가 있을 경우 태그를 URL Extra Data로 삽입 후 Story Activity를 호출한다.
     * @param v
     */
    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this,StoryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if(v.getTag() != null) {
            intent.putExtra(getString(R.string.intent_extra_key_url), v.getTag().toString());
        }

        startActivity(intent);
    }
}
