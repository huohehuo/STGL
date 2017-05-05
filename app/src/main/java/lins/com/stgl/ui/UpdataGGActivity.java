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
import lins.com.stgl.databinding.ActivityUpdataGonggaoBinding;
import lins.com.stgl.rxservice.MySubscribe;
import lins.com.stgl.ui.base.BaseActivity;

public class UpdataGGActivity extends BaseActivity {
    ActivityUpdataGonggaoBinding binding;

    @Override
    protected void initView() {
        binding= DataBindingUtil.setContentView(this, R.layout.activity_updata_gonggao);
        binding.toolbar.tvTopTitle.setText("更新公告");
        binding.toolbar.tvTopRight.setText("确定");

    }

    @Override
    protected void initEvent() {
        binding.toolbar.tvTopRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog("处理中...");
                App.getService().getUpdataGG(
                        App.getName(), App.getClub(),
                        binding.etUpdataGg.getText().toString(),
                        App.getSchool(), new MySubscribe<BaseData>() {
                            @Override
                            public void onNext(BaseData baseData) {
                                super.onNext(baseData);
                                closeDialog();
                                showToast("更新成功");
                                finish();
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                closeDialog();
                            }
                        }
                );
            }
        });
    }

    @Override
    protected void getData() {

    }
}
