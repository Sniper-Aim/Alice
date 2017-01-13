package cn.sniper.alice.Brain.BrainViews;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by peisong on 2017/1/13.
 */

public class MyListView extends ListView {

    private int mScreenWidth; // 屏幕宽度
    private int mDownX; // 按下点的x值
    private int mDownY; // 按下点的y值

    private int moveSize;
    private int itemy;

    private Boolean anisOver = true;

    private ViewGroup mPointChild; // 当前处理的item

    private LinearLayout.LayoutParams mLayoutParams; // 当前处理的item的LayoutParams

    public MyListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        // 获取屏幕宽度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        mScreenWidth = dm.widthPixels;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (anisOver) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    performActionDown(ev);
                    break;
                case MotionEvent.ACTION_MOVE:
                    performActionMove(ev);
                    break;
                case MotionEvent.ACTION_UP:
                    performActionUp();
                    break;
            }
        }
        return super.onTouchEvent(ev);
    }

    // 处理action_down事件
    private void performActionDown(MotionEvent ev) {

        mDownX = (int) ev.getX();
        mDownY = (int) ev.getY();

        // 获取当前点的item
        mPointChild = (ViewGroup) getChildAt(pointToPosition(mDownX, mDownY) - getFirstVisiblePosition());

    }

    // 处理action_move事件
    private void performActionMove(MotionEvent ev) {
        int nowX = (int) ev.getX();
        int nowY = (int) ev.getY();

    }

    // 处理action_up事件
    private void performActionUp() {

    }
}