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
import lins.com.stgl.databinding.ActivityForvipBinding;
import lins.com.stgl.rxservice.MySubscribe;
import lins.com.stgl.ui.base.BaseActivity;

public class ForVipActivity extends BaseActivity {
    ActivityForvipBinding binding;

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forvip);
        binding.toolbar.tvTopTitle.setText("提交申请");
        binding.toolbar.tvTopRight.setText("确定");
    }

    @Override
    protected void initEvent() {
        binding.toolbar.tvTopRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.getService().getForVip(App.getUserId(), App.getName(),
                        binding.etForvipRes.getText().toString(),
                        new MySubscribe<BaseData>() {
                            @Override
                            public void onNext(BaseData baseData) {
                                super.onNext(baseData);
                                showToast("提交成功");
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
