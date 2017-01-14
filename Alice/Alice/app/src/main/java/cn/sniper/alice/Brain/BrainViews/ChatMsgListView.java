package cn.sniper.alice.Brain.BrainViews;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import cn.sniper.alice.ExternalTools.Logs.Logger;
import cn.sniper.alice.R;

/**
 * Created by peisong on 2017/1/12.
 */

public class ChatMsgListView extends ListView{


    private Context context;
    /**
     * 按下的位置
     */
    private int downX;
    private int downY;
    /**
     * 整个Item 包括所有
     */
    private View view;
    private LinearLayout rootview;

    /**
     * Item,  但是不包括隐藏按钮
     * 否则会被一起滑动
     */
    private ScrollView scrollView;

    /**
     * 隐藏按钮
     */
    private TextView btn1;
    private TextView btn2;

    /**
     * 隐藏按钮的大小
     */
    private int ButtonWidth;

    /**
     * 正在滑动的距离
     */
    private int move;

    /**
     * 松开后还要继续滑动的距离
     */
    private int nextMove;

    private OnClickDelete onClickDelete;
    private OnClickOKReName onClickOKReName;

    private OnClickItem onClickItem;

    private Boolean isAnimo = false;

    private Boolean isexistBtn = false;

    private OnClickListener onClickListener;
    private OnClickListener onClickListeneritem;

    public ChatMsgListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChatMsgListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }


    public interface OnClickOKReName{
        void OnClick(int i);
    }
    public interface OnClickDelete{
        void OnClick(int i);
    }
    public interface OnClickItem{
        void OnClick(int i);
    }

    public void setOnClick(OnClickOKReName onClick){
        this.onClickOKReName = onClick;
    }
    public void setOnClick(OnClickDelete onClick){
        this.onClickDelete = onClick;
    }
    public void setItemOnClick(OnClickItem onClick){
        this.onClickItem = onClick;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!isAnimo) {
            switch (ev.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    //如果当前存在按钮的话就先判断是不是点击了按钮
                    Logger.e("点击的"+ev.getX()+"---"+ev.getY());

                    downX = (int) ev.getX();
                    downY = (int) ev.getY();

                    view = getChildAt((pointToPosition(downX, downY) - getFirstVisiblePosition()));

                    rootview = (LinearLayout) view.findViewById(R.id.chat_listview_layout_);

                    btn1 = (TextView) view.findViewById(R.id.btn1);
                    btn1.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onClickDelete.OnClick(getPositionForView(view));
                        }
                    });
                    btn2 = (TextView) view.findViewById(R.id.btn2);
                    btn2.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onClickOKReName.OnClick(getPositionForView(view));

                        }
                    });


                    scrollView = (ScrollView) view.findViewById(R.id.scrollview);

                    ButtonWidth = btn1.getWidth();
                    Logger.e("ACTION_DOWN: " + ev.getX() + "-" + ev.getY() + "--------" + scrollView.getX() + ":" + scrollView.getY());
                    break;

                case MotionEvent.ACTION_UP:
                    int upx = (int) ev.getX();
                    int upy = (int) ev.getY();

                    if (Math.abs(move) > ButtonWidth) {
                        nextMove = (ButtonWidth * 2) - Math.abs(move);
                        open();
                    } else {
                        close();
                    }
                    Logger.e("ACTION_UP: " + ev.getX() + "--"+downX+"--"+downY+"---" + ev.getY());
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (downX > ev.getX()) {
                        move = (int) ev.getX() - downX;
                        if (Math.abs(move) < ButtonWidth * 2)
                            scrollView.setX(move);
                        Logger.e("ACTION_MOVE: " + ev.getX() + "-" + ev.getY());
                    }
                    return false;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    if (scrollView.getX() >= -(ButtonWidth *2)) {
                        scrollView.setX(scrollView.getX() - 1);
                    }else {
                        move = 0;
                        isAnimo = false;
                        isexistBtn = true;
                    }
                    break;

                case 1:
                    if (scrollView.getX() <= -1) {
                        scrollView.setX(scrollView.getX() + 1);
                    }else {
                        isAnimo = false;
                        isexistBtn = false;
                    }
                    break;
            }
        }
    };

    private void open(){
        isAnimo = true;
        new Thread(){
            @Override
            public void run() {
                while (isAnimo) {
                    try {
                        Message message0 = new Message();
                        message0.what = 0;
                        handler.sendMessage(message0);
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private void close(){
        isAnimo = true;
        new Thread(){
            @Override
            public void run() {
                while (isAnimo) {
                    try {
                        Message message1 = new Message();
                        message1.what = 1;
                        handler.sendMessage(message1);
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
