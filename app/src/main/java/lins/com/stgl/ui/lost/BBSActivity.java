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
//import android.widget.AbsListView;
//import android.widget.AbsListView.OnScrollListener;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import lins.com.stgl.R;
//import lins.com.stgl.adapter.ChatAdapter;
//import lins.com.stgl.json.ChatUtil;
//import lins.com.stgl.ui.ReplyActivity;
//import lins.com.stgl.utils.HttpUtils;
//import lins.com.stgl.utils.Config;
//import lins.com.stgl.view.ReFlashListView;
//
//
//public class BBSActivity extends Activity implements ReFlashListView.IReflashListener {
//    private static final String TAG = "ChatActivity";
//    ProgressBar progressbar;
//    ReFlashListView list_chat;
//    EditText input_txt;
//    TextView head_name;
//    SharedPreferences pf;
//    Timer timer;
//    TimerTask task;
//    String toname, txt_myf, fromname, friendname;
//    private Thread thread;
//    private ChatAdapter chatAdapter;
//    List<Map<String, Object>> listData;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        // TODO Auto-generated method stub
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bbs);
//        init();
//        pf = getSharedPreferences("usertext", Activity.MODE_PRIVATE);
//        toname = pf.getString("rname", null);
//
//        //new BBSAsyncTask().execute(Config.url_bbs);
//
//        new Thread(mRunnable).start();
//
//        Log.e(TAG, "---------获取内部数据--c-----");
//
//        list_chat.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View arg1, int arg2,
//                                    long arg3) {
//                // TODO Auto-generated method stub
//                Map<String, Object> a = (Map<String, Object>) parent
//                        .getItemAtPosition(arg2);
//                friendname = (String) a.get("mfromname");
//
//                Intent dd = new Intent();
//                dd.setClass(BBSActivity.this, ReplyActivity.class);
//                dd.putExtra("fromname", friendname);
//                startActivity(dd);
//
//            }
//        });
//        list_chat.setOnScrollListener(new OnScrollListener() {
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
//            @Override
//            public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
//                // TODO Auto-generated method stub
//            }
//        });
//
//    }
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
//        }
//    };
//    Runnable mRunnable2 = new Runnable() {
//        @Override
//        public void run() {
//            try {
//                Thread.sleep(1000);// 暂停2秒再发送消息
//                showList(listData);
//                //通知listview刷新数据完毕
//                list_chat.reflashComplete();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    };
//    public void getData() {
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("jisname", toname);
//        String aa = HttpUtils.submitPostData(map, "utf-8", Config.url_bbs);
//        listData = ChatUtil.getchat(aa);
//        //List<Map<String, Object>> cht = ChatUtil.getchat(aa);
//        Log.e(TAG, "-------------获取的数据-----" + aa);
//        if (aa.length() == 0) {
//
//        }
//        Message msg = Message.obtain();
//        msg.obj = listData;
//        msg.what = 2;
//        handler.sendMessage(msg);
//    }
//    Handler handler = new Handler() {
//        public void handleMessage(android.os.Message msg) {
//            switch (msg.what) {
//                case 2:
//                    @SuppressWarnings("unchecked")
//                    List<Map<String, Object>> listData = (List<Map<String, Object>>) msg.obj;
//                    list_chat.setAdapter(new ChatAdapter(BBSActivity.this, listData));
//                    break;
//            }
//        };
//    };
//
//    public void getListData() {
//
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("jisname", toname);
//        String aa = HttpUtils.submitPostData(map, "utf-8", Config.url_bbs);
//        listData = ChatUtil.getchat(aa);
//        //List<Map<String, Object>> cht = ChatUtil.getchat(aa);
//        Log.e(TAG, "-------------获取的数据-----" + aa);
//        if (aa.length() == 0) {
//
//        }
//    }
//    /*class BBSAsyncTask extends
//            AsyncTask<String, Void, List<Map<String, Object>>> {
//
//        @Override
//        protected void onPreExecute() {
//            // TODO Auto-generated method stub
//            super.onPreExecute();
//            progressbar.setVisibility(View.VISIBLE);
//        }
//
//        @Override
//        protected List<Map<String, Object>> doInBackground(String... arg0) {
//            // TODO Auto-generated method stub
//            String url = arg0[0];
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("jisname", toname);
//
//            // Log.e(TAG, "-------------提交网络数据-----"+htoname+fromname);
//            String aa = HttpUtils.submitPostData(map, "utf-8", url);
//            listData = ChatUtil.getchat(aa);
//            //List<Map<String, Object>> cht = ChatUtil.getchat(aa);
//            Log.e(TAG, "-------------获取的数据-----" + aa);
//            if (aa.length() == 0) {
//
//            }
//            return listData;
//        }
//
//        @Override
//        protected void onPostExecute(List<Map<String, Object>> cht) {
//            // TODO Auto-generated method stub
//            super.onPostExecute(cht);
//            progressbar.setVisibility(View.GONE);
//            //List<Map<String, Object>> cht = (List<Map<String, Object>>) msg.obj;
//            list_chat.setAdapter(new ChatAdapter(BBSActivity.this, cht));
//        }
//
//    }*/
//    public void init() {
//        list_chat = (ReFlashListView) findViewById(R.id.chat_list);
//        // input_txt=(EditText) findViewById(R.id.send_input);
//        head_name = (TextView) findViewById(R.id.chat_to);
//        progressbar = (ProgressBar) findViewById(R.id.progressbar);
//    }
//    // 返回按钮
//    public void btn_back(View v) {
//        finish();
//    }
//    private void showList(List<Map<String, Object>> listData) {
//        if (chatAdapter == null) {
//            list_chat = (ReFlashListView) findViewById(R.id.chat_list);
//            list_chat.setInterface(this);
//            chatAdapter = new ChatAdapter(this, listData);
//            list_chat.setAdapter(chatAdapter);
//        } else {
//            chatAdapter.onDateChange(listData);
//        }
//    }
//    @Override
//    public void onReflash() {
//        // TODO Auto-generated method stub
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//                //获取最新数据
//                getListData();
//                //通知界面显示
//                showList(listData);
//                //通知listview 刷新数据完毕；
//                list_chat.reflashComplete();
//            }
//        }, 2000);
//
//    }
//
//}
