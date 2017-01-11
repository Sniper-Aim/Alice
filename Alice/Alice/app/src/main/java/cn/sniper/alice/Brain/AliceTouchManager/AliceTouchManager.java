package cn.sniper.alice.Brain.AliceTouchManager;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.view.Gravity;
import android.view.WindowManager;

import cn.sniper.alice.Brain.AliceTouchManager.AliceTouch.AliceTouch;
import cn.sniper.alice.Brain.AliceTouchManager.View.TouchView;

/**
 * Created by peisong on 2017/1/11.
 */


/**
 * 万能键
 */
public class AliceTouchManager {

    /**
     * 上下文
     */
    private static Context context;

    private WindowManager windowManager;

    private TouchView touchView;

    private WindowManager.LayoutParams layoutParams;

    private AliceTouch aliceTouch;

    private Point point;

    private int mScreenWidth = 0;
    private int mScreenHeight = 0;

    private static AliceTouchManager aliceTouchManager;

    public AliceTouchManager(Context context){
        this.context = context;
        this.aliceTouch = AliceTouch.getAliceTouch();
        this.windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);

        this.layoutParams = new WindowManager.LayoutParams();
        // 设置图片格式，效果为背景透明
        this.layoutParams.format = PixelFormat.RGBA_8888;
        // 设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
        this.layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 调整悬浮窗显示的停靠位置为左侧置�?
        this.layoutParams.gravity= Gravity.LEFT | Gravity.TOP;


        /**
         * 设置整个屏幕的宽高
         */
        aliceTouch.setScreenX(this.windowManager.getDefaultDisplay().getWidth());
        aliceTouch.setScreenY(this.windowManager.getDefaultDisplay().getHeight());

        /**
         * 屏幕为原点设置Touch的位置
         * 设置原点位置
         */
        this.layoutParams.x = (int)(aliceTouch.getScreenX() - aliceTouch.getTouchSize());
        this.layoutParams.y = (int)(aliceTouch.getScreenY() - aliceTouch.getTouchSize());
        aliceTouch.setTouchX(this.layoutParams.x);
        aliceTouch.setTouchY(this.layoutParams.y);

        // 设置悬浮窗口长宽数据
        this.layoutParams.width = (int)aliceTouch.getTouchSize();
        this.layoutParams.height = (int)aliceTouch.getTouchSize();
    }

    public void addView(TouchView floatView) {
        touchView = floatView;
        windowManager.addView(floatView, this.layoutParams);
    }

    public void setPosition(float x, float y){
        this.layoutParams.x = (int)x;
        this.layoutParams.y = (int)y;
        windowManager.updateViewLayout(touchView, layoutParams);
    }

    public static AliceTouchManager getAliceTouch(Context context){
        if (aliceTouchManager == null){
            return aliceTouchManager = new AliceTouchManager(context);
        }
        return aliceTouchManager;
    }
}
