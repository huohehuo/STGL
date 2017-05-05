//package lins.com.stgl.ui;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.SharedPreferences.Editor;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import lins.com.stgl.App;
//import lins.com.stgl.HomeActivity;
//import lins.com.stgl.MainActivity;
//import lins.com.stgl.R;
//import lins.com.stgl.bean.User;
//import lins.com.stgl.utils.HttpUtils;
//import lins.com.stgl.json.UserUtil;
//
//import static lins.com.stgl.utils.Config.url_login;
//
//public class LoginActivity extends Activity implements Runnable {
//
//    private static final String TAG = "LoginActivity";
//    private EditText editname;
//    private EditText editpwd;
//    private EditText et_ip;
//    private TextView txt_last;
//    private Button mLoginButton;
//    private Button mRegisterButton;
//    private TextView result;
//    String rname,rpwd,rsex,rclue,rvip,rmishi,rschool;
//    int rid,rage;
//    CheckBox rmb;
//    //String url="http://192.168.1.102:8080/STGLserver/login";
//    private Thread thread;
//    private Boolean isRemember = false;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.login);
//        initView();
//
//        findViewById(R.id.btn_ip).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                App.setIp(Integer.valueOf(et_ip.getText().toString()));
//            }
//        });
//
//        rmb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                // TODO Auto-generated method stub
//                isRemember = isChecked;
//            }
//        });
//
//        SharedPreferences pf=getSharedPreferences("usertext", Activity.MODE_PRIVATE);
//        editname.setText(pf.getString("rname",null));
//        rmb.setChecked(pf.getBoolean("rmb", true));
//        if(rmb.isChecked())
//        {
//            editpwd.setText(pf.getString("pwd", null));
//        }else{
//            editpwd.setText(null);
//        }
//
//        mLoginButton.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                Toast.makeText(LoginActivity.this, "ip:"+App.ip, Toast.LENGTH_LONG).show();
//                thread = new Thread(LoginActivity.this);
//                thread.start();
//
//            }
//        });
//
//        txt_last.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                startActivity(new Intent(LoginActivity.this,FindpwdActivity.class));
//                LoginActivity.this.overridePendingTransition(R.anim.push_up_in,
//                        R.anim.push_up_out);
//            }
//        });
//        // 注册按钮
//        mRegisterButton.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                Intent rgt = new Intent(LoginActivity.this,
//                        RegisterActivity.class);
//                startActivity(rgt);
//                LoginActivity.this.overridePendingTransition(
//                        R.anim.in_from_right, R.anim.out_to_left);
//
//            }
//        });
//
//    }
//
//    private void login() {
//        String username = editname.getText().toString();
//        String password = editpwd.getText().toString();
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("name", username);
//        map.put("pwd", password);
//
//        Message message = new Message();
//        Bundle bundle = new Bundle();
//
//        String aa = HttpUtils.submitPostData(map, "utf-8", url_login);
//        if (aa.equals("sorry")||aa.equals("")) {
//            bundle.putString("msg", "登录失败，请重新输入");
//            message.setData(bundle);
//            handler.sendMessage(message);
//        } else {
//            //if (aa.length()<12) {
//            List<User> us = UserUtil
//                    .user(aa);
//            // android多线程规定：子线程中不可以更改UI
//            // 从系统的消息池中拿消息，节省资源
//            Iterator<User> it = us.iterator();
//            while(it.hasNext())
//            {
//                User user = (User) it.next();
//                rid = user.getRid();
//                rname= user.getName();
//                rpwd =user.getPassword();
//                rsex = user.getSex();
//                rage = user.getAge();
//                rclue = user.getClue();
//                rvip = user.getVip();
//                rmishi=user.getMishi();
//                rschool=user.getSchool();
//
//
//            }
//            String srid=Integer.toString(rid);
//            String sage=Integer.toString(rage);
//            Log.e(TAG, "-------------------------"+rid+rname);
//            SharedPreferences sp = getSharedPreferences("usertext",Activity.MODE_PRIVATE);
//            Editor ed = sp.edit();
//            ed.putString("rid", srid);
//            ed.putString("pwd", password);
//            ed.putString("rname", username);
//            ed.putString("sex", rsex);
//            ed.putString("age", sage);
//            ed.putString("clue", rclue);
//            ed.putString("vip", rvip);
//            ed.putString("mishi", rmishi);
//            ed.putString("school", rschool);
//            ed.putBoolean("rmb", isRemember);
//
//            ed.commit();
//
//            Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
//
//            startActivity(intent);
//            LoginActivity.this.overridePendingTransition(
//                    R.anim.in_from_right, R.anim.out_to_left);
//            bundle.putString("msg", "登录成功");
//            message.setData(bundle);
//            handler.sendMessage(message);
//            finish();
//            // startActivity(new Intent(LoginActivity.this,MainActivity.class));
//
//        }
//
//    }
//
//    private void initView() {
//        editname = (EditText) findViewById(R.id.login_name);
//        editpwd = (EditText) findViewById(R.id.login_pwd);
//        et_ip = (EditText) findViewById(R.id.et_ip);
//        txt_last = (TextView)findViewById(R.id.last_btn);
//
//        rmb=(CheckBox) findViewById(R.id.ischecked);
//        mLoginButton = (Button) findViewById(R.id.login_btnLogin);
//        mRegisterButton = (Button) findViewById(R.id.register_btn);
//        //result = (TextView) findViewById(R.id.login_result);
//        // mLoginButton.setOnClickListener(login);
//    }
//    @Override
//    public void run() {
//        // TODO Auto-generated method stub
//        login();
//
//    }
//
//    //接收Toast消息并显示
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message message) {
//            super.handleMessage(message);
//            Bundle bundle = message.getData();
//            String msg = bundle.getString("msg");
//            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT)
//                    .show();
//        }
//    };
//
//}
//
