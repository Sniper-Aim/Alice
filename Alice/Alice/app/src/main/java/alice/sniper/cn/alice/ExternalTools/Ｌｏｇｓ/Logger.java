package alice.sniper.cn.alice.ExternalTools.Ｌｏｇｓ;

import android.util.Log;

/**
 * Created by Lisa on 2017/1/4.
 */

public class Logger {
    /**
     * 获取运行栈的信息
     */
    static StackTraceElement[] stacks = new Throwable().getStackTrace();
    /**
     *  Log开关
     */
    private static boolean ISDEBUG;

    /**
     * 正常模式打印语句
     */
    public static void i(String Icontent){
        if(ISDEBUG) Log.i(stacks[1].getClassName()+stacks[1].getMethodName()+"("+stacks[1].getLineNumber()+")",Icontent);
    }
    /**
     *  debug模式打印语句
     */
    public static void d(String Dcontent){
        if(ISDEBUG) Log.i(stacks[1].getClassName() + stacks[1].getMethodName() + "(" + stacks[1].getLineNumber() + ")", Dcontent);
    }
    /**
     * error时打印语句
     */
    public static void e(String Econtent){
        if(ISDEBUG) Log.i(stacks[1].getClassName() + stacks[1].getMethodName() + "(" + stacks[1].getLineNumber() + ")", Econtent);
    }

}
