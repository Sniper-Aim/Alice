package cn.sniper.jpush.AUser;

import android.content.Context;
import android.util.Log;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

/**
 * Created by peisong on 2017/1/12.
 */

public class AUser {
    private static AUser aUser;
    private static Context context;



    public void registerUser(String name, String pwd){
        JMessageClient.register(name, pwd, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if (isOK(i)){
                    Log.e("AUser"+i,"注册成功"+s);
                }else{
                    Log.e("AUser"+i,"注册失败"+s);
                }
            }
        });
    }

    public void login(String name, String pwd){
        JMessageClient.login(name, pwd, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if (isOK(i)){
                    Log.e("AUser"+i,"登录成功"+s);
                }else{
                    Log.e("AUser"+i,"登录失败"+s);
                }
            }
        });
    }

    public void getMyInfo(){
        UserInfo myInfo = JMessageClient.getMyInfo();
    }





    public AUser(Context context){
        AUser.context = context;
    }

    private Boolean isOK(int i){
        if (i == 0)
            return true;
        else
            return false;
    }

    public static AUser getInstance(Context context){
        if (aUser == null){
            aUser = new AUser(context);
        }
        return aUser;
    }


}
