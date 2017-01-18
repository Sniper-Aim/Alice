package cn.sniper.alice.Brain.BrainViews.Chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import cn.sniper.alice.Alice.Alice;
import cn.sniper.alice.Brain.BrainView.Adapter.ListViewAdapter.ConversationListViewAdapter;
import cn.sniper.alice.Brain.BrainViews.BaseFragment;
import cn.sniper.alice.R;

/**
 * Created by peisong on 2017/1/13.
 */

public class ConversationListFragment extends BaseFragment {

    private static ConversationListFragment conversationListFragment;

    /**
     * 会话列表
     */
    private ListView listView;

    /**
     * 会话列表适配器
     */
    private ConversationListViewAdapter conversationListViewAdapter;

    private Alice alice;



    private void initView(){
        listView = (ListView) rootView.findViewById(R.id.conversation_list_view);
    }

    private void initData(){
        alice = Alice.getInstance();
        conversationListViewAdapter = new ConversationListViewAdapter(alice.getConversationList(), mContext);

    }

    private void initEvent(){
        listView.setAdapter(conversationListViewAdapter);
    }




    @Override
    public View createRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.conversation_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initEvent();
    }




    public static ConversationListFragment getInstance(){
        if (conversationListFragment == null){
            conversationListFragment = new ConversationListFragment();
        }
        return conversationListFragment;
    }
}
