//package lins.com.stgl.ui;
//
//import java.util.HashMap;
//import java.util.Map;
//import android.app.Activity;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import lins.com.stgl.R;
//import lins.com.stgl.utils.HttpUtils;
//import lins.com.stgl.utils.Config;
//
//public class Reply extends Activity {
//
//    EditText txt_resend;
//    TextView btn_resend,txt_head;
//    private static final String TAG = "Reply";
//    SharedPreferences pf;
//    //String url = "http://192.168.1.102:8080/STGLserver/reply";
//    String isname,toname;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        // TODO Auto-generated method stub
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_send_to);
//        init();
//        Intent getn=getIntent();
//        toname=getn.getStringExtra("fromname");
//
//        txt_head.setText("正在回复 "+toname);
//        Log.e(TAG, "--------------获取intent-----"+toname);
//
//        btn_resend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(txt_resend.getText().length()==0)
//                {
//                    Toast.makeText(getApplicationContext(), "请输入回复内容", Toast.LENGTH_SHORT)
//                            .show();
//                }else
//                {
//                    new Thread() {
//                        public void run() {
//                            sendto();
//                        }
//                    }.start();
//                }
//            }
//        });
//
//
//    }
//
//
//    public void sendto()
//    {
//        pf = getSharedPreferences("usertext", Activity.MODE_PRIVATE);
//        isname = pf.getString("rname", null);
//
//        String sendtxt=txt_resend.getText().toString();
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("jisname", isname);
//        map.put("jistext", sendtxt);
//        map.put("jtoname", toname);
//
//        String aa = HttpUtils.submitPostData(map, "utf-8", Config.url_reply);
//        Message message = new Message();
//        Bundle bundle = new Bundle();
//        bundle.putString("msg", "回复成功");
//        message.setData(bundle);
//        handler.sendMessage(message);
//        finish();
//    }
//    // 显示toast线程
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
//    public void init()
//    {
//        txt_resend=(EditText)findViewById(R.id.txt_resend);
//        btn_resend=(TextView)findViewById(R.id.send_to);
//        txt_head=(TextView)findViewById(R.id.head_t);
//    }
//    //返回键
//    public void btn_back(View v) {
//        finish();
//    }
//
//}
