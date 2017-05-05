//package lins.com.stgl.ui;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import lins.com.stgl.R;
//import lins.com.stgl.adapter.HdGadapter;
//import lins.com.stgl.bean.HuoDong;
//import lins.com.stgl.json.HdongUtil;
//import lins.com.stgl.utils.HttpUtils;
//import lins.com.stgl.utils.Config;
//
//public class HDongdelete extends Activity implements Runnable {
//
//    private static final String TAG = "HDongdelete";
//    LinearLayout images_layout;
//    //String url = "http://192.168.1.102:8080/STGLserver/show_hd";
//    View view, view2, view3;
//    String height = "";
//    private Thread thread;
//    private HdGadapter myd;
//    private ListView contentListView;
//    // private TextView username,head_txt;
//
//    TextView ha;
//    SharedPreferences pf;
//    String userid, username, stringhid, clue, school;
//    int hid, rid;
//    String hname, htitle, htime, hword;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_hd_del);
//        contentListView = (ListView) findViewById(R.id.list_hd);
//        ha = (TextView) findViewById(R.id.head_txt);
//
//        pf = getSharedPreferences("usertext", Activity.MODE_PRIVATE);
//        userid = pf.getString("rid", null);
//        username = pf.getString("rname", null);
//        clue = pf.getString("clue", null);
//        school = pf.getString("school", null);
//
//        thread = new Thread(HDongdelete.this);
//        thread.start();
//
//        contentListView.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View arg1, int arg2,
//                                    long arg3) {
//
//                HuoDong hd = (HuoDong) parent.getItemAtPosition(arg2);
//                hid = hd.getHid();
//                // rid=hd.getRid();
//                hname = hd.getHname();
//                htitle = hd.getHtitle();
//                htime = hd.getHtime();
//                hword = hd.getHword();
//
//                stringhid = Integer.toString(hid);
//                LayoutInflater inf = LayoutInflater.from(HDongdelete.this);
//
//                View mydl = inf.inflate(R.layout.hd_dialog, null);
//                TextView ys = (TextView) mydl.findViewById(R.id.pop_yes);
//                TextView no = (TextView) mydl.findViewById(R.id.pop_no);
//
//                AlertDialog.Builder bd = new AlertDialog.Builder(
//                        HDongdelete.this);
//                bd.setTitle(null);
//                bd.setView(mydl);
//                ys.setOnClickListener(new OnClickListener() {
//
//                    @Override
//                    public void onClick(View arg0) {
//                        // TODO Auto-generated method stub
//                        Thread t = new Thread(new Runnable() {
//
//                            @Override
//                            public void run() {
//                                // TODO Auto-generated method stub
//                                Map<String, String> map = new HashMap<String, String>();
//                                map.put("jhid", stringhid);
//
//                                HttpUtils.submitPostData(map, "utf-8",
//                                        Config.url_hdong_del);
//                                finish();
//                            }
//
//                        });
//                        Toast.makeText(HDongdelete.this, "操作成功",
//                                Toast.LENGTH_SHORT).show();
//                        t.start();
//
//                        startActivity(new Intent(HDongdelete.this,
//                                HDongdelete.class));
//                    }
//                });
//                no.setOnClickListener(new OnClickListener() {
//
//                    @Override
//                    public void onClick(View arg0) {
//                        // TODO Auto-generated method stub
//                        startActivity(new Intent(HDongdelete.this,
//                                HDongdelete.class));
//                        finish();
//
//                    }
//                });
//                bd.create().show();
//
//            }
//
//        });
//
//    }
//
//    public void getData() {
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("name", username);
//        map.put("clue", clue);
//        map.put("school", school);
//
//        String aa = HttpUtils.submitPostData(map, "utf-8",
//                Config.url_hdong_del_show);
//        Log.e(TAG, "-------------------------" + aa);
//		/*
//		 * String result; try { result = GetData.getJsonData(url);
//		 */
//        List<HuoDong> hdong = HdongUtil.huodong(aa);
//
//        // android多线程规定：子线程中不可以更改UI
//        // 从系统的消息池中拿消息，节省资源
//        Message msg = Message.obtain();
//        msg.obj = hdong;
//        msg.what = 2;
//        handler.sendMessage(msg);
//
//		/*
//		 * } catch (IOException e) { // TODO Auto-generated catch block
//		 * e.printStackTrace(); }
//		 */
//    }
//
//    Handler handler = new Handler() {
//        public void handleMessage(android.os.Message msg) {
//            switch (msg.what) {
//                case 2:
//
//                    @SuppressWarnings("unchecked")
//                    List<HuoDong> hdong = (List<HuoDong>) msg.obj;
//                    contentListView.setAdapter(new HdGadapter(HDongdelete.this,
//                            hdong));
//                    break;
//
//            }
//        };
//    };
//
//    @Override
//    public void run() {
//        // TODO Auto-generated method stub
//        getData();
//    }
//
//    // 返回按钮
//    public void btn_back(View v) {
//        finish();
//    }
//}
