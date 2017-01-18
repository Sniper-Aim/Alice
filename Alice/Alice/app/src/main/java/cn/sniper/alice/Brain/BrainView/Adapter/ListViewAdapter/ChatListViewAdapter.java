package cn.sniper.alice.Brain.BrainView.Adapter.ListViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.jpush.im.android.api.model.UserInfo;
import cn.sniper.alice.ExternalTools.Logs.Logger;
import cn.sniper.alice.R;

/**
 * Created by peisong on 2017/1/13.
 */

public class ChatListViewAdapter extends ListViewBaseAdapter {

    private LayoutInflater mInflater = null;

    private List<UserInfo> list;

    private ViewHolder holder;


    public ChatListViewAdapter(List<UserInfo> list, Context context){
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
            view = mInflater.inflate(R.layout.chat_listview_item_layout, null);
            holder.user_icon = (ImageView) view.findViewById(R.id.user_icon);
            holder.user_name = (TextView) view.findViewById(R.id.user_name);
            holder.user_autograph = (TextView) view.findViewById(R.id.user_autograph);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.user_icon.setBackgroundResource(R.mipmap.ic_launcher);
        holder.user_name.setText(list.get(i).getNickname());
        Logger.e(list.get(i).getNickname());

        return view;
    }

    static class ViewHolder
    {
        public ImageView user_icon;
        public TextView user_name;
        public TextView user_autograph;
    }


}
