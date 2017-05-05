package lins.com.stgl.ui.login;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import lins.com.stgl.App;
import lins.com.stgl.R;
import lins.com.stgl.bean.BaseData;
import lins.com.stgl.databinding.RegisterBinding;
import lins.com.stgl.rxservice.MySubscribe;
import lins.com.stgl.ui.base.BaseActivity;

public class RegisterActivity extends BaseActivity {
    RegisterBinding binding;
    private static final String TAG = "MainActivity";
    //public static String url = "http://192.168.1.102:8080/STGLserver/register";
    private EditText name,pwd,sex,age,clue;
    private Button rgtbtn;
    private RadioGroup get_sex;
    private RadioButton rd_men,rd_wm;
    String get_group_sex="男";

    private Spinner mspclue,mspschool;
    private String msclue,msschool;

    @Override
    protected void initView() {
        binding= DataBindingUtil.setContentView(this, R.layout.register);
        binding.toolbar.tvTopTitle.setText("注册");
        binding.spClub.setOnItemSelectedListener(getClueItem);
        binding.spSchool.setOnItemSelectedListener(getSchool);
    }

    @Override
    protected void initEvent() {
        binding.groupSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId == binding.rbMen.getId());
                {
                    get_group_sex=binding.rbMen.getText().toString();
                }
                if(checkedId == binding.rbWomen.getId()){
                    get_group_sex=binding.rbWomen.getText().toString();
                }
            }
        });

        binding.btnRgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.getService().getRegister(
                        binding.etRgtName.getText().toString(),
                        binding.etRgtPwd.getText().toString(),
                        get_group_sex,
                        binding.etRgtAge.getText().toString(),
                        msclue, msschool, new MySubscribe<BaseData>() {
                            @Override
                            public void onNext(BaseData baseData) {
                                super.onNext(baseData);
                                showToast("注册成功");
                                finish();
                            }
                        }

                );
            }
        });

    }
    private AdapterView.OnItemSelectedListener getClueItem = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View v, int position,
                                   long id) {
            // TODO Auto-generated method stub
            msclue = parent.getSelectedItem().toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }
    };
    private AdapterView.OnItemSelectedListener getSchool = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View v, int position,
                                   long id) {
            // TODO Auto-generated method stub
            msschool = parent.getSelectedItem().toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }
    };
    @Override
    protected void getData() {

    }
}
