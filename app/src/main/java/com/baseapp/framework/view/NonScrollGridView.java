package com.baseapp.framework.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class NonScrollGridView extends GridView
{

    public NonScrollGridView(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public NonScrollGridView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }
}