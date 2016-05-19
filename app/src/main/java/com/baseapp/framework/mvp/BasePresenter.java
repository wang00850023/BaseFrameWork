package com.baseapp.framework.mvp;

import android.content.Context;

/**
 * Created by wby on 2016/5/18.
 */
public class BasePresenter<T extends MvpView>{

    private T mMvpView;
    private Context mContext;
    public BasePresenter(Context context,T mMvpView){
        this.mContext = context;
        this.mMvpView = mMvpView;
    }
    public boolean isViewAttached() {
        return mMvpView != null;
    }


    public T getMvpView() {
        return mMvpView;
    }
    public Context getContext(){ return  mContext;}
}
