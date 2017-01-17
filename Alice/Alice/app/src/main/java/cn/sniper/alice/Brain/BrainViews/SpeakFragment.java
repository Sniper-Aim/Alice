package cn.sniper.alice.Brain.BrainViews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cn.sniper.alice.Hear.Hear;
import cn.sniper.alice.R;

/**
 * Created by Lisa on 2017/1/5.
 */

public class SpeakFragment extends BaseFragment{

    /**
     * 开始说话按钮
     */
    private Button start_say;

    /**
     * 听写类
     */
    private Hear hear;

    /**
     * 说话按钮和显示的文字
     */
    private Boolean or = true;
    private TextView  start_say_tex;

    /**
     * Fragment rootView对象
     */

    private static SpeakFragment speakFragment;

    /**
     * 空参构造
     */
    public SpeakFragment(){}

    /**
     * 初始化
     */
    private void initView(){
        /* 开始说话按钮和文字 */
        start_say_tex= (TextView) rootView.findViewById(R.id.start_say_tex);
        start_say = (Button) rootView.findViewById(R.id.start_say);
    }

    /**
     * 初始化数据
     */
    private void initData(){
        /**
         * 听写对象
         */
        hear = new Hear(getContext());


        start_say.setText(R.string.StartSay);
    }


    /**
     * 设置所有事件
     */
    private void initEvent(){

        /**
         * 设置开始说话按钮的点击事件
         */
        start_say.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (or) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        start_say_tex.setText(R.string.EndSay);
                        startSay();
                    }

                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        start_say_tex.setText(R.string.StartSay);
                        stopSay();
                        or = false;
                    }
                }
                return true;
            }
        });
    }

    /**
     * 开始说话方法
     */
    public void startSay(){
        start_say.setBackgroundResource(R.color.button_Start_Color);
        start_say.setText(R.string.EndSay);
        hear.start();
    }

    /**
     * 结束说话方法
     */
    public void stopSay(){
        start_say.setBackgroundResource(R.color.button_End_Color);
        start_say.setText(R.string.StartSay);
        hear.stop();
    }

    @Override
    public View createRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.speak_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
          /* 初始化 开始说话按钮 文字 */
        initView();
        initData();
        initEvent();
    }

    /**
     * 单例模式
     */
    public static SpeakFragment getInstance(){
        if(speakFragment == null){
            speakFragment = new SpeakFragment();
        }
        return speakFragment;
    }
}
