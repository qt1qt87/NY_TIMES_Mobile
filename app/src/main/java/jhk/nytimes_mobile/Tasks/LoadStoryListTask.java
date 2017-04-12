package jhk.nytimes_mobile.Tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import jhk.nytimes_mobile.Interfaces.AsyncTaskCallBack;
import jhk.nytimes_mobile.Log.Logger;
import jhk.nytimes_mobile.Objects.API_Result;

/**
 * Story List Load Async Task
 * Async Task CallBack Type : API_Result
 */
public class LoadStoryListTask extends AsyncTask<URL,Void,String> {

    //Async Task CallBack
    private AsyncTaskCallBack callBack = null;

    /**
     * 생성자
     * @param callBack AsyncTaskCallBack
     */
    public LoadStoryListTask(AsyncTaskCallBack callBack){
        this.callBack = callBack;
    }

    protected String doInBackground(URL... params){

        HttpURLConnection urlConnection = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = params[0];
            urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(System.getProperty("line.separator"));
            }
            bufferedReader.close();
            return stringBuilder.toString();
        }
        catch(Exception e) {
            if ( isCancelled())
                Logger.Log_D("Task Cancelled.");
            else if(Thread.interrupted())
                Logger.Log_D("Thread Interrupted.");
            else
                e.printStackTrace();
        }
        finally{
            urlConnection.disconnect();
        }
        return stringBuilder.toString();
    }

    @Override
    protected void onPostExecute(String apiResult) {
        if(isCancelled())
            return;

        API_Result result = null;

        if(apiResult.isEmpty() == false) {
            Gson gson = new Gson();
            String data = apiResult.replace("abstact", "abstactString");
            result = gson.fromJson(data, API_Result.class);
        }

        if(callBack != null)
            callBack.asyncTaskCallBack(result);
    }
}
