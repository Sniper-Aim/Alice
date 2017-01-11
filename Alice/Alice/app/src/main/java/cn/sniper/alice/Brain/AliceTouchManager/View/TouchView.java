package cn.sniper.alice.Brain.AliceTouchManager.View;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import cn.sniper.alice.Brain.AliceTouchManager.AliceTouch.AliceTouch;
import cn.sniper.alice.Brain.AliceTouchManager.AliceTouchManager;
import cn.sniper.alice.R;

/**
 * Created by peisong on 2017/1/11.
 */

public class TouchView extends View{

    private static TouchView touchView;

    private AliceTouch aliceTouch;

    private AliceTouchManager aliceTouchManager;

    private float rex;
    private float rey;

    private float this_x;
    private float this_y;

    public TouchView(Context context) {
        super(context);
        this.setBackgroundResource(R.mipmap.alice_switch_say_button);
        aliceTouch = AliceTouch.getAliceTouch();
        aliceTouchManager = AliceTouchManager.getAliceTouch(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        this_x = event.getRawX();
        this_y = event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                aliceTouch.setDownX(this_x);
                aliceTouch.setDownY(this_y);
                rex = (this_x - aliceTouch.getTouchX());
                rey = (this_y - aliceTouch.getTouchY());
                aliceTouch.setDownTime(System.currentTimeMillis());

                break;
            case MotionEvent.ACTION_MOVE:

                aliceTouchManager.setPosition(this_x - rex, this_y - rey);
                break;

            case MotionEvent.ACTION_UP:
                aliceTouch.setTouchX((this_x - rex));
                aliceTouch.setTouchY((this_y - rey));
                aliceTouch.setUpX(this_x);
                aliceTouch.setUpY(this_y);
                aliceTouch.setUpTime(System.currentTimeMillis());
                break;
        }
        return true;
    }

    public static TouchView getTouchView(Context context){
        if (touchView == null){
            touchView = new TouchView(context);
        }
        return touchView;
    }

}