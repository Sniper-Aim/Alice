package cn.sniper.alice.Brain.BrainView.Adapter.ListViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.jpush.im.android.api.model.Conversation;
import cn.sniper.alice.R;

/**
 * Created by peisong on 2017/1/18.
 */

public class ConversationListViewAdapter extends ListViewBaseAdapter{

    private LayoutInflater mInflater = null;

    private List<Conversation> list;

    private ViewHolder holder;

    public ConversationListViewAdapter(List<Conversation> list, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
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
            view = mInflater.inflate(R.layout.conversation_listview_item_layout, null);
            holder.user_icon = (ImageView) view.findViewById(R.id.conversation_item_icon);
            holder.user_name = (TextView) view.findViewById(R.id.conversation_friends_name);
            holder.user_autograph = (TextView) view.findViewById(R.id.conversation_item_record);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.user_icon.setBackgroundResource(R.mipmap.ic_launcher);
        holder.user_name.setText(list.get(i).getTitle());
        holder.user_autograph.setText(list.get(i).getLatestMessage().getContent().getStringExtra("text"));
        return view;
    }

    static class ViewHolder
    {
        public ImageView user_icon;
        public TextView user_name;
        public TextView user_autograph;
    }

}
