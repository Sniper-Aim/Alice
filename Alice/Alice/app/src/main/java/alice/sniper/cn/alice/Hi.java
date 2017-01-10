package alice.sniper.cn.alice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zcw.togglebutton.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import alice.sniper.cn.alice.Alice.Alice;
import alice.sniper.cn.alice.Brain.Brain;
import alice.sniper.cn.alice.Brain.BrainInterFace.BrainInterFace;
import alice.sniper.cn.alice.ExternalTools.Animation.Animatiom;
import alice.sniper.cn.alice.ExternalTools.Animation.AnimationOver;
import alice.sniper.cn.alice.ExternalTools.Logs.Logger;
import alice.sniper.cn.alice.Hear.Hear;
import alice.sniper.cn.alice.Hear.HearResult.Result;
import alice.sniper.cn.alice.HiActivity.FragmentAdapter;
import alice.sniper.cn.alice.HiActivity.SpeakFragment;
import alice.sniper.cn.alice.HiActivity.WriteFragment;
import alice.sniper.cn.alice.Setting.SettingActivity.SettingActivity;
import alice.sniper.cn.alice.Thought.Thought;
import alice.sniper.cn.alice.View.NoScrollViewPager;

/**
 * Created by pei_song on 2016/12/26.
 */

public class Hi extends Brain implements View.OnClickListener{
    private static String TAG = "Hi";
    /**
     * Alice的主要开关
     */
    private ToggleButton open_btn;
    private ViewPager viewPager;
    private Button speak_btn,write_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*  设置主页布局  */
        setContentView(R.layout.activity_alice);

        /*  设置Alice已经启动  */
        Alice.setISRUN(true);

        /*  初始化操作  */
        initView();
        setEvent();
    }
    /**
     * 初始化操作
     */
    public void initView() {
        viewPager= (ViewPager) findViewById(R.id.my_viewpager);
        viewPager.setOffscreenPageLimit(2);
        FragmentAdapter fragmentAdapter=new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapter);
        /* 开关 */
        open_btn = (ToggleButton) findViewById(R.id.open_btn);
        speak_btn= (Button) findViewById(R.id.speak_btn);
        write_btn= (Button) findViewById(R.id.write_btn);
        speak_btn.setOnClickListener(this);
        write_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.speak_btn:
                Log.i("33333333333333","666666666666666666666666666666666666666");
                Toast.makeText(this, "说话", Toast.LENGTH_SHORT).show();
                viewPager.setCurrentItem(0);
                break;

            case R.id.write_btn:
                Toast.makeText(this, "写字", Toast.LENGTH_SHORT).show();
                viewPager.setCurrentItem(1);
                break;
        }
    }

    /**
     * 设置所有事件
     */
    public void setEvent(){
        /**
         * 开启 “思想” 类刷新线程
         */
        new Thought().start();

        /**
         * 设置开关事件, 开关之后的状态回调事件
         */
        open_btn.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                /**
                 * 为真的时候证明按钮被打开 否则为按钮被关闭
                 */
                if (on){

                }else{

                }
                Alice.setISRUN(on);
            }
        });
    }

    /**---------下为周期方法-----------*/
    @Override
    protected void onStart() {
        super.onStart();

        /* 初始化按钮状态 */
        open_btn.toggle(Alice.getISRUN());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void test(){

    }
}
