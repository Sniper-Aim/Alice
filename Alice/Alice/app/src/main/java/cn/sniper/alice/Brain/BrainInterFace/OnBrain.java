package cn.sniper.alice.Brain.BrainInterFace;

/**
 * Created by Administrator on 2017/1/1.
 */

public
interface OnBrain {
    interface ToastSay{
        void Say(String str);
    }

    interface SignOver{
        void over(Boolean isOver);
    }
}
