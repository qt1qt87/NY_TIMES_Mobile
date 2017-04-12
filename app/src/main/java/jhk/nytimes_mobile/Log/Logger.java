package jhk.nytimes_mobile.Log;

import android.util.Log;

/**
 * Log Helper
 */
public class Logger {

    //Log Tag
    private static final String TAG = "NY_Times_Mobile";

    /**
     * Debug Log
     * @param log
     */
    public static void Log_D(String log){
        Log.d(TAG,log);
    }
}
