package com.baseapp.framework.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;

public class BackView extends ImageButton
{

    public BackView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BackView(Context context) {
        super(context);
        init(context);
    }

    public void init(final Context context) {
        setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                ((Activity) context).finish();
            }
        });

    }
}
