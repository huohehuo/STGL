//package lins.com.stgl.ui;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.ListView;
//
//import lins.com.stgl.R;
//import lins.com.stgl.bean.HuoDong;
//import lins.com.stgl.easyadapter.HdAdapter;
//import lins.com.stgl.utils.GetData;
//import lins.com.stgl.json.HdongUtil;
//
///**
// * Created by LINS on 2017/4/8.
// */
//
//public class test extends Activity{
//    private static final String TAG = "test";
//    private ListView contentListView;
//    private Thread thread;
//    private Button btn;
//    String url = "http://192.168.1.102:8080/STGLserver/show_hd";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.testview);
//        btn=(Button) findViewById(R.id.bb);
//        contentListView = (ListView)findViewById(R.id.list_test);
//
//        //uthread.start();
//        new Thread(mRunnable).start();
//        btn.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                new Thread(mRunnable).start();
//            }
//        });
//
//
//
//        Timer timer = new Timer();
//        final Handler handlerg = new Handler(){
//
//            public void handleMessage(Message msg) {
//                switch (msg.what) {
//                    case 1:
//                        btn.performClick();
//                        break;
//                }
//                super.handleMessage(msg);
//            }
//
//        };
//        TimerTask task = new TimerTask(){
//
//            public void run() {
//                Message message = new Message();
//                message.what = 1;
//                handlerg.sendMessage(message);
//            }
//
//        };
//        timer.schedule(task,0,1*1000);
//
//    }
//
//    // Runnable实现
//    Runnable mRunnable = new Runnable() {
//        @Override
//        public void run() {
//            //for (int i = 0; i < 100; i++) {
//            try {
//                Thread.sleep(1000);// 暂停2秒再发送消息
//                // temp = temp + i;
//                getData();
//                // 第一种发送消息的方式
//                // Message message = new Message();
//                // message.what = MESSAGE;
//                // mHandler.sendMessage(message);
//
//                // 第二种发送消息的方式,推荐使用这种
//                // Message message = mHandler.obtainMessage();
//                // mHandler.obtainMessage(MESSAGE).sendToTarget();
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            // }
//        }
//    };
//
//
//
//
//    public void getData()
//    {
//        String result;
//        try {
//            result = GetData.getJsonData(url);
//
//            List<HuoDong> hdong = HdongUtil
//                    .huodong(result);
//
//            Log.e(TAG, "-------------------------"+result);
//            // android多线程规定：子线程中不可以更改UI
//            // 从系统的消息池中拿消息，节省资源
//            Message msg = Message.obtain();
//            msg.obj = hdong;
//            msg.what = 2;
//            handler.sendMessage(msg);
//
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    Handler handler = new Handler() {
//        public void handleMessage(android.os.Message msg) {
//            switch (msg.what) {
//                case 2:
//
//                    @SuppressWarnings("unchecked")
//                    List<HuoDong> hdong = (List<HuoDong>) msg.obj;
//                    contentListView.setAdapter(new HdAdapter(test.this, hdong));
//                    break;
//
//            }
//        };
//    };
//
//
//}
