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
import cn.sniper.alice.Brain.BrainViews.Chat.ChatActivity;
import cn.sniper.alice.Brain.BrainViews.HiViewPager;
import cn.sniper.alice.Brain.BrainViews.User.SignActivity;
import cn.sniper.alice.ExternalTools.JPush.UserManager;
import cn.sniper.alice.ExternalTools.LocalStorage.SharedPreference;


/**
 * Created by pei_song on 2016/12/26.
 */
public class Hi extends Brain implements View.OnClickListener{
    private static String TAG = "Hi";

    private UserManager userManager;


    /**
     * Alice对象
     */

    private Alice alices;
    /**
     * Fragment适配器
     */
    private HiFragmentAdapter fragmentAdapter;

    /**
     * Alice的主要开关
     */
    private ToggleButton open_btn;

    /**
     * SharedPreference sharedPreferences;
     */
    private SharedPreference sharedPreferences;

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
     * 初始化数据
     */
    private void initData(){
        /**
         * 初始化主页的Fragment的Adapter
         */
        fragmentAdapter = new HiFragmentAdapter(getSupportFragmentManager());

        alices = Alice.getInstance();


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

    private void initRun(){
        sharedPreferences = SharedPreference.getInstance(alice);
        String id = sharedPreferences.readStr(SharedPreference.FileName.SAVE_USERNAME.name(), "" + 0);
        if (id != null){
            String pwd = sharedPreferences.readStr(SharedPreference.FileName.SAVE_PASSWORD.name(), id);
            if (pwd != null) {
                Alice.setISRUN(true);
            }else{
                Intent intent4 = new Intent(alice, SignActivity.class);
                startActivity(intent4);
            }
        }else{
            Intent intent4 = new Intent(alice, SignActivity.class);
            startActivity(intent4);
        }
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
                Toast.makeText(this, "好友列表", Toast.LENGTH_SHORT).show();
                userManager.getFriendList();
                break;

            case R.id.test3:
                Toast.makeText(this, "申请加好友", Toast.LENGTH_SHORT).show();
                break;

            case R.id.test4:
                Intent intent4 = new Intent(alice, SignActivity.class);
                startActivity(intent4);
                Toast.makeText(this, "登录", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*  设置主页布局  */
        setContentView(R.layout.activity_alice);

        /*  设置Alice已经启动  */
        userManager = UserManager.getInstance(alice);

        /*  初始化操作  */
        initView();
        initData();
        initRun();
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
