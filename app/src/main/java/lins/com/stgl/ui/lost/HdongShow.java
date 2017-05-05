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
//import android.view.View.OnClickListener;
//import android.widget.AbsListView;
//import android.widget.AbsListView.OnScrollListener;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import lins.com.stgl.R;
//import lins.com.stgl.adapter.PlAdapter;
//import lins.com.stgl.bean.PlunInfo;
//import lins.com.stgl.utils.HttpUtils;
//import lins.com.stgl.json.PLunUtil;
//import lins.com.stgl.utils.Config;
//
//public class HdongShow extends Activity{
//
//    private static final String TAG = "HdongShowActivity";
//    //String url="http://192.168.1.102:8080/STGLserver/show_pl";
//    private TextView txt_word,txt_title,txt_name,txt_time;
//    private Button btn_pl;
//    private Thread thread;
//    private PlAdapter myd;
//    private ListView list_pl;
//    String tohid,torid,tohname,tohtitle,tohtime,tohword,username,userid;
//    SharedPreferences pf;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        // TODO Auto-generated method stub
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.hd_show);
//        init();
//
//        new Thread(mRunnable).start();
//
//
//        pf = getSharedPreferences("usertext", Activity.MODE_PRIVATE);
//        userid = pf.getString("rid", null);
//        username = pf.getString("rname", null);
//
//        tohid =pf.getString("hid", null);
//        tohname = pf.getString("hname", null);
//        tohtitle =pf.getString("htitle", null);
//        tohtime=pf.getString("htime", null);
//        tohword=pf.getString("hword", null);
//
//
//        Log.e(TAG, "-------------------------"+tohid+tohname);
//        txt_name.setText("发布人: "+tohname);
//        txt_time.setText("发布日期: "+tohtime);
//        txt_title.setText(tohtitle);
//        txt_word.setText(tohword);
//
//        btn_pl.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                Intent gos=new Intent(HdongShow.this,AddPlun.class);
//
//                startActivity(gos);
//                finish();
//            }
//        });
//        list_pl.setOnScrollListener(new OnScrollListener() {
//
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scroll) {
//                // TODO Auto-generated method stub
//                if(scroll == OnScrollListener.SCROLL_STATE_IDLE)
//                {
//                    if(view.getLastVisiblePosition() == view.getCount()-1)
//                    {
//                        new Thread(mRunnable).start();
//                        Toast.makeText(getApplicationContext(), "正在加载更多", Toast.LENGTH_SHORT)
//                                .show();
//                    }
//
//                }
//            }
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
//
//            try {
//                Thread.sleep(1000);// 暂停2秒再发送消息
//
//                showpl();
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            // }
//        }
//    };
//
//    private void showpl()
//    {
//        //String ss="3";
//        Map<String,String> map = new HashMap<String,String>();
//        map.put("jhid", tohid);
//
//
//        String aa= HttpUtils.submitPostData(map, "utf-8", Config.url_hdong_show);
//
//        List<PlunInfo> pl = PLunUtil.pinglun(aa);
//
//        Message msg = Message.obtain();
//        msg.obj = pl;
//        msg.what = 2;
//        handler.sendMessage(msg);
//
//
//    }
//    public void init()
//    {
//        txt_name = (TextView) findViewById(R.id.txt_name);
//        txt_time = (TextView) findViewById(R.id.txt_time);
//        txt_word = (TextView) findViewById(R.id.txt_word);
//        txt_title = (TextView) findViewById(R.id.txt_title);
//
//        btn_pl = (Button) findViewById(R.id.btn_pl);
//        list_pl = (ListView) findViewById(R.id.list_pl);
//
//
//    }
//
//
//    Handler handler = new Handler() {
//        public void handleMessage(android.os.Message msg) {
//            switch (msg.what) {
//                case 2:
//
//                    @SuppressWarnings("unchecked")
//                    List<PlunInfo> pl = (List<PlunInfo>) msg.obj;
//                    list_pl.setAdapter(new PlAdapter(HdongShow.this, pl));
//                    break;
//
//            }
//        };
//    };
//    //返回按钮
//    public void btn_back2(View v)
//    {
//        finish();
//    }
//}
