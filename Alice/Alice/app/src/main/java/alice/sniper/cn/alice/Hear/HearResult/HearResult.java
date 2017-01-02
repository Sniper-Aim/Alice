package alice.sniper.cn.alice.Hear.HearResult;

/**
 * Created by Administrator on 2017/1/1.
 */

/**
 * 听写说话结果类
 */
public class HearResult {
    /**
     * 结果
     */
    private static StringBuffer Result = null;

    /**
     * 当前是否已经有了结果
     */
    private static Boolean isResult = false;

    /**
     * 初始化结果
     */
    public static void initResult(){
        if (Result == null)
            Result = new StringBuffer();
        Result.delete(0, Result.length());
    }

    public static void setResult(String str){
        Result.append(str);
    }

    public static StringBuffer getResult(){
        return Result;
    }

    public static Boolean isResult(){
        return isResult;
    }

    public static void setIsResult(Boolean isResult){
        HearResult.isResult = isResult;
    }

}
