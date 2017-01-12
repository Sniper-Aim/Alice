package cn.sniper.alice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zcw.togglebutton.ToggleButton;

import cn.sniper.alice.Alice.Alice;
import cn.sniper.alice.Brain.AliceTouchManager.AliceTouchManager;
import cn.sniper.alice.Brain.AliceTouchManager.View.TouchView;
import cn.sniper.alice.Brain.Brain;
import cn.sniper.alice.Brain.BrainView.Adapter.FragmentAdapter.HiFragmentAdapter;
import cn.sniper.alice.Brain.BrainViews.ChatActivity;
import cn.sniper.alice.Brain.BrainViews.HiViewPager;


/**
 * Created by pei_song on 2016/12/26.
 */
public class Hi extends Brain implements View.OnClickListener{
    private static String TAG = "Hi";

    /**
     * Fragment适配器
     */
    private HiFragmentAdapter fragmentAdapter;

    /**
     * Alice的主要开关
     */
    private ToggleButton open_btn;

    /**
     * 主页ViewPage, 包含切换的Fragment
     * ViewPageLimit : ViewPage预加载页数
     */
    private HiViewPager viewPager;
    private int ViewPageLimit = 2;

    /**
     * 说话和输入的按钮声明
     */
    private Button speak_btn;
    private Button write_btn;
    private Button test1;
    private Button test2;
    private Button test3;
    private Button test4;

    /**
     * 初始化操作
     */
    public void initView() {
        viewPager= (HiViewPager) findViewById(R.id.my_viewpager);

        open_btn = (ToggleButton) findViewById(R.id.open_btn);

        speak_btn= (Button) findViewById(R.id.speak_btn);
        write_btn= (Button) findViewById(R.id.write_btn);

        test1 = (Button) findViewById(R.id.test1);
        test2 = (Button) findViewById(R.id.test2);
        test3 = (Button) findViewById(R.id.test3);
        test4 = (Button) findViewById(R.id.test4);
    }

    /**
     * 初始化动画
     */
   public void initAnimation(){

   }

    /**
     * 初始化数据
     */
    private void initData(){
        /**
         * 初始化主页的Fragment的Adapter
         */
        fragmentAdapter = new HiFragmentAdapter(getSupportFragmentManager());

    }

    /**
     * 初始化事件
     */
    private void initEvent(){
        speak_btn.setOnClickListener(this);
        write_btn.setOnClickListener(this);
        test1.setOnClickListener(this);
        test2.setOnClickListener(this);
        test3.setOnClickListener(this);
        test4.setOnClickListener(this);

        viewPager.setOffscreenPageLimit(ViewPageLimit);
        viewPager.setAdapter(fragmentAdapter);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.speak_btn:
                viewPager.setCurrentItem(0);
                break;

            case R.id.write_btn:
                Toast.makeText(this, "写字", Toast.LENGTH_SHORT).show();
                viewPager.setCurrentItem(1);
                break;




            case R.id.test1:
                Toast.makeText(this, "Chat", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(alice, ChatActivity.class);
                startActivity(intent);
                break;
            case R.id.test2:
                Toast.makeText(this, "test2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.test3:
                Toast.makeText(this, "test3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.test4:
                Toast.makeText(this, "test4", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*  设置主页布局  */
        setContentView(R.layout.activity_alice);

        /*  设置Alice已经启动  */



        /*  初始化操作  */
        initView();
        initData();
        initEvent();
//        test();
    }

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
        AliceTouchManager aliceTouch = AliceTouchManager.getAliceTouch(alice);
        aliceTouch.addView(TouchView.getTouchView(alice));

    }
}
