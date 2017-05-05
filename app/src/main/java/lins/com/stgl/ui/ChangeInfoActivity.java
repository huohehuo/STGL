package lins.com.stgl.ui;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lins.com.stgl.App;
import lins.com.stgl.R;
import lins.com.stgl.bean.BaseData;
import lins.com.stgl.bean.PickerViewBean;
import lins.com.stgl.bean.UserData;
import lins.com.stgl.databinding.ActivityDataBinding;
import lins.com.stgl.rxservice.MySubscribe;
import lins.com.stgl.ui.base.BaseActivity;

public class ChangeInfoActivity extends BaseActivity {
    ActivityDataBinding binding;
    @Override
    protected void initView() {
        binding= DataBindingUtil.setContentView(this, R.layout.activity_data);
        binding.toolbar.tvTopTitle.setText("修改信息");
        binding.toolbar.tvTopRight.setText("完成");
        binding.toolbar.ivTopArrow.setImageResource(R.drawable.btn_return_selector);


    }

    @Override
    protected void initEvent() {

        binding.toolbar.tvTopRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog("正在处理...");
                App.getService().getUpdataInfo(
                        binding.etChangeName.getText().toString(),
                        binding.etChangePwd.getText().toString(),
                        binding.etChangeSex.getText().toString(),
                        binding.etChangeAge.getText().toString(),
                        binding.etChangeClub.getText().toString(),
                        binding.etChangeMishi.getText().toString(),
                        new MySubscribe<BaseData>() {
                            @Override
                            public void onNext(BaseData baseData) {
                                super.onNext(baseData);
                                closeDialog();
                                showToast("修改成功");
                                finish();
                            }

                            @Override
                            public void onError(Throwable e) {
                                closeDialog();
                                showToast("Somethis Error...");
                            }
                        }
                );
            }
        });
    }

    @Override
    protected void getData() {
        showDialog("正在获取信息");
        App.getService().login(App.getName(), App.getPwd(),
                new MySubscribe<UserData>() {
                    @Override
                    public void onNext(UserData userData) {
                        super.onNext(userData);
                        closeDialog();
                        //填充布局数据
                        binding.etChangeName.setText(userData.getData().getName());
                        binding.etChangePwd.setText(userData.getData().getPwd());
                        binding.etChangeAge.setText(userData.getData().getAge()+"");
                        binding.etChangeSex.setText(userData.getData().getSex());
                        binding.etChangeClub.setText(userData.getData().getClue());
                        binding.etChangeMishi.setText(userData.getData().getMishi());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        closeDialog();
                    }
                });
    }
}
