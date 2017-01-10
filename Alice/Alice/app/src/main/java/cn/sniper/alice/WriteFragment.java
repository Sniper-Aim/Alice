package cn.sniper.alice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Lisa on 2017/1/5.
 */
public class WriteFragment extends Fragment{

    public WriteFragment(){}
    /**
     * 单例模式
     */
    private static WriteFragment writeFragment;
    public static WriteFragment getInstance(){
        if(writeFragment==null){
            writeFragment=new WriteFragment();
        }
        return writeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.write_fragment,container,false);
    }
}
