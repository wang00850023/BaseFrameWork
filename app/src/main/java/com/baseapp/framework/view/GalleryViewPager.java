package com.baseapp.framework.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
/**
 * 
 * @ClassName GalleryViewPager 
 * @Description TODO(这里用一句话描述这个类的作用) 
 * @author 王博扬
 * @date 2015-6-29 上午10:08:57 
 * @history
 * 1.YYYY-MM-DD
 *    author:
 *    description:
 */
public class GalleryViewPager extends ViewPager {  
  
    private DisplayMetrics displayMetrics;  
  
    public GalleryViewPager(Context context) {  
        super(context);  
        init();  
    }  
  
    public GalleryViewPager(Context context, AttributeSet attrs) {  
        super(context, attrs);  
        init();  
    }  
  
    private void init() {  
        displayMetrics = getContext().getResources().getDisplayMetrics();  
    }  
  
    @Override  
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(displayMetrics.widthPixels - getPageMargin() * 4, MeasureSpec.AT_MOST);  
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);  
    }  

}
