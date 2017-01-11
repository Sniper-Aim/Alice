package cn.sniper.alice.Brain.BrainView.Adapter.FragmentAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.sniper.alice.Brain.BrainViews.SpeakFragment;
import cn.sniper.alice.Brain.BrainViews.WriteFragment;

/**
 * Created by Lisa on 2017/1/5.
 */

public class HiFragmentAdapter extends FragmentPagerAdapter{
    Fragment fragment;

    public HiFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
            fragment = SpeakFragment.getInstance();
                break;

            case 1:
            fragment = WriteFragment.getInstance();
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
