package jhk.nytimes_mobile.Objects;

import android.graphics.Bitmap;

import java.util.HashMap;

/**
 * SingleTone Image Cache
 */
public class ImageCache extends HashMap<String,Bitmap>{
    private static ImageCache Instance = null;

    /**
     * SingleTone 생성자
     */
    private ImageCache(){

    }

    /**
     * Image Cache 객체 획득 함수
     * @return ImageCache
     */
    public static ImageCache GetInstance(){
        if(Instance == null)
            Instance = new ImageCache();
        return Instance;
    }
}
