package com.example.wupeixin.connerdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.facebook.drawee.view.SimpleDraweeView;


public class SelectRadiusLoaderImageView extends SimpleDraweeView {
    public SelectRadiusLoaderImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public SelectRadiusLoaderImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SelectRadiusLoaderImageView(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
    }


    // UI
    private Drawable foreground;

    private boolean foregroundBoundsChanged = false;

    public void setForeground(Drawable drawable) {
        if (foreground != drawable) {
            if (foreground != null) {
                foreground.setCallback(null);
                unscheduleDrawable(foreground);
            }

            foreground = drawable;

            if (drawable != null) {
                setWillNotDraw(false);
                drawable.setCallback(this);
            } else {
                setWillNotDraw(true);
            }
            requestLayout();
            invalidate();
        }
    }

    /**
     * Returns the drawable used as the foreground of this layout. The foreground drawable,
     * if non-null, is always drawn on top of the children.
     *
     * @return A Drawable or null if no foreground was set.
     */
    public Drawable getForeground() {
        return foreground;
    }


    @Override
    protected boolean verifyDrawable(Drawable who) {
        return super.verifyDrawable(who) || (who == foreground);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        foregroundBoundsChanged = true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (foreground != null) {
            final Drawable foreground = this.foreground;
            if (foregroundBoundsChanged) {
                foregroundBoundsChanged = false;
                final int w = getRight() - getLeft();
                final int h = getBottom() - getTop();
                foreground.setBounds(0, 0, w, h);
            }
            foreground.draw(canvas);
        }
    }
}
