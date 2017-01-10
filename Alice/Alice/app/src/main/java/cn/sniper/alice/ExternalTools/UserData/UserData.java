package cn.sniper.alice.ExternalTools.UserData;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by peisong on 2017/1/10.
 */


/**
 * 用户数据
 */
public class UserData {

    /**
     * 获取手机号码, 有部分手机是获取不到的, 这跟手机运营商和手机卡有关
     * @param context
     */
    public String getPhoneNumber(Context context){
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getLine1Number();
    }
}
