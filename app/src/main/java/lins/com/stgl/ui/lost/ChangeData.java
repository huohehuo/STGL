//package lins.com.stgl.ui;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.content.SharedPreferences;
//import android.content.SharedPreferences.Editor;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import lins.com.stgl.R;
//import lins.com.stgl.utils.HttpUtils;
//import lins.com.stgl.utils.Config;
//
//public class ChangeData extends Activity implements Runnable {
//    private EditText t_name,t_pwd,t_sex,t_age,t_st,t_mishi;
//    private TextView change;
//    private Thread thread;
//
//    //String url="http://192.168.1.102:8080/STGLserver/changedata";
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_data);
//        initView();
//
//        SharedPreferences pf=getSharedPreferences("usertext", Activity.MODE_PRIVATE);
//        t_name.setText(pf.getString("rname",null));
//        t_pwd.setText(pf.getString("pwd",null));
//        t_sex.setText(pf.getString("sex",null));
//        t_age.setText(pf.getString("age",null));
//        t_st.setText(pf.getString("clue",null));
//        String mishis=pf.getString("mishi", null);
//        if(mishis.equals(""))
//        {
//            t_mishi.setText("");
//        }else
//        {
//            t_mishi.setText(pf.getString("mishi", null));
//        }
//
//
//
//        change.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                if(t_name.getText().length()==0 && t_pwd.getText().length() == 0 && t_age.getText().length() == 0
//                        && t_sex.getText().length() == 0 && t_st.getText().length() == 0)
//                {
//                    Toast.makeText(getApplicationContext(), "请确保修改的信息完整性", Toast.LENGTH_SHORT)
//                            .show();
//                }else{
//                    thread = new Thread(ChangeData.this);
//                    thread.start();
//                }
//            }
//        });
//    }
//
//    private void changedata() {
//        String username = t_name.getText().toString();
//        String password = t_pwd.getText().toString();
//        String sex = t_sex.getText().toString();
//        String age = t_age.getText().toString();
//        String clue = t_st.getText().toString();
//        String mishi = t_mishi.getText().toString();
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("name", username);
//        map.put("pwd", password);
//        map.put("sex", sex);
//        map.put("age", age);
//        map.put("clue", clue);
//        map.put("mishi", mishi);
//
//        Message message = new Message();
//        Bundle bundle = new Bundle();
//
//        String aa= HttpUtils.submitPostData(map, "utf-8", Config.url_changedata);
//        if(aa.equals("success"))
//        {
//            bundle.putString("msg", "信息更新成功");
//            message.setData(bundle);
//            handler.sendMessage(message);
//
//            SharedPreferences sp = getSharedPreferences("usertext",Activity.MODE_PRIVATE);
//            Editor ed = sp.edit();
//            //ed.putString("rid", username);
//            ed.putString("pwd", password);
//            ed.putString("rname", username);
//            ed.putString("sex", sex);
//            ed.putString("age", age);
//            ed.putString("clue", clue);
//            ed.putString("mishi", mishi);
//
//            ed.commit();
//            finish();
//            // startActivity(new Intent(LoginActivity.this,MainActivity.class));
//
//        }else{
//            bundle.putString("msg", "数据更新失败");
//            message.setData(bundle);
//            handler.sendMessage(message);
//        }
//
//    }
//
//    private void initView()
//    {
//        t_name = (EditText) findViewById(R.id.tlt_name);
//        t_pwd = (EditText) findViewById(R.id.tlt_pwd);
//        t_sex = (EditText) findViewById(R.id.tlt_sex);
//        t_age = (EditText) findViewById(R.id.tlt_age);
//        t_st = (EditText) findViewById(R.id.tlt_st);
//        t_mishi=(EditText) findViewById(R.id.tlt_mishi);
//        change = (TextView)findViewById(R.id.change);
//    }
//    //接收Toast消息并显示
//    @SuppressLint("HandlerLeak") private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message message) {
//            super.handleMessage(message);
//            Bundle bundle = message.getData();
//            String msg = bundle.getString("msg");
//            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
//                    .show();
//        }
//    };
//    //返回按钮
//    public void btn_back(View v)
//    {
//        finish();
//    }
//    @Override
//    public void run() {
//        // TODO Auto-generated method stub
//        changedata();
//    }
//}
//
