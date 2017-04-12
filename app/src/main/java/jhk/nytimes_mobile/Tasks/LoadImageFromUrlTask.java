package jhk.nytimes_mobile.Tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import jhk.nytimes_mobile.Interfaces.AsyncTaskCallBack;
import jhk.nytimes_mobile.Log.Logger;

/**
 * Load Image From Url Task
 * Async Task CallBack Type : Object[]{String,Bitmap}
 */
public class LoadImageFromUrlTask extends AsyncTask<String,Void,Bitmap> {

    //Async Task CallBack
    private AsyncTaskCallBack callBack = null;
    //Load Image Url
    private String loadImageUrl = "";

    public LoadImageFromUrlTask(AsyncTaskCallBack callBack){
        this.callBack = callBack;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        if(params == null)
            throw new NullPointerException("LoadImageFromUrlTask Param url is Null");

        if(params.length != 1)
            throw new IllegalArgumentException("Url Length is not 1.");

        loadImageUrl = params[0];

        Bitmap bmp = null;
        InputStream in = null;
        try {
            in = new URL(loadImageUrl).openStream();
            bmp = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            if (isCancelled())
                Logger.Log_D("Task Canceled.");
            else if( Thread.currentThread().isInterrupted() )
                Logger.Log_D("Thread is Interrupted.");
            else
                e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bmp;
    }

    protected void onPostExecute(Bitmap result) {
        if(isCancelled())
            return;

        if(callBack == null)
            return;

        callBack.asyncTaskCallBack(new Object[]{loadImageUrl,result});
    }
}
