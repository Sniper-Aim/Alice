package cn.sniper.jpush;

import android.content.Context;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by Neo on 2016/12/15.
 */

public class JPush{
    static JPush operation = null;
    public static JPush getInstance(Context context, Boolean isDebug){
        if (operation == null){
            operation = new JPush(context,isDebug);
        }
        return operation;
    }
    private JPush(Context context, Boolean isDebug){
        JPushInterface.setDebugMode(isDebug);
        JPushInterface.init(context);
    }
}
