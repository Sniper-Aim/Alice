package cn.sniper.alice.Brain.BrainViews;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import cn.sniper.alice.Brain.BrainView.Adapter.ListViewAdapter.ChatListViewAdapter;
import cn.sniper.alice.ExternalTools.Logs.Logger;
import cn.sniper.alice.R;

/**
 * Created by peisong on 2017/1/13.
 */

public class ChatListFragment extends BaseFragment {

    private static ChatListFragment chatListFragment;

    private ChatMsgListView listView;

    private ChatListViewAdapter chatListViewAdapter;

    private WindowManager windowManager;

    List<String> list = new ArrayList<>();

    private int x = 0;
    private int y = 0;

    private int downx;
    private int downy;

    private  Boolean isleft;

    /**
     * 初始化布局
     */
    private void initView(){
        listView = (ChatMsgListView) rootView.findViewById(R.id.Chat_ListView);

        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
    }

    /**
     * 初始化数据
     */
    private void initData(){
        windowManager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);

        chatListViewAdapter = new ChatListViewAdapter(list, mContext);
    }

    /**
     * 初始化事件
     */
    private void initEvent(){
        listView.setAdapter(chatListViewAdapter);

        listView.setOnClick(new ChatMsgListView.OnClickOKReName() {
            @Override
            public void OnClick(int i) {
                Logger.e("点击了备注"+i);
            }
        });

        listView.setOnClick(new ChatMsgListView.OnClickDelete() {
            @Override
            public void OnClick(int i) {
                Logger.e("点击了删除"+i);
            }
        });


        listView.setItemOnClick(new ChatMsgListView.OnClickItem() {
            @Override
            public void OnClick(int i) {
                Logger.e("点击了第"+i+"个Item");
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
