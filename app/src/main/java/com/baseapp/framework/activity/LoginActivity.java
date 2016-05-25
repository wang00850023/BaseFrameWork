package com.baseapp.framework.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.baseapp.framework.R;
import com.baseapp.framework.base.BaseActivity;
import com.baseapp.framework.mvp.presenter.LoginPresenter;
import com.baseapp.framework.mvp.presenter.view.LoginView;

/**
 * 5.25 18.07
 * 5.25 18.02
 * 5.25 17.33
 * Created by wby on 2016/5/18
 */
public class LoginActivity extends BaseActivity implements LoginView {
    //asd
    private EditText editUser,editPwd;
    LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        loginPresenter = new LoginPresenter(this,this);
        initView();
    }

    public void initView(){
        editUser = (EditText) findViewById(R.id.editText);
        editPwd = (EditText) findViewById(R.id.editText2);
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.login();
            }
        });
    }

    @Override
    public String getUserName() {
        return editUser.getText().toString();
    }

    @Override
    public String getUserPwd() {
        return editPwd.getText().toString();
    }

    @Override
    public void setUserName(String user) {
         editUser.setText(user);
    }

    @Override
    public void setUserPwd(String pwd) {
        editPwd.setText(pwd);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void loginSucess() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
