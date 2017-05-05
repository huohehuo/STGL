package lins.com.stgl.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;

import java.net.URI;

import lins.com.stgl.App;
import lins.com.stgl.HomeActivity;
import lins.com.stgl.R;
import lins.com.stgl.bean.UserData;
import lins.com.stgl.databinding.LoginBinding;
import lins.com.stgl.rxservice.MySubscribe;
import lins.com.stgl.ui.base.BaseActivity;
import lins.com.stgl.utils.Config;
import lins.com.stgl.utils.KeyValueStorage;

import static android.R.attr.name;
import static android.provider.ContactsContract.CommonDataKinds.Website.URL;
import static lins.com.stgl.utils.Config.A;

public class LoginActivity extends BaseActivity {
    LoginBinding binding;
    private static final String TAG = "LoginActivity";
    private Boolean isRemember = false;
    private KeyValueStorage mKeyValueStorage;
    public static final int AUTO_INPUT=0;
    private String mName;
    private String mPwd;
    private String isLogin;
    private Intent mIntent;
    @Override
    protected void initView() {
        binding= DataBindingUtil.setContentView(this, R.layout.login);
        mKeyValueStorage=new KeyValueStorage(LoginActivity.this);
        mIntent=getIntent();
        mName=mIntent.getStringExtra("name");
        mPwd=mIntent.getStringExtra("pwd");
        isLogin = mIntent.getStringExtra("autologin");
        binding.loginName.setText(mName);
        binding.loginPwd.setText(mPwd);

    }

    @Override
    protected void initEvent() {
        startActivityWith(ForgetActivity.class,binding.lastBtn);
        startActivityWith(RegisterActivity.class,binding.registerBtn);

        binding.loginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog("正在登录...");
                App.getService().login(binding.loginName.getText().toString(),
                        binding.loginPwd.getText().toString(),
                        new MySubscribe<UserData>() {
                            @Override
                            public void onNext(UserData userData) {
                                super.onNext(userData);
                                mKeyValueStorage.putString(Config.USER_ID,userData.getData().getRid()+"");
                                mKeyValueStorage.putString(Config.USER_NAME,userData.getData().getName());
                                mKeyValueStorage.putString(Config.USER_PWD,userData.getData().getPwd());
                                mKeyValueStorage.putString(Config.USER_SEX,userData.getData().getSex());
                                mKeyValueStorage.putString(Config.USER_CLUB,userData.getData().getClue());
                                mKeyValueStorage.putString(Config.USER_SCHOOL,userData.getData().getSchool());
                                mKeyValueStorage.putString(Config.IS_VIP,userData.getData().getVip());
                                mKeyValueStorage.putString(Config.IS_AUTO_LOGIN,"0");
                                startActivity(HomeActivity.class);
                                showToast("登录成功");
                                finish();
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                closeDialog();
                                showToast("登录失败");
                            }
                        });
            }
        });

        if("1".equals(isLogin)||TextUtils.isEmpty(isLogin)){
            return;
        }else if(!TextUtils.isEmpty(mName)&&!TextUtils.isEmpty(mPwd)){
            showDialog("正在登录...");
            App.getService().login(mName, mPwd,
                    new MySubscribe<UserData>() {
                        @Override
                        public void onNext(UserData userData) {
                            super.onNext(userData);
                            mKeyValueStorage.putString(Config.USER_ID,userData.getData().getRid()+"");
                            mKeyValueStorage.putString(Config.USER_NAME,userData.getData().getName());
                            mKeyValueStorage.putString(Config.USER_PWD,userData.getData().getPwd());
                            mKeyValueStorage.putString(Config.USER_SEX,userData.getData().getSex());
                            mKeyValueStorage.putString(Config.USER_CLUB,userData.getData().getClue());
                            mKeyValueStorage.putString(Config.USER_SCHOOL,userData.getData().getSchool());
                            mKeyValueStorage.putString(Config.IS_VIP,userData.getData().getVip());
                            mKeyValueStorage.putString(Config.IS_AUTO_LOGIN,"0");
                            mKeyValueStorage.putString(userData.getData().getName(),"0");//初始化留言板数量
                            startActivity(HomeActivity.class);
                            showToast("登录成功");
                            finish();
                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                            closeDialog();
                            showToast("登录失败");
                        }
                    });
        }


    }

    @Override
    protected void getData() {

    }

    /**
     * @param context
     * @param name
     * @param pwd
     * @param autologin   是否自动登录    0：自动登录   1：不登录
     */
    public static void start(Context context,String name,String pwd,String autologin) {
        Intent starter = new Intent(context, LoginActivity.class);
        starter.putExtra("name",name);
        starter.putExtra("pwd",pwd);
        starter.putExtra("autologin",autologin);
        context.startActivity(starter);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==RESULT_OK&&resultCode==0){
        if (data!=null){
            switch (requestCode){
                case AUTO_INPUT:
                    binding.loginName.setText(data.getStringExtra("name"));
                    binding.loginPwd.setText(data.getStringExtra("pwd"));
                    break;
            }
        }
    }
}
