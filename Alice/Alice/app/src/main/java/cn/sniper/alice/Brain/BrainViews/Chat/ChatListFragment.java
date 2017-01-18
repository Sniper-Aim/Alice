package cn.sniper.alice.Brain.BrainViews.Chat;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import cn.sniper.alice.Alice.Alice;
import cn.sniper.alice.Brain.BrainView.Adapter.ListViewAdapter.ChatListViewAdapter;
import cn.sniper.alice.Brain.BrainViews.BaseFragment;
import cn.sniper.alice.R;

/**
 * Created by peisong on 2017/1/13.
 */

public class ChatListFragment extends BaseFragment {

    private static ChatListFragment chatListFragment;

    private Alice alice;

    private ListView listView;

    private ChatListViewAdapter chatListViewAdapter;

    /**
     * 初始化布局
     */
    private void initView(){
        listView = (ListView) rootView.findViewById(R.id.chat_list_view);
    }

    /**
     * 初始化数据
     */
    private void initData(){
        alice = Alice.getInstance();
        chatListViewAdapter = new ChatListViewAdapter(alice.getUserList(), mContext);
    }

    /**
     * 初始化事件
     */
    private void initEvent(){
        listView.setAdapter(chatListViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(mContext, ConversationActivity.class);
                intent.putExtra("user_id", alice.getUserList().get(i).getUserName());
                intent.putExtra("user_name", alice.getUserList().get(i).getNickname());
                startActivity(intent);
            }
        });
    }

    @Override
    public View createRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chat_list_fragment, container, false);
    }

    public static ChatListFragment getInstance(){
        if (chatListFragment == null){
            chatListFragment = new ChatListFragment();
        }
        return chatListFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initEvent();
    }

}
