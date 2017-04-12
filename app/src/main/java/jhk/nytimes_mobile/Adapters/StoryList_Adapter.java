package jhk.nytimes_mobile.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import jhk.nytimes_mobile.Objects.StoryData;
import jhk.nytimes_mobile.View.StoryCard;


/**
 * Story List Adapter
 */
public class StoryList_Adapter extends ArrayAdapter<StoryData> {

    //Layout Inflater
    private final LayoutInflater layoutInflater;

    //StoryData List
    private ArrayList<StoryData> datas = null;

    //Story Card Click Listener
    private View.OnClickListener onClickListener = null;

    /**
     * Constructor
     * @param context Context
     * @param resource Resource
     */
    public StoryList_Adapter(Context context, int resource) {
        super(context, resource);
        datas = new ArrayList<>();
        layoutInflater = LayoutInflater.from(context);
    }

    /**
     * Get View
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new StoryCard(getContext());
        }

        StoryData sData = getItem(position);

        StoryCard storyCard = (StoryCard)convertView;
        storyCard.setStory(sData);
        storyCard.setTitleClickListener(this.onClickListener);
        return convertView;
    }

    /**
     * StoryCard를 생성하며 적용할 OnClickListener를 미리 변수에 설정하는 함수.
     * @param onClickListener
     */
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
