package cn.sniper.alice.Brain.BrainViews.Chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.sniper.alice.Brain.BrainViews.BaseFragment;
import cn.sniper.alice.R;

/**
 * Created by peisong on 2017/1/13.
 */

public class GroupListFragment extends BaseFragment {

    private static GroupListFragment groupListFragment;


    @Override
    public View createRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.group_list_fragment, container, false);
    }

    public static GroupListFragment getInstance(){
        if (groupListFragment == null){
            groupListFragment = new GroupListFragment();
        }
        return groupListFragment;
    }
}
