package com.baseapp.framework.mvp;

/**
 * Created by wby on 2016/5/18.
 */
public interface MvpView {
    /**
     * 发生错误
     *
     * @param e e
     */
    void onFailure(Throwable e);
}
