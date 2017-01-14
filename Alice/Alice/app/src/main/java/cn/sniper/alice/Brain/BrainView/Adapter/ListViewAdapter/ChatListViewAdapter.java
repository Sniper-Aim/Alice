package cn.sniper.alice.Brain.BrainView.Adapter.ListViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

import cn.sniper.alice.R;

/**
 * Created by peisong on 2017/1/13.
 */

public class ChatListViewAdapter extends ListViewBaseAdapter {

    private LayoutInflater mInflater = null;

    private View.OnTouchListener onTouchListener;

    private List<String> list;

    private ViewHolder holder;

    private WindowManager windowManager;

    private int layoutW;


    public ChatListViewAdapter(List<String> list, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
        windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        layoutW = this.windowManager.getDefaultDisplay().getWidth();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.chat_listview_item_layout, null);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        return view;
    }

    public interface OnTouch{
        void Touch(int i);
    }

    public void setOnTouch(View.OnTouchListener touch){
        this.onTouchListener = touch;
    }

    static class ViewHolder
    {
        public ScrollView scrollView;
        public TextView user_nick_name_text;
        public LinearLayout linearLayout;
        public TextView btn1;
    }


}
