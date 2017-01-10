package cn.sniper.alice;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Lisa on 2017/1/5.
 */

public class FragmentAdapter extends FragmentPagerAdapter{
    Fragment fragment;

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
            fragment= SpeakFragment.getInstance();
                break;

            case 1:
            fragment= WriteFragment.getInstance();
                break;

        }
        return fragment;
    }


    @Override
    public int getCount() {
        return 2;
    }
}
