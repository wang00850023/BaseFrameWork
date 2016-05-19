package com.baseapp.framework.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.baseapp.framework.bean.response.ResponseBean;
import com.baseapp.framework.mvp.BasePresenter;
import com.baseapp.framework.mvp.presenter.view.MainView;

import com.baseapp.framework.mvp.model.MainModel;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * MainActvity主导控制器
 * 操作model和View
 * Created by wby on 2016/5/18.
 */
public class MainPresenter extends BasePresenter<MainView> {

   private MainModel model = new MainModel();

    public MainPresenter(Context context, MainView mMvpView) {
        super(context, mMvpView);
    }


    public void getData(){
        model.getDaily().subscribeOn(Schedulers.io())
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
                        MainPresenter.this.getMvpView().loadData(contributors);
                    }
                });
    }
}
