//package lins.com.stgl.ui;
//
//import android.app.Activity;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
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
//public class Forvip extends Activity {
//
//    EditText txt_forvip;
//    TextView btn_send;
//    SharedPreferences pf;
//    String userid,username;
//    //String url = "http://192.168.1.102:8080/STGLserver/forvip";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        // TODO Auto-generated method stub
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_forvip);
//        init();
//        btn_send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(txt_forvip.getText().length()==0)
//                {
//                    Toast.makeText(getApplicationContext(), "请输入申请理由", Toast.LENGTH_SHORT)
//                            .show();
//                }else
//                {
//                    new Thread() {
//                        public void run() {
//                            forvip();
//                        }
//                    }.start();
//                }
//            }
//        });
//    }
//    private void forvip() {
//        pf = getSharedPreferences("usertext", Activity.MODE_PRIVATE);
//        userid = pf.getString("rid", null);
//        username = pf.getString("rname", null);
//
//
//        String forvip = txt_forvip.getText().toString();
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("juserid", userid);
//        map.put("jusername", username);
//        map.put("jforvip", forvip);
//
//        Message message = new Message();
//        Bundle bundle = new Bundle();
//
//        String aa = HttpUtils.submitPostData(map, "utf-8", Config.url_forvip);
//        if (aa.equals("error")) {
//            bundle.putString("msg", "您已提交过申请，请耐心等待回复");
//            message.setData(bundle);
//            handler.sendMessage(message);
//
//
//            // startActivity(new Intent(LoginActivity.this,MainActivity.class));
//        } else if (aa.equals("success")){
//            bundle.putString("msg", "提交申请成功,请耐心等候回复 ");
//            message.setData(bundle);
//            handler.sendMessage(message);
//            finish();
//        }
//
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
//
//    public void init()
//    {
//        txt_forvip=(EditText) findViewById(R.id.txt_forvip);
//        btn_send=(TextView) findViewById(R.id.forvip_send);
//    }
//    //返回键
//    public void btn_back(View v) {
//        finish();
//    }
//}
//
