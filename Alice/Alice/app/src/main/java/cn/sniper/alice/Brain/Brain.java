package cn.sniper.alice.Brain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import cn.sniper.alice.Hear.HearResult.Result;
import cn.sniper.alice.Setting.SettingActivity.SettingActivity;


/**
 * Brain 大脑类, 主要的存在.
 *
 * Created by pei_song on 2016/12/21.
 */
public abstract class Brain extends Activity{

    private static String TAG = "Brain";

    /**
     * 枚举所有消息类型
     */
    public enum MSG_STATE{

        /**
         * 默认类型, 没有任何操作
         */
        MSG_STATE,

        /**
         * 用来表示说话类型, 方式是Toast
         */
        SAY_TOAST,

        /**
         * 用来表示根据包名跳转到某个应用
         */
        INTENT_PACKAGE_NAME
    }

    /**
     * 声明类型变量, 用于switch
     */
    MSG_STATE msg_State = MSG_STATE.MSG_STATE;

    /**
     * 消息标识, 在msg消息中传递的Key必须带此标识,  否则将不被识别
     */
    public static String MSG_STATE_BRAIN = "Brain";

    /**
     * Brain The Context, 提供给继承者对自己的控制权.
     */
    public Context alice = this;

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
//                case MSG_STATE:
//                    break;

                /**  说话  */
                case SAY_TOAST:
                    if (result.getValues().equals("设置。")){
                        Intent intent = new Intent(alice, SettingActivity.class);
                        startActivity(intent);
                    }

                    Toast.makeText(alice, result.getValues(),Toast.LENGTH_LONG).show();
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