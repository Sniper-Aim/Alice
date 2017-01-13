package cn.sniper.alice.Brain.BrainViews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;

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

    private ChatMsgListView chatListView;

    private ListView listView;

    private ChatListViewAdapter chatListViewAdapter;

    private WindowManager windowManager;

    List<String> list = new ArrayList<>();

    /**
     * 初始化布局
     */
    private void initView(){
//        chatListView = (ChatMsgListView) rootView.findViewById(R.id.Chat_ListView);
        listView = (ListView) rootView.findViewById(R.id.Chat_ListView);



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
        chatListViewAdapter = new ChatListViewAdapter(list, mContext);
    }

    /**
     * 初始化事件
     */
    private void initEvent(){
//        chatListView.setAdapter(chatListViewAdapter);
        listView.setAdapter(chatListViewAdapter);

        chatListViewAdapter.setOnTouch(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent ev) {
                switch (ev.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        view.scrollTo((int)ev.getX() ,0);
                        Logger.e(ev.getX()+"");
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
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
