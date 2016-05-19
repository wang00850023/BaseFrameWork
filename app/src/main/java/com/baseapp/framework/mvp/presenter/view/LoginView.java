package com.baseapp.framework.mvp.presenter.view;

import com.baseapp.framework.mvp.MvpView;

/**
 * Created by wby on 2016/5/18.
 */
public interface LoginView extends MvpView {
    public String getUserName();
    public String getUserPwd();

    public void setUserName(String user);
    public void setUserPwd(String pwd);

    public void showLoading();
    public void hideLoading();

    public void loginSucess();
}
