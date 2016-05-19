package com.baseapp.framework.mvp.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.baseapp.framework.bean.response.ResponseBean;
import com.baseapp.framework.mvp.BasePresenter;
import com.baseapp.framework.mvp.model.LoginModel;
import com.baseapp.framework.mvp.presenter.view.LoginView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wby on 2016/5/18.
 */
public class LoginPresenter extends BasePresenter<LoginView> {

    LoginModel loginModel =new LoginModel();


    public LoginPresenter(Context context,LoginView mMvpView) {
        super(context,mMvpView);

    }

    public void sava(String user,String pwd){

    }

    public void login(){

        if(getMvpView().getUserName().isEmpty() || getMvpView().getUserPwd().isEmpty()){
            Toast.makeText(getContext(),"账号密码不能为空",Toast.LENGTH_SHORT).show();
            return ;
        }
        loginModel.doLogin(getMvpView().getUserName(), getMvpView().getUserPwd()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBean>() {
                    @Override
                    public void onCompleted() {
                        Log.i("TAG","onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseBean contributors) {
                        Log.i("TAG","RxJava--> ResponseBean: "+ contributors.getStatus());
                        getMvpView().loginSucess();

                    }
                });

    }
}
