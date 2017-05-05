//package lins.com.stgl.ui;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.SharedPreferences.Editor;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.view.View;
//import android.widget.AbsListView;
//import android.widget.AbsListView.OnScrollListener;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import lins.com.stgl.R;
//import lins.com.stgl.adapter.HdAdapter;
//import lins.com.stgl.bean.HuoDong;
//import lins.com.stgl.json.HdongUtil;
//import lins.com.stgl.utils.HttpUtils;
//import lins.com.stgl.utils.Config;
//
//public class HDongActivity extends Activity {
//
//    private static final String TAG = "HDongActivity";
//    LinearLayout images_layout;
//    //String url = "http://192.168.1.102:8080/STGLserver/show_allhd";
//    View view, view2, view3;
//
//    private Thread thread_hd;
//    private HdAdapter myd;
//    private ListView contentListView;
//    private ProgressBar progressbar;
//    //private TextView username,head_txt;
//    Timer timer;
//    TimerTask task;
//
//    SharedPreferences pf;
//    String userid, username, school;
//    int hid, rid;
//    String hname, htitle, htime, hword;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_hdong);
//        contentListView = (ListView) findViewById(R.id.list_hd);
//        progressbar = (ProgressBar) findViewById(R.id.progressbar);
//
//        pf = getSharedPreferences("usertext", Activity.MODE_PRIVATE);
//        userid = pf.getString("rid", null);
//        username = pf.getString("rname", null);
//        school = pf.getString("school", null);
//
//        new HdAsyncTask().execute(Config.url_hdong);
//        //new Thread(mRunnable).start();
//
//
//        contentListView.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View arg1,
//                                    int arg2, long arg3) {
//
//                HuoDong hd = (HuoDong) parent.getItemAtPosition(arg2);
//                hid = hd.getHid();
//                //rid=hd.getRid();
//                hname = hd.getHname();
//                htitle = hd.getHtitle();
//                htime = hd.getHtime();
//                hword = hd.getHword();
//
//                SharedPreferences sp = getSharedPreferences("usertext", Activity.MODE_PRIVATE);
//                Editor ed = sp.edit();
//                ed.putString("hid", Integer.toString(hid));
//                //ed.putString("rid", Integer.toString(rid));
//
//                ed.putString("hname", hname);
//                ed.putString("htitle", htitle);
//                ed.putString("htime", htime);
//                ed.putString("hword", hword);
//
//                ed.commit();
//
//
//                Intent gos = new Intent(HDongActivity.this, HdongShow.class);
//
//                startActivity(gos);
//                Log.e(TAG, "-------------------------" + hid + hname + rid + htitle + htime + hword);
//                System.out.println("-------------------------" + hid + hname + rid + htitle + htime + hword);
//                //Toast.makeText(HDongActivity.this, aa, Toast.LENGTH_SHORT).show();
//                //finish();
//            }
//
//        });
//
//        contentListView.setOnScrollListener(new OnScrollListener() {
//
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scroll) {
//                // TODO Auto-generated method stub
//                if (scroll == OnScrollListener.SCROLL_STATE_IDLE) {
//                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
//                        new Thread(mRunnable).start();
//                        Toast.makeText(getApplicationContext(), "正在加载更多", Toast.LENGTH_SHORT)
//                                .show();
//                    }
//
//                }
//            }
//
//            @Override
//            public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
//                // TODO Auto-generated method stub
//
//            }
//        });
//    }
//
//    // Runnable实现
//    Runnable mRunnable = new Runnable() {
//        @Override
//        public void run() {
//
//            try {
//                Thread.sleep(1000);// 暂停2秒再发送消息
//
//                getData();
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            // }
//        }
//    };
//
//    public void getData() {
//        Map<String, String> map = new HashMap<String, String>();
//
//        map.put("school", school);
//
//        String aa = HttpUtils.submitPostData(map, "utf-8", Config.url_hdong);
//        Log.e(TAG, "-------------------------" + aa);
//        List<HuoDong> hdong = HdongUtil
//                .huodong(aa);
//        // android多线程规定：子线程中不可以更改UI
//        // 从系统的消息池中拿消息，节省资源
//        Message msg = Message.obtain();
//        msg.obj = hdong;
//        msg.what = 2;
//        handler_hd.sendMessage(msg);
//
//    }
//
//    Handler handler_hd = new Handler() {
//        public void handleMessage(android.os.Message msg) {
//            switch (msg.what) {
//                case 2:
//
//                    @SuppressWarnings("unchecked")
//                    List<HuoDong> hdong = (List<HuoDong>) msg.obj;
//                    contentListView.setAdapter(new HdAdapter(HDongActivity.this, hdong));
//                    break;
//
//            }
//        }
//
//        ;
//    };
//
//    class HdAsyncTask extends AsyncTask<String, Void, List<HuoDong>> {
//        @Override
//        protected void onPreExecute() {
//            // TODO Auto-generated method stub
//            super.onPreExecute();
//            progressbar.setVisibility(View.VISIBLE);
//        }
//
//        @Override
//        protected List<HuoDong> doInBackground(String... arg) {
//            // TODO Auto-generated method stub
//            String url = arg[0];
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("school", school);
//            String aa = HttpUtils.submitPostData(map, "utf-8", url);
//            List<HuoDong> hdong = HdongUtil
//                    .huodong(aa);
//            return hdong;
//        }
//
//        @Override
//        protected void onPostExecute(List<HuoDong> result) {
//            // TODO Auto-generated method stub
//            super.onPostExecute(result);
//            progressbar.setVisibility(View.GONE);
//            contentListView.setAdapter(new HdAdapter(HDongActivity.this, result));
//
//        }
//
//    }
//
//    //返回按钮
//    public void btn_back(View v) {
//
//        finish();
//    }
//}
