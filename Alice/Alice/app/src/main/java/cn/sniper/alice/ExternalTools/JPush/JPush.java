package cn.sniper.alice.ExternalTools.JPush;

import android.content.Context;

/**
 * Created by pei_song on 2017/1/3.
 */


/**
 * JPush 操作类
 */
public class JPush {

    /**
     * 开启JPush 传入上下文参数
     * @param context
     */
    public static void startJPush(Context context){
        cn.sniper.jpush.JPush.getInstance(context, true);
    }

}