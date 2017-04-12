package jhk.nytimes_mobile.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;

import jhk.nytimes_mobile.Interfaces.AsyncTaskCallBack;
import jhk.nytimes_mobile.Objects.MultiMediaData;
import jhk.nytimes_mobile.Objects.StoryData;
import jhk.nytimes_mobile.R;
import jhk.nytimes_mobile.Tasks.LoadImageFromUrlTask;

/**
 * Story Card View
 */
public class StoryCard extends LinearLayout implements View.OnClickListener, AsyncTaskCallBack {

    //텍스브 박스 백그라운드 컬러 설정을 위한 변수
    private Random random = null;
    private final int[] colorIDs = {R.color.blue, R.color.red, R.color.gray, R.color.green, R.color.yellow};

    //텍스트 박스 클릭시 호출 될 Listener
    private OnClickListener onTitleClickListener = null;

    //News Data
    private StoryData storyData = null;

    //Image Load Async Task
    private LoadImageFromUrlTask imageLoadTasker = null;

    //Static Image Cache
    static HashMap<String, Bitmap> ImageCache = new HashMap<>();

    /**
     * New Card의 이미지 Type
     */
    enum CARD_TYPE {
        LANDSCAPE,
        PORTRAIT
    }

    //Widgets
    private ImageView imageView = null;         //메인 이미지
    private TextView topTextView = null;        //이미지 위에 겹쳐 표현되는 텍스트 박스
    private TextView bottomTextView = null;     //이미지 하단에 따로 표시되는 텍스트 박스

    /**
     * News Card 생성자.
     * @param context Context
     */
    public StoryCard(Context context) {
        super(context);
        initView();
        random = new Random();
    }

    /**
     * News Card 생성자.
     * @param context Context
     * @param attrs   Attrs
     */
    public StoryCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        random = new Random();
    }

    /**
     * News Card 생성자.
     * @param context      Context
     * @param attrs        Attrs
     * @param defStyleAttr DefStyleAttr
     */
    public StoryCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        random = new Random();
    }

    /**
     * Widget 초기화 함수
     */
    private void initView() {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.grid_view_item, this, false);
        addView(view);

        //set Local Variable
        imageView = (ImageView) view.findViewById(R.id.img_main);
        topTextView = (TextView) view.findViewById(R.id.tv_top_text);
        bottomTextView = (TextView) view.findViewById(R.id.tv_bottom_text);

        //set onClick Listener
        topTextView.setOnClickListener(this);
        bottomTextView.setOnClickListener(this);
    }

    /**
     * Set Story
     * 스토리 데이터에 따라 레이아웃을 변경하고 각종 Widget의 값을 할당한다.
     *
     * @param data Story Data
     */
    public void setStory(StoryData data) {

        storyData = data;

        //기존 작업 중단
        if (imageLoadTasker != null)
            imageLoadTasker.cancel(true);

        //View 초기화
        setImage(null);
        setText("");

        //Set Background Color
        int index = random.nextInt(100) % colorIDs.length;
        bottomTextView.setBackgroundResource(colorIDs[index]);

        //Set Title Text
        if (storyData.title.isEmpty() == false) {
            setText(data.title);
        }

        //Check Image Count
        if (data.multimedia.size() == 0) {
            return;
        }

        //Normal Size Image를 찾아 Image View에 설정
        for (MultiMediaData mData : storyData.multimedia) {
            if (mData.format.equals("Normal")) {

                // 기존에 로드된 이미지가 있는지 체크
                if (ImageCache.containsKey(mData.url))
                    setImage(ImageCache.get(mData.url));
                else {
                    imageLoadTasker = new LoadImageFromUrlTask(this);
                    imageLoadTasker.execute(mData.url);
                }

            }
        }
    }


    /**
     * 카드 이미지 변경 함수
     *
     * @param bmp 변경할 이미지
     */
    public void setImage(Bitmap bmp) {

        if (imageView == null)
            return;

        if(bmp != null) {
            if (bmp.getHeight() > bmp.getWidth()) {
                setType(CARD_TYPE.PORTRAIT);
            } else {
                setType(CARD_TYPE.LANDSCAPE);
            }
        }

        imageView.setImageBitmap(bmp);
    }

    /**
     * 카드 텍스트 변경함수
     *
     * @param str 변경할 텍스트
     */
    public void setText(String str) {
        if (topTextView != null)
            topTextView.setText(str);
        if (bottomTextView != null)
            bottomTextView.setText(str);
    }

    /**
     * 카드 타입 설정 함수
     * 카드 타입에 따라 Widget의 구성을 변경한다
     *
     * @param type CARD_TYPE
     */
    public void setType(CARD_TYPE type) {
        switch (type) {
            case LANDSCAPE:
                imageView.setVisibility(VISIBLE);
                topTextView.setVisibility(GONE);
                bottomTextView.setVisibility(VISIBLE);
                break;
            case PORTRAIT:
                imageView.setVisibility(VISIBLE);
                bottomTextView.setVisibility(GONE);
                topTextView.setVisibility(VISIBLE);
                break;
        }
    }

    /**
     * Title Text Click시 호출 될 Listener 설정 함수
     *
     * @param listener
     */
    public void setTitleClickListener(OnClickListener listener) {
        onTitleClickListener = listener;
    }

    /**
     * Card Click Function
     *
     * @param v View
     */
    @Override
    public void onClick(View v) {
        if (onTitleClickListener == null)
            return;

        v.setTag(storyData.url);
        onTitleClickListener.onClick(v);
    }

    /**
     * Async Task CallBack
     *
     * @param result Bitmap Image
     */
    @Override
    public void asyncTaskCallBack(Object result) {
        Object[] results = (Object[]) result;
        String bmpUrl = (String) results[0];
        Bitmap bmp = (Bitmap) results[1];
        ImageCache.put(bmpUrl, bmp);
        setImage(bmp);
    }
}
