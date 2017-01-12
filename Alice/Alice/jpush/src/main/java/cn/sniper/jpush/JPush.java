package cn.sniper.jpush;

import android.content.Context;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;


/**
 * Created by Neo on 2016/12/15.
 */

public class JPush{
    private static JPush operation = null;
    public static JPush getInstance(Context context, Boolean isDebug){
        if (operation == null){
            operation = new JPush(context,isDebug);
        }
        return operation;
    }
    private JPush(Context context, Boolean isDebug){
        JPushInterface.setDebugMode(isDebug);
        JMessageClient.setDebugMode(isDebug);

        JPushInterface.init(context);
        JMessageClient.init(context);
    }
}
