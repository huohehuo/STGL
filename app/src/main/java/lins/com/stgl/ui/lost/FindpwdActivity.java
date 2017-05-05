//package lins.com.stgl.ui;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
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
//public class FindpwdActivity extends Activity {
//    private static final String TAG = "FindpwdActivity";
//    private EditText find_name,find_mishi,find_changepwd;
//    private TextView txt_btn_change;
//    String name,mishi,changepwd;
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_findpwd);
//        init();
//
//        txt_btn_change.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                name=find_name.getText().toString();
//                mishi=find_mishi.getText().toString();
//                changepwd=find_changepwd.getText().toString();
//
//                Thread chack=new Thread(new Runnable(){
//
//                    @Override
//                    public void run() {
//                        // TODO Auto-generated method stub
//
//                        Map<String, String> map = new HashMap<String, String>();
//                        map.put("jname", name);
//                        map.put("jmishi", mishi);
//                        map.put("jpwd", changepwd);
//
//                        Message message = new Message();
//                        Bundle bundle = new Bundle();
//                        String aa= HttpUtils.submitPostData(map, "utf-8", Config.url_findpwd);
//                        Log.e(TAG, "-------------返回--------"+aa);
//                        if (aa.equals("sorry")) {
//                            bundle.putString("msg", "录入信息有误，请重新确认");
//                            message.setData(bundle);
//                            handler.sendMessage(message);
//                            Log.e(TAG, "-----------sorry---------"+aa);
//
//                        } else {
//                            bundle.putString("msg", "改密成功，请登录");
//                            message.setData(bundle);
//                            handler.sendMessage(message);
//                            Log.e(TAG, "-----------yes---------"+aa);
//                            finish();
//                        }
//                    }
//
//                });
//                chack.start();
//
//            }
//        });
//
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
//    void init()
//    {
//        find_name=(EditText) findViewById(R.id.find_name);
//        find_mishi=(EditText) findViewById(R.id.find_mishi);
//        find_changepwd=(EditText) findViewById(R.id.change_pwd);
//
//        txt_btn_change=(TextView) findViewById(R.id.btn_change);
//    }
//    //返回按钮
//    public void btn_back(View v)
//    {
//        finish();
//    }
//}
//
