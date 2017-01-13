package cn.sniper.alice.Brain.BrainViews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.sniper.alice.R;

/**
 * Created by Lisa on 2017/1/5.
 */
public class WriteFragment extends BaseFragment{

    public WriteFragment(){}

    private static WriteFragment writeFragment;


    @Override
    public View createRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.write_fragment, container, false);
    }


    /**
     * 单例模式
     */
    public static WriteFragment getInstance(){
        if(writeFragment == null){
            writeFragment = new WriteFragment();
        }
        return writeFragment;
    }
}
