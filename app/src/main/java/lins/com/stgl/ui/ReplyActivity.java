package lins.com.stgl.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import lins.com.stgl.App;
import lins.com.stgl.R;
import lins.com.stgl.bean.BaseData;
import lins.com.stgl.databinding.ActivitySendToBinding;
import lins.com.stgl.rxservice.MySubscribe;
import lins.com.stgl.ui.base.BaseActivity;

import static android.os.Build.VERSION_CODES.M;

public class ReplyActivity extends BaseActivity {
    private String toFriend;
    ActivitySendToBinding binding;

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_send_to);
        binding.toolbar.tvTopRight.setText("发送");

        Intent getn=getIntent();
        toFriend=getn.getStringExtra("toFriend");

        binding.toolbar.tvTopTitle.setText("正在回复 "+toFriend);
    }

    @Override
    protected void initEvent() {
        binding.toolbar.tvTopRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.getService().reply(App.getName(), binding.etReply.getText().toString(),
                        toFriend, new MySubscribe<BaseData>() {
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
