package com.example.wupeixin.connerdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class StateAttachLinearLayout extends LinearLayout {
    public StateAttachLinearLayout(Context context) {
        super(context);
    }

    public StateAttachLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StateAttachLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public StateAttachLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private List<Drawable> drawables;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        isAttachToWindow = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isAttachToWindow = false;
    }

    private boolean isAttachToWindow;

    public boolean isAttachedToWindow() {
        return isAttachToWindow;
    }


    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (getDrawable() != null && getDrawable().size() > 0 && isAttachToWindow) {
            final int[] state = getDrawableState();
            for (Drawable drawable : drawables) {
                drawable.setState(state);
            }
        }
    }

    @Override
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (getDrawable() != null && getDrawable().size() > 0 && isAttachToWindow) {
            for (Drawable drawable : drawables) {
                drawable.jumpToCurrentState();
            }
        }
    }


    public List<Drawable> getDrawable() {
        return drawables;
    }

    public void addDrawable(Drawable drawable) {
        if (drawables == null) {
            drawables = new ArrayList<>();
        }
        drawables.add(drawable);
    }
}
