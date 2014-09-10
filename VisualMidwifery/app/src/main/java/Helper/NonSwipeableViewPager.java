package Helper;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by liub3 on 24/04/14.
 */
public class NonSwipeableViewPager extends ViewPager {
    private boolean enabled;

    public NonSwipeableViewPager(Context context) {
        super(context);
        this.enabled = true;
    }

    public NonSwipeableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        // Never allow swiping to switch between pages
        if (this.enabled) {
            return super.onInterceptTouchEvent(arg0);
        }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        if (this.enabled) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
