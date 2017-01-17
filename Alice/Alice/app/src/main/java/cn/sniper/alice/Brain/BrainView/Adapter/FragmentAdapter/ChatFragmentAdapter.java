package cn.sniper.alice.Brain.BrainView.Adapter.FragmentAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.sniper.alice.Brain.BrainViews.Chat.ChatListFragment;
import cn.sniper.alice.Brain.BrainViews.Chat.GroupListFragment;

/**
 * Created by Lisa on 2017/1/5.
 */

public class ChatFragmentAdapter extends FragmentPagerAdapter{

    public ChatFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
            return ChatListFragment.getInstance();

            case 1:
            return GroupListFragment.getInstance();

        }
        return new Fragment();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
