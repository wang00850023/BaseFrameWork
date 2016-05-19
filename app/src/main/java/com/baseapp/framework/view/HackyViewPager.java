package com.baseapp.framework.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class HackyViewPager extends ViewPager
{
	  public HackyViewPager(Context paramContext)
	  {
	    super(paramContext);
	  }

	  public HackyViewPager(Context paramContext, AttributeSet paramAttributeSet)
	  {
	    super(paramContext, paramAttributeSet);
	  }

	  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
	  {
	    try
	    {
	      boolean bool = super.onInterceptTouchEvent(paramMotionEvent);
	      return bool;
	    }
	    catch (IllegalArgumentException localIllegalArgumentException)
	    {
	      localIllegalArgumentException.printStackTrace();
	    }
	    return false;
	  }
	}