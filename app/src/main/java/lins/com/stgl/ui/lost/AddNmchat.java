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
//import java.util.HashMap;
//import java.util.Map;
//
//import lins.com.stgl.R;
//import lins.com.stgl.ui.NmChatActivity;
//import lins.com.stgl.utils.HttpUtils;
//import lins.com.stgl.utils.Config;
//
//public class AddNmchat extends Activity {
//    Config urldata;
//    EditText saytxt;
//    TextView send;
//    SharedPreferences pf;
//    String username,sex,clue,school;
//    //String url="http://192.168.1.102:8080/STGLserver/wr_nmchat";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        // TODO Auto-generated method stub
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_nmchat);
//        init();
//        send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(saytxt.getText().length()==0)
//                {
//                    Toast.makeText(getApplicationContext(), "发布失败，请确认发布信息的完整性。", Toast.LENGTH_SHORT)
//                            .show();
//                }else
//                {
//                    new Thread() {
//                        public void run() {
//                            addnm();
//                        }
//                    }.start();
//                }
//            }
//        });
//    }
//
//
//    private void addnm() {
//        pf = getSharedPreferences("usertext", Activity.MODE_PRIVATE);
//        username = pf.getString("rname", null);
//        sex=pf.getString("sex", null);
//        clue=pf.getString("clue", null);
//        school=pf.getString("school", null);
//
//        String say = saytxt.getText().toString();
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("jname",username);
//        map.put("jsex", sex);
//        map.put("jclue", clue);
//        map.put("jschool", school);
//        map.put("jsaytext", say);
//
//        String aa = HttpUtils.submitPostData(map, "utf-8", Config.url_add_nmchat);
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
//
//
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
//        saytxt=(EditText) findViewById(R.id.txt_nmsay);
//        send=(TextView) findViewById(R.id.nm_send);
//    }
//    //返回键
//    public void btn_back(View v) {
//        finish();
//    }
//
//}
//
