package lins.com.stgl.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import lins.com.stgl.App;
import lins.com.stgl.R;
import lins.com.stgl.bean.BaseData;
import lins.com.stgl.databinding.ActivityAddPlBinding;
import lins.com.stgl.rxservice.MySubscribe;
import lins.com.stgl.ui.base.BaseActivity;

public class AddPlunActivity extends BaseActivity {
    ActivityAddPlBinding binding;
    String hid,userid,username;
    SharedPreferences pf;
    @Override
    protected void initView() {
        binding= DataBindingUtil.setContentView(this, R.layout.activity_add_pl);
        binding.toolbar.tvTopTitle.setText("发表评论");
        binding.toolbar.tvTopRight.setText("发送");
        pf = getSharedPreferences("usertext", Activity.MODE_PRIVATE);
        userid = pf.getString("rid", null);
        username = pf.getString("rname", null);
        hid =pf.getString("hid", null);

    }

    @Override
    protected void initEvent() {
        binding.toolbar.tvTopRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.getService().addPlun(hid, userid, username,
                        binding.etPl.getText().toString(),
                        new MySubscribe<BaseData>() {
                            @Override
                            public void onNext(BaseData baseData) {
                                super.onNext(baseData);
                                showToast("评论成功");
                                finish();
                            }
                        });
            }
        });
    }

    @Override
    protected void getData() {

    }
}