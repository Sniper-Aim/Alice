package cn.sniper.alice.ExternalTools.Animation;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import cn.sniper.alice.R;


/**
 * Created by peisong on 2017/1/4.
 */

/**
 * 动画类, 在这里有各种动画效果
 */
public class Animatiom {

    /**
     * 动画类
     */
    private static Animation animation;


    private static AnimationOver.Over over;

    /**
     * 透明 0.5s 动画,
     * @param context 上下文
     * @param v  布局 | 控件
     * @param fillAfter 是否定格在动画最后
     */
    public static void Alpha05(Context context, View v, Boolean fillAfter, AnimationOver.Over over){
        animation = AnimationUtils.loadAnimation(context, R.anim.alpha_0_5s);
        animation.setFillAfter(fillAfter);
        v.startAnimation(animation);
        if (over != null)
            Evnet(animation, over);
    }

    /**
     * 1s
     */
    public static void Alpha1(Context context, View v, Boolean fillAfter, AnimationOver.Over over){
        animation = AnimationUtils.loadAnimation(context, R.anim.alpha_1s);
        animation.setFillAfter(fillAfter);
        v.startAnimation(animation);
        if (over != null)
            Evnet(animation, over);
    }

    /**
     * 0.5s 反向透明, 也就是将透明的变成不透明
     */
    public static void BackAlpha05(Context context, View v, Boolean fillAfter, AnimationOver.Over over){
        animation = AnimationUtils.loadAnimation(context, R.anim.alpha_back_0_5s);
        animation.setFillAfter(fillAfter);
        v.startAnimation(animation);
        if (over != null)
            Evnet(animation, over);
    }

    /**
     * 1s
     */
    public static void BackAlpha1(Context context, View v, Boolean fillAfter, AnimationOver.Over over){
        animation = AnimationUtils.loadAnimation(context, R.anim.alpha_back_1s);
        animation.setFillAfter(fillAfter);
        v.startAnimation(animation);
        if (over != null)
            Evnet(animation, over);
    }


    /**
     * 移动动画
     */
    public static void MoveView(View v, float fromXDelta, float toXDelta,float fromYDelta, float toYDelta, AnimationOver.Over over){
        animation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
        animation.setDuration(300);
        v.startAnimation(animation);
        if (over != null)
            Evnet(animation, over);
    }


    public static void Evnet(Animation alpha, final AnimationOver.Over over){
        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                over.over();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
