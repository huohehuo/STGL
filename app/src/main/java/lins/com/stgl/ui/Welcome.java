package lins.com.stgl.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import lins.com.stgl.R;
import lins.com.stgl.databinding.WelcomeBinding;
import lins.com.stgl.ui.base.BaseActivity;
import lins.com.stgl.ui.login.LoginActivity;
import lins.com.stgl.utils.Config;
import lins.com.stgl.utils.KeyValueStorage;


public class Welcome extends BaseActivity {
    private final int SPLASH_DISPLAY_LENGHT =1000;

    WelcomeBinding binding;

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this,R.layout.welcome);
    }

    @Override
    protected void initEvent() {
        KeyValueStorage mKeyValueStorage=new KeyValueStorage(this);
        final String isLogin=mKeyValueStorage.getString(Config.IS_AUTO_LOGIN,"0");
        final String name=mKeyValueStorage.getString(Config.USER_NAME,"");
        final String pwd=mKeyValueStorage.getString(Config.USER_PWD,"");

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (isLogin.equals("0")&&!TextUtils.isEmpty(isLogin)){
                    if((!TextUtils.isEmpty(name))&&(!"".equals(name))&&(!TextUtils.isEmpty(pwd))&&(!"".equals(pwd))){
                        LoginActivity.start(Welcome.this,name,pwd,isLogin);
                        finish();
                    }else {
                        startActivity(LoginActivity.class);
                        finish();
                    }
                }else{
                    startActivity(LoginActivity.class);
                    finish();

                }

            }

        }, SPLASH_DISPLAY_LENGHT);

    }

    @Override
    protected void getData() {

    }

}

