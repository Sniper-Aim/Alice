package alice.sniper.cn.alice.HiActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import alice.sniper.cn.alice.Brain.Brain;
import alice.sniper.cn.alice.Brain.BrainInterFace.BrainInterFace;
import alice.sniper.cn.alice.ExternalTools.Logs.Logger;
import alice.sniper.cn.alice.Hear.Hear;
import alice.sniper.cn.alice.Hear.HearResult.Result;
import alice.sniper.cn.alice.R;
import alice.sniper.cn.alice.Setting.SettingActivity.SettingActivity;
import alice.sniper.cn.alice.Thought.Thought;

/**
 * Created by Lisa on 2017/1/5.
 */

public class SpeakFragment extends Fragment implements View.OnClickListener{
    /**
     * 消息标识, 在msg消息中传递的Key必须带此标识,  否则将不被识别
     */
    public static String MSG_STATE_BRAIN = "Brain";
    /**
     * 开始说话按钮
     */
    private Button start_say;
    /**
     * 听写类
     */
    private Hear hear;
    /**
     * 说话按钮控制
     */
    private Boolean or = true;
    TextView  start_say_tex;

    public SpeakFragment(){}

    /**
     * Handler 主要的消息处理部分
     */
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            /**
             * 解析序列化对象结果并还原成为封装好的结果类型
             */
            Result result = getMsgState(msg);

            /**
             * 根据不同的消息请求状态来执行不同的操作
             */
            switch (result.getState()){
                /**  默认  */
                case MSG_STATE:
                    break;

                /**  说话  */
                case SAY_TOAST:
                    if (result.getValues().equals("设置。")){
                        Intent intent = new Intent(getContext(), SettingActivity.class);
                        startActivity(intent);
                    }

                    Toast.makeText(getContext(), result.getValues(),Toast.LENGTH_LONG).show();
                    break;

                /**  跳转  */
                case INTENT_PACKAGE_NAME:

                    break;
            }
        }
    };

    /**
     * 得到结果集
     * @param msg
     * @return
     */
    public Result getMsgState(Message msg){
        Bundle bundle = msg.getData();
        Result result = (Result) bundle.getSerializable(MSG_STATE_BRAIN);
        return result;
    }
    /**
     * 单例模式
     */
    private static SpeakFragment speakFragment;
    public static SpeakFragment getInstance(){
        if(speakFragment==null){
            speakFragment=new SpeakFragment();
        }
        return speakFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.speak_fragment,container,false);
        start_say_tex= (TextView) view.findViewById(R.id.start_say_tex);
        /* 开始说话按钮 */
        start_say = (Button)view.findViewById(R.id.start_say);
        start_say.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /**
         * 初始化各项类对象
         */
        hear = new Hear(getContext());
          /* 初始化 开始说话按钮 文字 */
        start_say.setText(R.string.StartSay);
        setEvent();
    }

    /**
     * 设置所有事件
     */
    public void setEvent(){
        Thought.ToastSay(new BrainInterFace.ToastSay() {
            @Override
            public void Say(String str) {
                /**
                 * 调用继承的Brain类handler发送一条消息进行处理
                 * 这里是一个接口的回调, 有了结果就会回调这个方法,
                 * 然后这个方法再向Brain的Handler发送一个讲话消息, 将结果发送给Brain处理
                 */
                handler.sendMessage(msg(Brain.MSG_STATE_BRAIN, new Result("", str, Brain.MSG_STATE.SAY_TOAST)));
            }
        });

        /**
         * 设置开始说话按钮的点击事件
         */
        start_say_tex.setOnTouchListener(new View.OnTouchListener() {
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
    }

    @Override
    public void onClick(View view) {

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

    /**
     * 提供一个对外的消息类型, 对外需要遵循这个类型给我传递参数, 这个类型最终将传递给本类的Handler进行处理
     * @param key
     * @param result
     * @return
     */
    public Message msg(String key, Result result){
        Message msg= new Message();
        Bundle bundle = new Bundle();
        bundle.putSerializable(key, result);
        msg.setData(bundle);
        return msg;
    }

}
