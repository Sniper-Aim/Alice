package cn.sniper.alice.Brain.BrainViews.Chat;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class ChatViewPager extends ViewPager {
    private boolean noScroll = false;
    private Context context;
    
    @Override
	public void setOffscreenPageLimit(int limit) {
		// TODO Auto-generated method stub
		super.setOffscreenPageLimit(limit);
	}

	public ChatViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        // TODO Auto-generated constructor stub
    }


    public ChatViewPager(Context context) {
        super(context);
        this.context = context;
    }
 
    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }
 
    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }
 
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        /* return false;//super.onTouchEvent(arg0); */
        if (noScroll)
            return false;
        else
            return super.onTouchEvent(arg0);
    }
 
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (noScroll)
            return false;
        else
            return super.onInterceptTouchEvent(arg0);
    }
 
    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }
 
    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

	@Override
	public int getCurrentItem() {
		// TODO Auto-generated method stub
		return super.getCurrentItem();
	}

    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {
        super.onPageScrolled(position, offset, offsetPixels);
    }

    @Override
    public void setAnimation(Animation animation) {
        super.setAnimation(new AlphaAnimation(context, null));
    }
}