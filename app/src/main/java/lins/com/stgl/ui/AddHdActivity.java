package lins.com.stgl.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.bigkoo.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lins.com.stgl.App;
import lins.com.stgl.R;
import lins.com.stgl.bean.BaseData;
import lins.com.stgl.databinding.ActivityAddHdBinding;
import lins.com.stgl.rxservice.MySubscribe;
import lins.com.stgl.ui.base.BaseActivity;

public class AddHdActivity extends BaseActivity {
    ActivityAddHdBinding binding;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private String gettime;
    private DatePicker datepick;
    private TimePicker timepick;
    SimpleDateFormat formatter;
    Date date;
    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_hd);
        binding.toolbar.tvTopTitle.setText("发布活动");
        binding.toolbar.tvTopRight.setText("添加");

        formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");

    }

    @Override
    protected void initEvent() {
        binding.tvAddHdTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //时间选择器
                TimePickerView pvTime = new TimePickerView.Builder(AddHdActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        binding.tvAddHdTime.setText(formatter.format(date));
                    }
                }).build();
                pvTime.show();
            }
        });


        binding.toolbar.tvTopRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date = new Date(System.currentTimeMillis());
                App.getService().addHd(App.getUserId(), App.getName(),
                        binding.etHdTitle.getText().toString(),
                        formatter.format(date),
                        binding.tvAddHdTime.getText().toString(),
                        binding.etHdWord.getText().toString(),
                        App.getSchool(), App.getClub(), new MySubscribe<BaseData>() {
                            @Override
                            public void onNext(BaseData baseData) {
                                super.onNext(baseData);
                                showToast("创建成功");
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
