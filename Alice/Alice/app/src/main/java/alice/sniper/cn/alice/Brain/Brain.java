package alice.sniper.cn.alice.Brain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import alice.sniper.cn.alice.Hear.HearResult.Result;
import alice.sniper.cn.alice.Setting.SettingActivity.SettingActivity;

/**
 * Brain 大脑类, 主要的存在.
 *
 * Created by pei_song on 2016/12/21.
 */
public abstract class Brain extends FragmentActivity {

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
     * 得到结果集
     * @param msg
     * @return
     */
    public Result getMsgState(Message msg){
        Bundle bundle = msg.getData();
        Result result = (Result) bundle.getSerializable(MSG_STATE_BRAIN);
        return result;
    }


}