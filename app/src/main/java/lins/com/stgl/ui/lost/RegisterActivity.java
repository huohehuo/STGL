//package lins.com.stgl.ui;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import lins.com.stgl.R;
//import lins.com.stgl.utils.HttpUtils;
//import lins.com.stgl.utils.Config;
//
//public class RegisterActivity extends Activity {
//
//    private static final String TAG = "MainActivity";
//    //public static String url = "http://192.168.1.102:8080/STGLserver/register";
//    private EditText name,pwd,sex,age,clue;
//    private Button rgtbtn;
//    private RadioGroup get_sex;
//    private RadioButton rd_men,rd_wm;
//    String get_group_sex="男";
//
//    private Spinner mspclue,mspschool;
//    private String msclue,msschool;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.register);
//        initView();
//
//        mspclue = (Spinner) findViewById(R.id.get_clue);
//        mspschool = (Spinner)findViewById(R.id.get_school);
//
//        mspclue.setOnItemSelectedListener(getClueItem);
//        mspschool.setOnItemSelectedListener(getSchool);
//
//
//        get_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                // TODO Auto-generated method stub
//
//                if(checkedId == rd_men.getId());
//                {
//                    get_group_sex=rd_men.getText().toString();
//                }
//                if(checkedId == rd_wm.getId()){
//                    get_group_sex=rd_wm.getText().toString();
//                }
//
//
//            }
//        });
//
//    }
//    private AdapterView.OnItemSelectedListener getClueItem = new AdapterView.OnItemSelectedListener() {
//
//        @Override
//        public void onItemSelected(AdapterView<?> parent, View v, int position,
//                                   long id) {
//            // TODO Auto-generated method stub
//            msclue = parent.getSelectedItem().toString();
//        }
//
//        @Override
//        public void onNothingSelected(AdapterView<?> arg0) {
//            // TODO Auto-generated method stub
//
//        }
//    };
//    private AdapterView.OnItemSelectedListener getSchool = new AdapterView.OnItemSelectedListener() {
//
//        @Override
//        public void onItemSelected(AdapterView<?> parent, View v, int position,
//                                   long id) {
//            // TODO Auto-generated method stub
//            msschool = parent.getSelectedItem().toString();
//        }
//
//        @Override
//        public void onNothingSelected(AdapterView<?> arg0) {
//            // TODO Auto-generated method stub
//
//        }
//    };
//
//    private View.OnClickListener rgtData = new View.OnClickListener() {
//
//        @Override
//        public void onClick(View arg0) {
//            // TODO Auto-generated method stub
//            if(name.getText().length()==0 && pwd.getText().length() == 0 && sex.getText().length() == 0)
//            {
//                Toast.makeText(getApplicationContext(), "请按要求填写信息~！", Toast.LENGTH_SHORT)
//                        .show();
//            }else{
//                new Thread(){
//                    public void run(){
//                        registerJson();
//                    }
//                }.start();
//            }
//
//        }
//    };
//
//
//    private void registerJson(){
//
//        String username = name.getText().toString();
//        String password = pwd.getText().toString();
//
//
//        String rage = age.getText().toString();
//
//
//        Map<String,String> map = new HashMap<String,String>();
//        map.put("name", username);
//        map.put("pwd", password);
//        map.put("sex", get_group_sex);
//        map.put("age", rage);
//        map.put("clue", msclue);
//        map.put("school", msschool);
//
//        Message message = new Message();
//        Bundle bundle = new Bundle();
//
//        String aa = HttpUtils.submitPostData(map, "utf-8", Config.url_register);
//        if (aa.equals("error")) {
//            bundle.putString("msg", "该用户名已被注册");
//            message.setData(bundle);
//            handler.sendMessage(message);
//
//
//            // startActivity(new Intent(LoginActivity.this,MainActivity.class));
//        } else if (aa.equals("success")){
//            bundle.putString("msg", "注册成功 ");
//            message.setData(bundle);
//            handler.sendMessage(message);
//            finish();
//        }
//
//
//    }
//
//    //显示toast线程
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message message) {
//            super.handleMessage(message);
//            Bundle bundle = message.getData();
//            String msg = bundle.getString("msg");
//            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
//        }
//    };
//
//
//
//
//    private void initView() {
//        name = (EditText) findViewById(R.id.rgt_name);
//        pwd = (EditText) findViewById(R.id.rgt_pwd);
//
//        age = (EditText) findViewById(R.id.rgt_age);
//
//        get_sex = (RadioGroup) findViewById(R.id.group_sex);
//        rd_men = (RadioButton) findViewById(R.id.men);
//        rd_wm = (RadioButton) findViewById(R.id.women);
//
//
//        rgtbtn = (Button)findViewById(R.id.rgt);
//        rgtbtn.setOnClickListener(rgtData);
//
//    }
//    //返回按钮
//    public void btn_back(View v)
//    {
//        finish();
//    }
//}
//
