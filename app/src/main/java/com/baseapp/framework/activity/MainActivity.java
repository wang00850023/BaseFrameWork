package com.baseapp.framework.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.baseapp.framework.R;
import com.baseapp.framework.base.BaseActivity;
import com.baseapp.framework.bean.response.ResponseBean;
import com.baseapp.framework.mvp.presenter.MainPresenter;
import com.baseapp.framework.mvp.presenter.view.MainView;

/**
 * Created by wby on 2016/4/12.
 * 注释添加
 */
public class MainActivity extends BaseActivity implements MainView{
    MainPresenter mainPresenter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mian);

        Button btn = (Button) findViewById(R.id.button);

       mainPresenter = new MainPresenter(this,this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainPresenter.getData();

            }
        });
    }


    @Override
    public void loadData(ResponseBean data) {
        Log.i("HTML","mvp运行后的结果:"+data.getStatus());
    }

    @Override
    public void onFailure(Throwable e) {

    }
}