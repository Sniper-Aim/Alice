package cn.sniper.alice.Brain.MyApplication;

import android.app.Application;
import android.view.WindowManager;

import cn.sniper.alice.ExternalTools.JPush.JPush;

/**
 * Created by pei song on 2017/1/11.
 */

public class MyApplication extends Application{
    /**
     * 创建全局变量
     * 全局变量一般都比较倾向于创建一个单独的数据类文件，并使用static静态变量
     *
     * 这里使用了在Application中添加数据的方法实现全局变量
     * 注意在AndroidManifest.xml中的Application节点添加android:name=".MyApplication"属性
     *
     */

    private WindowManager.LayoutParams wmParams=new WindowManager.LayoutParams();


    public WindowManager.LayoutParams getMywmParams(){
        return wmParams;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        JPush.startJPush(this, true);
    }
}
