//package lins.com.stgl.ui;
//
///**
// * Created by LINS on 2017/4/8.
// */
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
//import lins.com.stgl.adapter.AllmenAdapter;
//import lins.com.stgl.bean.User;
//import lins.com.stgl.json.AllmenUtil;
//import lins.com.stgl.utils.HttpUtils;
//import lins.com.stgl.utils.Config;
//
//public class AllmenActivity extends Activity implements Runnable {
//
//    private static final String TAG = "AllmenActivity";
//    LinearLayout images_layout;
//    //String url = "http://192.168.1.102:8080/STGLserver/show_allst";
//    View view, view2, view3;
//
//    private Thread thread_all;
//
//    private ListView list_all;
//
//    SharedPreferences pf;
//    String torid,toclue,toschool;
//    int befid;
//    String hname,htitle,htime,hword,befname,changefid;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_allst);
//        list_all = (ListView)findViewById(R.id.list_allsta);
//
//
//        pf = getSharedPreferences("usertext", Activity.MODE_PRIVATE);
//        torid = pf.getString("rid", null);
//        toclue = pf.getString("clue", null);
//        toschool = pf.getString("school", null);
//
//        thread_all = new Thread(AllmenActivity.this);
//        thread_all.start();
//
//
//        list_all.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View arg1,
//                                    int position, long arg3) {
//
//                User us = (User) parent.getItemAtPosition(position);
//                befid = us.getRid();
//                befname = us.getName();
//
//                changefid = Integer.toString(befid);
//                LayoutInflater inf = LayoutInflater.from(AllmenActivity.this);
//                View mydl = inf.inflate(R.layout.dialog_add_friend, null);
//                TextView ys = (TextView) mydl.findViewById(R.id.friend_yes);
//                TextView no = (TextView) mydl.findViewById(R.id.friend_no);
//
//                AlertDialog.Builder dlg=new AlertDialog.Builder(AllmenActivity.this);
//                dlg.setView(mydl);
//                ys.setOnClickListener(new OnClickListener() {
//
//                    @Override
//                    public void onClick(View arg0) {
//                        // TODO Auto-generated method stub
//                        Thread addfr= new Thread(new Runnable() {
//
//                            @Override
//                            public void run() {
//                                Message message = new Message();
//                                Bundle bundle = new Bundle();
//
//                                // TODO Auto-generated method stub
//                                Map<String,String> map = new HashMap<String,String>();
//                                map.put("jrid", torid);
//                                map.put("jfriendid", changefid);
//                                map.put("jfriendname", befname);
//                                String aa = HttpUtils.submitPostData(map, "utf-8", Config.url_friend_add);
//                                if (aa.equals("error")) {
//                                    bundle.putString("msg", "此人已是您的好友，无需重复添加");
//                                    message.setData(bundle);
//                                    handler2.sendMessage(message);
//                                    finish();
//
//                                    // startActivity(new Intent(LoginActivity.this,MainActivity.class));
//                                } else if (aa.equals("error2")){
//                                    bundle.putString("msg", "不能添加自己为好友 ");
//                                    message.setData(bundle);
//                                    handler2.sendMessage(message);
//                                    finish();
//                                }else if(aa.equals("success"))
//                                {
//                                    bundle.putString("msg", "添加好友成功 ");
//                                    message.setData(bundle);
//                                    handler2.sendMessage(message);
//                                    finish();
//                                }
//                                //finish();
//                            }
//                        });
//                        //Toast.makeText(AllmenActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
//                        addfr.start();
//                        startActivity(new Intent(AllmenActivity.this,AllmenActivity.class));
//                    }
//                });
//                dlg.create().show();
//
//                Log.e(TAG, "----------------c-----");
//
//            }
//
//        });
//
//
//    }
//    public void getData()
//    {
//        Map<String,String> map = new HashMap<String,String>();
//        map.put("jclue", toclue);
//        map.put("jschool", toschool);
//
//        String aa=HttpUtils.submitPostData(map, "utf-8", Config.url_allmen);
//        Log.e(TAG, "-------------------------"+aa);
//        List<User> us = AllmenUtil.stallmen(aa);
//
//        // android多线程规定：子线程中不可以更改UI
//        // 从系统的消息池中拿消息，节省资源
//        Message msg = Message.obtain();
//        msg.obj = us;
//        msg.what = 2;
//        handler.sendMessage(msg);
//
//    }
//
//    Handler handler = new Handler() {
//        public void handleMessage(android.os.Message msg) {
//            switch (msg.what) {
//                case 2:
//
//                    @SuppressWarnings("unchecked")
//                    List<User> us = (List<User>) msg.obj;
//                    list_all.setAdapter(new AllmenAdapter(AllmenActivity.this, us));
//
//                    break;
//
//            }
//        };
//    };
//
//
//    //显示toast线程
//    private Handler handler2 = new Handler(){
//        @Override
//        public void handleMessage(Message message) {
//            super.handleMessage(message);
//            Bundle bundle = message.getData();
//            String msg = bundle.getString("msg");
//            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
//        }
//    };
//
//    @Override
//    public void run() {
//        // TODO Auto-generated method stub
//        getData();
//    }
//
//    //返回按钮
//    public void btn_back(View v)
//    {
//        finish();
//    }
//
//}
