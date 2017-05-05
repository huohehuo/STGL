//package lins.com.stgl.ui;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpPost;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import lins.com.stgl.R;
//import lins.com.stgl.ui.HdShowActivity;
//import lins.com.stgl.utils.HttpUtils;
//import lins.com.stgl.utils.Config;
//
//public class AddPlun extends Activity {
//    EditText tname, tplun;
//    TextView btnInSaveButton;
//    private static final String TAG = "MainActivity";
//    SharedPreferences pf;
//    //String url = "http://192.168.1.102:8080/STGLserver/wr_pl";
//    private HttpPost post;
//    private HttpResponse httpResponse;
//    String hid,userid,username;
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_pl);
//        initView();
//
//
//        btnInSaveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(tplun.getText().length()==0)
//                {
//                    Toast.makeText(getApplicationContext(), "请输入评论内容", Toast.LENGTH_SHORT)
//                            .show();
//                }else{
//                    new Thread() {
//                        public void run() {
//                            addpl();
//                        }
//                    }.start();
//                }
//            }
//        });
//
//    }
//
//    private void addpl() {
//        pf = getSharedPreferences("usertext", Activity.MODE_PRIVATE);
//        userid = pf.getString("rid", null);
//        username = pf.getString("rname", null);
//        hid =pf.getString("hid", null);
//
//        String plun = tplun.getText().toString();
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("jhid", hid);
//        map.put("juserid", userid);
//        map.put("jusername", username);
//        map.put("jpword", plun);
//
//        String aa = HttpUtils.submitPostData(map, "utf-8", Config.url_add_plun);
//        Message message = new Message();
//        Bundle bundle = new Bundle();
//        bundle.putString("msg", "发布成功");
//        message.setData(bundle);
//        handler.sendMessage(message);
//        finish();
//
//
//
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
//    public void initView()
//    {
//
//        tplun = (EditText) findViewById(R.id.txt_pl);
//        btnInSaveButton = (TextView) findViewById(R.id.send);
//
//    }
//    //返回键
//    public void btn_back(View v) {
//        finish();
//    }
//
//}
//
