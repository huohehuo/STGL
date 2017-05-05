package lins.com.stgl.ui.login;

import android.databinding.DataBindingUtil;
import android.view.View;

import lins.com.stgl.App;
import lins.com.stgl.R;
import lins.com.stgl.bean.BaseData;
import lins.com.stgl.databinding.ActivityFindpwdBinding;
import lins.com.stgl.rxservice.MySubscribe;
import lins.com.stgl.ui.base.BaseActivity;

public class ForgetActivity extends BaseActivity {
    ActivityFindpwdBinding binding;

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_findpwd);
        binding.toolbar.tvTopTitle.setText("忘记密码");
        binding.toolbar.tvTopRight.setText("确定");
    }

    @Override
    protected void initEvent() {
        binding.toolbar.tvTopRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.getService().getForget(
                        binding.etFindName.getText().toString(),
                        binding.etFindMishi.getText().toString(),
                        binding.etFindNewpwd.getText().toString(),
                        new MySubscribe<BaseData>() {
                            @Override
                            public void onNext(BaseData forgetData) {
                                super.onNext(forgetData);
                                showToast("修改成功");
                                finish();
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
