package com.baseapp.framework.mvp.model;

import com.baseapp.framework.api.ApiManger;
import com.baseapp.framework.bean.response.ResponseBean;

import rx.Observable;

/**
 * Created by wby on 2016/5/18.
 */
public class LoginModel {
    public Observable<ResponseBean> doLogin(String user,String pwd){
        return ApiManger.getInstance().getResfApi().getDaily();
    }

}
