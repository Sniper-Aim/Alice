package cn.sniper.alice.Brain.MyApplication;

import android.app.Application;

import cn.sniper.alice.ExternalTools.JPush.UserManager;

/**
 * Created by pei song on 2017/1/11.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 初始化JPush和JMessage
         */
        UserManager.startJPush(this, true);
    }
}
