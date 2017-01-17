package cn.sniper.alice.Thought;

/**
 * Created by pei_song on 2016/12/26.
 */


import cn.sniper.alice.Brain.BrainInterFace.OnBrain;
import cn.sniper.alice.ExternalTools.Logs.Logger;
import cn.sniper.alice.Hear.HearResult.HearResult;

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
     * 声明大脑的说话接口 OnBrain -> Say 接口
     */
    private static OnBrain.ToastSay brainSay;

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

                    /**
                     * 打印一次结果
                     */
                    Logger.i(HearResult.getResult().toString());

                    /**
                     * 将结果传递到需要的地方 TODO 这个结果来自用户的口述, 需要分析后给出合理的状态和答案
                     */
                    brainSay.Say(HearResult.getResult().toString());

                    /**
                     * 将是否结果是否接收完的属性初始化!
                     */
                    HearResult.setIsResult(false);
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Logger.i("思想线程错误");
            e.printStackTrace();
        }
    }

    public static void setThoughtIsRun(Boolean isRun){
        Thought.isRun = isRun;
    }

    public static void ToastSay(OnBrain.ToastSay brainSay){
        Thought.brainSay = brainSay;
    }

}
