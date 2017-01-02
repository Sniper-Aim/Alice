package alice.sniper.cn.alice.Thought;

/**
 * Created by pei_song on 2016/12/26.
 */


import android.util.Log;

import alice.sniper.cn.alice.Brain.BrainInterFace.BrainInterFace;
import alice.sniper.cn.alice.Hear.HearResult.HearResult;

/**
 * 思想主类, 用来思考处理何种问题
 */
public class Thought extends Thread{

    private static String TAG = "Thought";

    /**
     * 标识思想是否运行, 若设置为false 那么思想的 while 就会停止
     */
    private static Boolean isRun = true;

    /**
     * 声明大脑的说话接口 BrainInterFace -> Say 接口
     */
    private static BrainInterFace.ToastSay brainSay;

    @Override
    public void run() {
        try {

            /**
             * 思想的循环状态
             */
            while (isRun) {

                /**
                 * 是否已经有结果,  有的话将通知大脑处理这个结果
                 */
                if (HearResult.isResult()) {
                    Log.e(TAG, "" + HearResult.getResult());
                    brainSay.Say(HearResult.getResult().toString());
                    HearResult.setIsResult(false);
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Log.e(TAG,"思想线程错误");
            e.printStackTrace();
        }
    }

    public static void setThoughtIsRun(Boolean isRun){
        Thought.isRun = isRun;
    }

    public static void BrainSay(BrainInterFace.ToastSay brainSay){
        Thought.brainSay = brainSay;
    }
}
