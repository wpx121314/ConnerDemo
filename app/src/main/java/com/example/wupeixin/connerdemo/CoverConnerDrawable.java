package com.example.wupeixin.connerdemo;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;



public class CoverConnerDrawable extends Drawable {


    private final CoverConner coverConner;
    private Rect rect = new Rect();
    private Path path = new Path();
    private Paint paint;
    private int size;

    public CoverConnerDrawable(CoverConner coverConner) {
        this.coverConner = coverConner;
        size = (int) TypedValue.applyDimension(coverConner.getUnit(), coverConner.getSize(),  Resources.getSystem().getDisplayMetrics());
        initTextPaint();
    }


    private void initTextPaint() {
        paint = new Paint();

        paint.setTextSize(size);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        rect.set(left, top, right, bottom);
        path.reset();
        //左上
        path.addArc(new RectF(0, 0, size * 2, size * 2), 180, 90);
        path.lineTo(0, 0);
        path.lineTo(0, size);
        //右上
        path.addArc(new RectF(right - 2 * size, 0, right, 2 * size), 270, 90);
        path.lineTo(right, 0);
        path.lineTo(right - size, 0);
        //右下
        path.addArc(new RectF(right - 2 * size, bottom - 2 * size, right, bottom), 0, 90);
        path.lineTo(right, bottom);
        path.lineTo(right, bottom - size);
        //左下
        path.addArc(new RectF(0, bottom - 2 * size, size * 2, bottom), 90, 90);
        path.lineTo(0, bottom);
        path.lineTo(size, bottom);
    }


    @Override
    public void draw(Canvas canvas) {
        paint.setColor(coverConner.getColor().getColorForState(getState(), coverConner.getColor().getDefaultColor()));
        canvas.drawPath(path, paint);
    }


    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }


    @Override
    public void setColorFilter(ColorFilter cf) {
        paint.setColorFilter(cf);
    }

    @Override
    public void clearColorFilter() {
        paint.setColorFilter(null);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }

    @Override
    public boolean isStateful() {
        return coverConner.getColor().isStateful();
    }

    @Override
    protected boolean onStateChange(int[] state) {
        invalidateSelf();
        return true;
    }

}
