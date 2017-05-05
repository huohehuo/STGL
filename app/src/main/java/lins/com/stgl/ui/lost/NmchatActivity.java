//package lins.com.stgl.ui;
//
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.view.View;
//import android.widget.AbsListView;
//import android.widget.AbsListView.OnScrollListener;
//import android.widget.ListView;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//import lins.com.stgl.R;
//import lins.com.stgl.adapter.NmchatAdapter;
//import lins.com.stgl.utils.GetData;
//import lins.com.stgl.json.NmchatUtil;
//import lins.com.stgl.utils.Config;
//
//
//public class NmchatActivity extends Activity {
//    private static final String TAG = "NmchatActivity";
//    // String url = "http://192.168.1.102:8080/STGLserver/show_nmchat";
//
//    private ListView contentListView;
//    String username, sex, clue, school;
//    int hid, rid;
//    String hname, htitle, htime, hword;
//    private Thread thread_nm;
//    TextView btn_nmsay;
//
//    private ProgressBar progreesbar;
//    private String getNmchat;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        // TODO Auto-generated method stub
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_nmchat);
//        contentListView = (ListView) findViewById(R.id.list_nm);
//        btn_nmsay = (TextView) findViewById(R.id.btn_nmsay);
//        progreesbar = (ProgressBar) findViewById(R.id.progressbar);
//
//        new NmAsyncTask().execute(Config.url_nmchat);
//        //new Thread(mRunnable).start();
//
//        btn_nmsay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(NmchatActivity.this, AddNmchat.class));
//                finish();
//            }
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
//                        Toast.makeText(getApplicationContext(), "正在加载更多",
//                                Toast.LENGTH_SHORT).show();
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
//
//    }
//
//    // Runnable实现
//    Runnable mRunnable = new Runnable() {
//        @Override
//        public void run() {
//            try {
//                Thread.sleep(1000);// 暂停2秒再发送消息
//                getData();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            // }
//        }
//    };
//
//    public void getData() {
//
//
//        try {
//            getNmchat = GetData.getJsonData(Config.url_nmchat);
//
//            List<Map<String, Object>> nmc = NmchatUtil.nmchat(getNmchat);
//            Log.e(TAG, "-------------------------" + getNmchat);
//
//            // android多线程规定：子线程中不可以更改UI
//            // 从系统的消息池中拿消息，节省资源
//            Message msg = Message.obtain();
//            msg.obj = nmc;
//            msg.what = 2;
//            handler_nm.sendMessage(msg);
//
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    Handler handler_nm = new Handler() {
//        public void handleMessage(android.os.Message msg) {
//            switch (msg.what) {
//                case 2:
//
//                    @SuppressWarnings("unchecked")
//                    List<Map<String, Object>> nmc = (List<Map<String, Object>>) msg.obj;
//                    contentListView.setAdapter(new NmchatAdapter(
//                            NmchatActivity.this, nmc));
//                    break;
//
//            }
//        };
//    };
//
//    class NmAsyncTask extends AsyncTask<String, Void, List<Map<String,Object>>>
//    {
//
//        @Override
//        protected void onPreExecute() {
//            // TODO Auto-generated method stub
//            super.onPreExecute();
//            progreesbar.setVisibility(View.VISIBLE);
//        }
//        @Override
//        protected List<Map<String, Object>> doInBackground(String... arg0) {
//            // TODO Auto-generated method stub
//
//            String url = arg0[0];
//            try {
//                getNmchat = GetData.getJsonData(url);
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//            List<Map<String, Object>> nmc = NmchatUtil.nmchat(getNmchat);
//            Log.e(TAG, "-------------------------" + getNmchat);
//
//            // android多线程规定：子线程中不可以更改UI
//            // 从系统的消息池中拿消息，节省资源
//			/*	Message msg = Message.obtain();
//				msg.obj = nmc;
//				msg.what = 2;
//				handler_nm.sendMessage(msg);
//*/
//
//            return nmc;
//        }
//
//        @Override
//        protected void onPostExecute(List<Map<String, Object>> result) {
//            // TODO Auto-generated method stub
//            super.onPostExecute(result);
//            progreesbar.setVisibility(View.GONE);
//            contentListView.setAdapter(new NmchatAdapter(
//                    NmchatActivity.this, result));
//        }
//    }
//    // 返回按钮
//    public void btn_back(View v) {
//        finish();
//    }
//}
