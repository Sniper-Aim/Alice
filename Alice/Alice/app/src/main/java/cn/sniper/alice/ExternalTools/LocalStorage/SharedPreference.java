package cn.sniper.alice.ExternalTools.LocalStorage;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

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
        APP_NAME,

        /** 保存的用户名文件名 */
        SAVE_USERNAME,

        /** 保存的密码文件名 */
        SAVE_PASSWORD,

        /** 密码保存状态文件名 */
        SAVE_IS_USERNAME
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
        editor.apply();
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
     * 删除数据
     * @param fileName
     * @param key
     */
    public void deleteStr(String fileName, String key){
        sp = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    /**----------------------------------------------------------------------------------*/

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
        editor.apply();
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

    /**----------------------------------------------------------------------------------*/


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
        editor.apply();
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

    /**----------------------------------------------------------------------------------*/


    /**
     * 获取文件名字里面的字段个数
     * @param fileName
     * @return
     */
    public int getSize(String fileName){
        SharedPreferences sp = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Map<String, ?> all = sp.getAll();
        return all.size();
    }
}
