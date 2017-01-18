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
import cn.sniper.alice.Brain.BrainInterFace.OnBrain;
import cn.sniper.alice.Brain.BrainView.Adapter.FragmentAdapter.HiFragmentAdapter;
import cn.sniper.alice.Brain.BrainViews.Chat.ChatActivity;
import cn.sniper.alice.Brain.BrainViews.HiViewPager;
import cn.sniper.alice.Brain.BrainViews.User.SignActivity;
import cn.sniper.alice.Brain.Task.Task;
import cn.sniper.alice.ExternalTools.JPush.UserManager;
import cn.sniper.alice.ExternalTools.LocalStorage.SharedPreference;
import cn.sniper.alice.ExternalTools.Logs.Logger;


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
                Logger.e("开始登录"+id+pwd);
                userManager.login(id, pwd, new OnBrain.SignOver() {
                    @Override
                    public void over(Boolean isOver) {
                        Alice.setISRUN(true);
                        Logger.e("initRun() - 登录成功");
                    }
                });
            }else{
//                Intent intent4 = new Intent(alice, SignActivity.class);
//                startActivity(intent4);
            }
        }else{
//            Intent intent4 = new Intent(alice, SignActivity.class);
//            startActivity(intent4);
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
                Toast.makeText(this, "聊天", Toast.LENGTH_SHORT).show();

                new Task(){
                    @Override
                    public void init() {
                        Logger.e("init");
                    }

                    @Override
                    public void start() {
                        Logger.e("start");
                        for (int i=0; i< 5; i++){
                            try {
                                Thread.sleep(500);
                                aupdate();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void update() {
                        Logger.e("update");
                        speak_btn.setText("");
                    }

                    @Override
                    public void exit() {
                        Logger.e("exit");
                    }
                };

                break;

            case R.id.test2:
                if (Alice.getISRUN()) {
                    Toast.makeText(this, "好友列表", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(alice, ChatActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.test3:
                if (Alice.getISRUN()) {
                    Toast.makeText(this, "申请加好友", Toast.LENGTH_SHORT).show();
//                    userManager.acceptInvitation("123456", "");
                }else {
                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                }
                    break;

            case R.id.test4:
                if (!Alice.getISRUN()) {
                    Intent intent4 = new Intent(alice, SignActivity.class);
                    startActivity(intent4);
                    Toast.makeText(this, "登录", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "已登录", Toast.LENGTH_SHORT).show();
                }
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
