//package lins.com.stgl.ui;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import lins.com.stgl.R;
//import lins.com.stgl.adapter.MyfriendAdapter;
//import lins.com.stgl.bean.FriendInfo;
//import lins.com.stgl.utils.HttpUtils;
//import lins.com.stgl.json.MyfriendUtil;
//import lins.com.stgl.utils.Config;
//
//public class MyfriendActivity extends Activity implements Runnable {
//
//    private static final String TAG = "MyfriendActivity";
//    LinearLayout images_layout;
//    //String url = "http://192.168.1.102:8080/STGLserver/show_friend";
//    View view, view2, view3;
//
//    private Thread thread;
//
//    private ListView list_all;
//
//    String fromname;
//    SharedPreferences pf;
//    String tonames, torid;
//    int hid, rid;
//    String hname, htitle, htime, hword;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_myfriend);
//        list_all = (ListView) findViewById(R.id.list_myfriend);
//
//
//        pf = getSharedPreferences("usertext", Activity.MODE_PRIVATE);
//        torid = pf.getString("rid", null);
//        tonames = pf.getString("rname", null);
//
//        thread = new Thread(MyfriendActivity.this);
//        thread.start();
//
//
//        list_all.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View arg1,
//                                    int arg2, long arg3) {
//
//                FriendInfo fr = (FriendInfo) parent.getItemAtPosition(arg2);
//                fromname = fr.getFriendname();
//                Log.e(TAG, "--------------点击获取好友名字-----" + fromname);
//                Intent dd = new Intent();
//                dd.setClass(MyfriendActivity.this, Reply.class);
//                dd.putExtra("fromname", fromname);
//                startActivity(dd);
//
//            }
//
//        });
//
//
//    }
//
//    public void getData() {
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("jrid", torid);
//
//
//        String aa = HttpUtils.submitPostData(map, "utf-8", Config.url_myfriend);
//        Log.e(TAG, "-------------------------" + aa);
//        List<FriendInfo> us = MyfriendUtil.getfd(aa);
//
//        // android多线程规定：子线程中不可以更改UI
//        // 从系统的消息池中拿消息，节省资源
//        Message msg = Message.obtain();
//        msg.obj = us;
//        msg.what = 2;
//        handler.sendMessage(msg);
//
//
//    }
//
//    Handler handler = new Handler() {
//        public void handleMessage(android.os.Message msg) {
//            switch (msg.what) {
//                case 2:
//
//                    @SuppressWarnings("unchecked")
//                    List<FriendInfo> us = (List<FriendInfo>) msg.obj;
//                    list_all.setAdapter(new MyfriendAdapter(MyfriendActivity.this, us));
//
//                    break;
//
//            }
//        }
//
//        ;
//    };
//
//    @Override
//    public void run() {
//        // TODO Auto-generated method stub
//        getData();
//    }
//
//    //返回按钮
//    public void btn_back(View v) {
//        finish();
//    }
//}