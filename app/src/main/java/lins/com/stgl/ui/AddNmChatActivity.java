package lins.com.stgl.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.view.View;

import lins.com.stgl.App;
import lins.com.stgl.R;
import lins.com.stgl.bean.BaseData;
import lins.com.stgl.databinding.ActivityAddNmchatBinding;
import lins.com.stgl.rxservice.MySubscribe;
import lins.com.stgl.ui.base.BaseActivity;

public class AddNmChatActivity extends BaseActivity {
    ActivityAddNmchatBinding binding;
    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_nmchat);
        binding.toolbar.tvTopTitle.setText("发表吐槽");
        binding.toolbar.tvTopRight.setText("确定");
    }

    @Override
    protected void initEvent() {
        binding.toolbar.tvTopRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.getService().addNmChat(App.getName(), App.getSex(), App.getClub(), App.getSchool(),
                        binding.etNmchatTxt.getText().toString(),
                        new MySubscribe<BaseData>() {
                            @Override
                            public void onNext(BaseData baseData) {
                                super.onNext(baseData);
                                showToast("发送成功");
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
