package alice.sniper.cn.alice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zcw.togglebutton.ToggleButton;

import alice.sniper.cn.alice.Alice.Alice;
import alice.sniper.cn.alice.Brain.Brain;
import alice.sniper.cn.alice.Brain.BrainInterFace.BrainInterFace;
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
     * 开始说话按钮
     */
    private Button start_say;

    /**
     * 主页显示Text
     */
    private TextView start_say_tex;

    /**
     * 说话按钮控制
     */
    public Boolean or = true;

    /**
     * 听写类
     */
    private Hear hear;

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
                if (or) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        start_say.setBackgroundResource(R.color.button_Start);
                        start_say_tex.setText(R.string.EndSay);
                        hear.start();
                    }

                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        start_say.setBackgroundResource(R.color.button_End);
                        start_say_tex.setText(R.string.StartSay);
                        hear.stop();
                        or = false;
                    }
                }
                return true;
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
        /* 开关 */
        open_btn = (ToggleButton) findViewById(R.id.open_btn);

        /* 屏幕中间大标题 */
        start_say_tex = (TextView) findViewById(R.id.start_say_tex);

        /* 开始说话按钮 */
        start_say = (Button) findViewById(R.id.start_say);

        /* --------------------------------------------------------------------------------------- */
        /* 初始化 开始说话按钮 文字 */
        start_say.setText(R.string.StartSay);


        /**
         * 初始化各项类对象
         */
        hear = new Hear(alice);

    }


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

}