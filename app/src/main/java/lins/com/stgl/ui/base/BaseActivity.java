package lins.com.stgl.ui.base;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import lins.com.stgl.App;
import lins.com.stgl.R;
import lins.com.stgl.utils.DialogUtils;

/**
 * Created by LINS on 2017/4/8.
 */

public abstract class BaseActivity extends AppCompatActivity{
    private Toast toast;
    private Dialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉actionBar
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        initView();//初始化页面
        initEvent();//初始化事件
        getData();//加载数据
    }

    /**
     * 跳转至Activity
     * @param pClass
     */
    public void startActivity(Class<?> pClass){
        startActivity(new Intent(this,pClass));
    }
    /**
     * 自定义Toast消息展示
     * @param msg
     */
    public void showToast(String msg){
        if (toast == null) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        }
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setText(msg);
        toast.show();
    }
    //长时间的Toast
    public void showToast(String msg,int type){
        if (toast == null) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        }
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setText(msg);
        toast.show();
    }
    public void showToast(int resId){
        showToast(getString(resId));
    }

    public void showBottomToast(View view,String str){
        Snackbar snackbar=Snackbar.make(view,str,Snackbar.LENGTH_SHORT).setDuration(3000);
        setSnackbarMessageTextColor(snackbar,getResources().getColor(R.color.item_weibo));
        snackbar.show();
    }
    //设置Snackbar的字体颜色
    public static void setSnackbarMessageTextColor(Snackbar snackbar, int color) {
        View view = snackbar.getView();
        ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(color);
    }
    public void startActivityWith(final Class<?> pClass, View view){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(App.getContext(),pClass));
            }
        });
    }

    /**
     * 封装Activity关闭方法
     * @param view
     */
    public void closeActivity(View view){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void showDialog(String str){
        dialog = DialogUtils.createLoadingDialog(this,str);
    }
    public void closeDialog(){
        if (dialog!=null){
            DialogUtils.closeDialog(dialog);
        }
    }
    public void closeDialgWithActivity(){
        if (dialog!=null){
            DialogUtils.closeDialog(dialog);
            finish();
        }
    }









    protected abstract void initView();
    protected abstract void initEvent();
    protected abstract void getData();
}
