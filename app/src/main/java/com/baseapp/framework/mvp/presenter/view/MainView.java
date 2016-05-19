package com.baseapp.framework.mvp.presenter.view;

import com.baseapp.framework.bean.response.ResponseBean;
import com.baseapp.framework.mvp.MvpView;

/**
 * Created by wby on 2016/5/18.
 */
public  interface MainView extends MvpView {

    public void loadData(ResponseBean data);

}
