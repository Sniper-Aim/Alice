package alice.sniper.cn.alice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zcw.togglebutton.ToggleButton;

import alice.sniper.cn.alice.Alice.Alice;
import alice.sniper.cn.alice.Brain.Brain;
import alice.sniper.cn.alice.Brain.BrainInterFace.BrainInterFace;
import alice.sniper.cn.alice.ExternalTools.Animation.Animatiom;
import alice.sniper.cn.alice.ExternalTools.Animation.AnimationOver;
import alice.sniper.cn.alice.ExternalTools.Logs.Logger;
import alice.sniper.cn.alice.Hear.Hear;
import alice.sniper.cn.alice.Hear.HearResult.Result;
import alice.sniper.cn.alice.Thought.Thought;


/**
 * Created by pei_song on 2016/12/26.
 */

public class Hi extends Brain{

    private static String TAG = "Hi";

    /**
     * Alice的主要开关
     */
    private ToggleButton open_btn;

    /**
     * 打字输入布局
     */
    private RelativeLayout main_input_layout;

    /**
     * 说话输入布局
     */
    private RelativeLayout main_say_layout;

    /**
     * 开始说话按钮
     */
    private Button start_say;

    /**
     * 切换输入按钮
     */
    private Button main_switch_input_button;

    /**
     * 切换说话按钮
     */
    private Button main_switch_say_button;

    /**
     * 主页显示Text
     */
    private TextView start_say_tex;

    /**
     * 说话按钮控制
     */
    private Boolean or = true;

    /**
     * 当前停留的输入界面
     */
    private enum PAGE{
        /** 说话页面 */
        SAY,
        /** 输入页面 */
        INPUT
    }
    public static PAGE page;

    /**
     * 是否正在切换页面
     */
    private Boolean isSwitchPage = false;

    /**
     * 听写类
     */
    private Hear hear;

    /**
     * 设置所有事件
     */
    public void setEvent(){



        main_say_layout.setVisibility(View.VISIBLE);
        main_input_layout.setVisibility(View.GONE);


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

        Thought.ToastSay(new BrainInterFace.ToastSay() {
            @Override
            public void Say(String str) {
                /**
                 * 调用继承的Brain类handler发送一条消息进行处理
                 * 这里是一个接口的回调, 有了结果就会回调这个方法,
                 * 然后这个方法再向Brain的Handler发送一个讲话消息, 将结果发送给Brain处理
                 */
                handler.sendMessage(msg(Brain.MSG_STATE_BRAIN, new Result("", str, MSG_STATE.SAY_TOAST)));
            }
        });

        /**
         * 设置开始说话按钮的点击事件
         */
        start_say.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Logger.i("我在测试点击哦6666666666666666666666");

                if (or) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        startSay();
                    }

                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        stopSay();
                        or = false;
                    }
                }
                return true;
            }
        });

        /**
         * 切换输入按钮
         */
        main_switch_input_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* 切换页面 */
                switchPage();
            }
        });

        main_switch_say_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* 切换页面 */
                switchPage();
            }
        });
    }



    /**
     * 初始化布局
     */
    public void initView() {
        /**
         * 初始化操作
         */

        /* 文字输入布局 */
        main_input_layout = (RelativeLayout) findViewById(R.id.main_input_layout);

        /* 说话输入布局 */
        main_say_layout = (RelativeLayout) findViewById(R.id.main_say_layout);

        /* 开关 */
        open_btn = (ToggleButton) findViewById(R.id.open_btn);

        /* 屏幕中间大标题 */
        start_say_tex = (TextView) findViewById(R.id.start_say_tex);

        /* 开始说话按钮 */
        start_say = (Button) findViewById(R.id.start_say);

        /* 切换输入按钮 */
        main_switch_input_button = (Button) findViewById(R.id.main_switch_input_button);

        /* 切换说话按钮 */
        main_switch_say_button = (Button) findViewById(R.id.main_switch_say_button);

        /* --------------------------------------------------------------------------------------- */
        /* 初始化 开始说话按钮 文字 */
        start_say.setText(R.string.StartSay);

        /**
         * 初始化各项类对象
         */
        hear = new Hear(alice);

    }


    /**---------上为初始化以及设置事件-----------*/

    /**
     * 切换主页页面的方法
     */
    public void switchPage(){
        if (!isSwitchPage) {

            /**
             * 这里枚举当前停留的页面
             */
            switch (page) {
                case SAY:
                    /**
                     * 设置动画效果, 最后一个参数为动画结束后的回调函数执行什么
                     * 首先将切换状态设置为true 然后在回调内做自己的事情再将切换状态设置为false
                     */
                    isSwitchPage = true;
                    Animatiom.Alpha05(alice, main_say_layout, true, new AnimationOver.Over() {
                        @Override
                        public void over() {
                                /* 动画效果结束后执行的代码 */

                            page = PAGE.INPUT;
                            isSwitchPage = false;

                    main_input_layout.setVisibility(View.VISIBLE);
                    main_say_layout.setVisibility(View.GONE);

                        }
                    });
                    break;
                case INPUT:
                    isSwitchPage = true;
                    Animatiom.BackAlpha05(alice, main_say_layout, true, new AnimationOver.Over() {
                        @Override
                        public void over() {
                                /* 动画效果结束后执行的代码 */

                            page = PAGE.SAY;
                            isSwitchPage = false;

                    main_say_layout.setVisibility(View.VISIBLE);
                    main_input_layout.setVisibility(View.GONE);
                        }
                    });
                    break;
            }
        }
    }


    /**
     * 开始说话方法
     */
    public void startSay(){
        start_say.setBackgroundResource(R.color.button_Start_Color);
        start_say_tex.setText(R.string.EndSay);
        hear.start();
    }


    /**
     * 结束说话方法
     */
    public void stopSay(){
        start_say.setBackgroundResource(R.color.button_End_Color);
        start_say_tex.setText(R.string.StartSay);
        hear.stop();
    }


    /**---------下为周期方法-----------*/


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*  设置主页布局  */
        setContentView(R.layout.activity_alice);

        /*  设置Alice已经启动  */
        Alice.setISRUN(true);

        /*  当前页面设置为说话页面  */
        page = PAGE.SAY;

        /*  初始化操作  */
        initView();
        setEvent();
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

    }
}
