package cn.sniper.alice.ExternalTools.Logs;

import android.util.Log;

/**
 * Created by Lisa on 2017/1/4.
 */

public class Logger {
    /**
     * 获取运行栈的信息
     */
    private static StackTraceElement[] stacks = new Throwable().getStackTrace();
    /**
     *  Log开关
     */
    public static boolean IS_DEBUG = true;

    /**
     * 正常模式打印语句
     */
    public static void i(String iContent){
        if(IS_DEBUG) Log.i(" -> " + stacks[1].getFileName().substring(0, stacks[1].getFileName().length() - 5) +
                " : " + stacks[1].getMethodName() +
                " ("+stacks[1].getLineNumber() + ") ", iContent);
    }
    /**
     *  debug模式打印语句
     */
    public static void d(String dContent){
        if(IS_DEBUG) Log.d(" -> " + stacks[1].getFileName().substring(0, stacks[1].getFileName().length() - 5) +
                " : " + stacks[1].getMethodName() +
                " ("+stacks[1].getLineNumber() + ") ", dContent);
    }
    /**
     * error时打印语句
     */
    public static void e(String eContent){
        if(IS_DEBUG) Log.e(" -> " + stacks[1].getFileName().substring(0, stacks[1].getFileName().length() - 5) +
                " : " + stacks[1].getMethodName() +
                " ("+stacks[1].getLineNumber() + ") ", eContent);
    }

}
