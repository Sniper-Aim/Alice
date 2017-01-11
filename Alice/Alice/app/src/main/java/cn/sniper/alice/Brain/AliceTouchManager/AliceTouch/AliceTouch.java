package cn.sniper.alice.Brain.AliceTouchManager.AliceTouch;

/**
 * Created by peisong on 2017/1/11.
 */

/**
 * 万能键
 */
public class AliceTouch {

    private static AliceTouch aliceTouch;

    private float TouchSize = 200;

    private long downTime;

    private long upTime;

    private float downX;
    private float downY;

    private float upX;
    private float upY;

    private float TouchX;
    private float TouchY;

    private float screenX;
    private float screenY;


    public long getDownTime() {
        return downTime;
    }

    public void setDownTime(long downTime) {
        this.downTime = downTime;
    }

    public long getUpTime() {
        return upTime;
    }

    public void setUpTime(long upTime) {
        this.upTime = upTime;
    }

    public float getDownX() {
        return downX;
    }

    public void setDownX(float downX) {
        this.downX = downX;
    }

    public float getDownY() {
        return downY;
    }

    public void setDownY(float downY) {
        this.downY = downY;
    }

    public float getUpX() {
        return upX;
    }

    public void setUpX(float upX) {
        this.upX = upX;
    }

    public float getUpY() {
        return upY;
    }

    public void setUpY(float upY) {
        this.upY = upY;
    }

    public float getScreenX() {
        return screenX;
    }

    public void setScreenX(float screenX) {
        this.screenX = screenX;
    }

    public float getScreenY() {
        return screenY;
    }

    public void setScreenY(float screenY) {
        this.screenY = screenY;
    }

    public float getTouchSize() {
        return TouchSize;
    }

    public void setTouchSize(float touchSize) {
        TouchSize = touchSize;
    }

    public float getTouchX() {
        return TouchX;
    }

    public void setTouchX(float touchX) {
        TouchX = touchX;
    }

    public float getTouchY() {
        return TouchY;
    }

    public void setTouchY(float touchY) {
        TouchY = touchY;
    }

    public static AliceTouch getAliceTouch(){
        if (aliceTouch == null){
            aliceTouch = new AliceTouch();
        }
        return aliceTouch;
    }
}
