package cn.sniper.alice.ExternalTools.LocalStorage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by peisong on 2017/1/10.
 */


/**
 * 写本地数据类 SharedPreference
 */

public class SharedPreference {

    private static SharedPreference sharedPreferences;

    private static Context mContext;

    /**
     * 文件名统一声明在这里, 并且加上final
     */
    public enum FileName{
        APP_NAME
    }


    /**
     * Shared对象
     */
    private SharedPreferences sp;

    /**
     * 单例
     * @param context
     * @return
     */
    public static SharedPreference getInstance(Context context){
        mContext = context;
        if (sharedPreferences == null) {
            sharedPreferences = new SharedPreference();
        }
        return sharedPreferences;
    }


    /**
     * 写数据,
     * @param fileName  文件名
     * @param key       Key值
     * @param value     Value值
     */
    public void writeStr(String fileName, String key, String value){
        sp = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }


    /**
     * 写数据,
     * @param fileName  文件名
     * @param key       Key值
     * @param value     Value值
     */
    public void writeInt(String fileName, String key, int value){
        sp = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }


    /**
     * 写数据,
     * @param fileName  文件名
     * @param key       Key值
     * @param value     Value值
     */
    public void writeBol(String fileName, String key, Boolean value){
        sp = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 读数据
     * @param fileName  文件名
     * @param key       Key值
     * @return          Value值
     */
    public String readStr(String fileName, String key){
        SharedPreferences sp = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getString(key, null);
    }

    /**
     * 读数据
     * @param fileName  文件名
     * @param key       Key值
     * @return          Value值
     */
    public int readInt(String fileName, String key){
        SharedPreferences sp = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getInt(key, -1);
    }

    /**
     * 读数据
     * @param fileName  文件名
     * @param key       Key值
     * @return          Value值
     */
    public Boolean readBol(String fileName, String key){
        SharedPreferences sp = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }
}
