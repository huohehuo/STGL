//package lins.com.stgl.ui;
//
//import android.app.Activity;
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
//import java.util.HashMap;
//import java.util.Map;
//
//import lins.com.stgl.R;
//import lins.com.stgl.utils.HttpUtils;
//import lins.com.stgl.utils.Config;
//
//public class GgaoUpdata extends Activity {
//
//    private static final String TAG = "GgaoUpdata";
//    //String url = "http://192.168.1.102:8080/STGLserver/updata_ggao";
//    private EditText gonggao;
//    private TextView btn_gx;
//    SharedPreferences pf;
//    String clue,username,rschool;
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_updata_gonggao);
//        init();
//
//        btn_gx.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(gonggao.getText().length()==0)
//                {
//                    Toast.makeText(getApplicationContext(), "请输入公告内容", Toast.LENGTH_SHORT)
//                            .show();
//                }else
//                {
//                    new Thread() {
//                        public void run() {
//                            updata();
//                        }
//                    }.start();
//                }
//            }
//        });
//
//    }
//
//    private void updata() {
//        pf = getSharedPreferences("usertext", Activity.MODE_PRIVATE);
//
//        username = pf.getString("rname", null);
//        clue = pf.getString("clue", null);
//        rschool=pf.getString("school", null);
//
//        String ggao = gonggao.getText().toString();
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("jgname", username);
//        map.put("jgclue", clue);
//        map.put("jgonggao",ggao);
//        map.put("jschool", rschool);
//        Log.e(TAG, "-------------------------"+rschool+"-------"+clue);
//        String aa = HttpUtils.submitPostData(map, "utf-8", Config.url_ggaoupdata);
//        Message message = new Message();
//        Bundle bundle = new Bundle();
//        bundle.putString("msg", "更新公告成功");
//        message.setData(bundle);
//        handler.sendMessage(message);
//        //startActivity(new Intent(GgaoUpdata.this,MainActivity.class));
//        finish();
//
//    }
//
//    // 显示toast线程
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message message) {
//            super.handleMessage(message);
//            Bundle bundle = message.getData();
//            String msg = bundle.getString("msg");
//            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
//                    .show();
//        }
//    };
//    public void init()
//    {
//        gonggao=(EditText)findViewById(R.id.txt_gonggao);
//        btn_gx=(TextView)findViewById(R.id.gg_send);
//    }
//    //返回键
//    public void btn_back(View v) {
//
//        finish();
//    }
//
//}
//
