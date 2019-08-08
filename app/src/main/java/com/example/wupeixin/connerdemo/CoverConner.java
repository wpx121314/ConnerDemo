package com.example.wupeixin.connerdemo;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.ColorRes;
import android.util.TypedValue;


public class CoverConner {
    private float size;
    private @ColorRes
    int colorResource;
    private Context context;
    private int unit= TypedValue.COMPLEX_UNIT_DIP;
    public CoverConner(@ColorRes int colorResource, int size, Context context){
        setColorResource(colorResource);
        setSize(size);
        this.context = context;
    }
    public ColorStateList getColor() {
        return context.getResources().getColorStateList(colorResource);
    }

    public void setColorResource(int colorResource) {
        this.colorResource = colorResource;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }
}
