//package lins.com.stgl;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.SharedPreferences.Editor;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.GridLayout;
//import android.widget.ImageButton;
//import android.widget.PopupWindow;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import lins.com.stgl.bean.Gonggao;
//import lins.com.stgl.json.GgaoUtil;
//import lins.com.stgl.ui.AddHdong;
//import lins.com.stgl.ui.BBSActivity;
//import lins.com.stgl.ui.ChangeData;
//import lins.com.stgl.ui.Forvip;
//import lins.com.stgl.ui.HDongdelete;
//import lins.com.stgl.ui.MyfriendActivity;
//import lins.com.stgl.ui.ClubManActivity;
//import lins.com.stgl.ui.HDongActivity;
//import lins.com.stgl.ui.NmChatActivity;
//import lins.com.stgl.utils.HttpUtils;
//import lins.com.stgl.utils.Config;
//
//public class MainActivity extends Activity {
//    private Context mContext = null;
//    private static final String TAG = "MainActivity";
//
//    private Context brContext;
//    private ImageButton btn_img_myside;
//    Thread gg;
//    String toname, torid, tosex, toclue, tovip, toschool;
//    SharedPreferences pf;
//    boolean chackvip;
//    int gid;
//    Timer timer;
//    TimerTask task;
//    private String gname, gclue, gonggao, school,chack;
//    private TextView txtbtn_hd, txtbtn_allst, txt_gonggao, txt_lyb, txtbtn_nm;
//    private Button pop_change, pop_hd_add, pop_hd_set, pop_myfriends, pop_gg,
//            pop_forvip;
//    private TextView xingbie, xibie, now_user;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        init();
//
//        pf = getSharedPreferences("usertext", Activity.MODE_PRIVATE);
//        torid = pf.getString("rid", null);
//        toname = pf.getString("rname", null);
//        tosex = pf.getString("sex", null);
//        toclue = pf.getString("clue", null);
//        tovip = pf.getString("vip", null);
//        toschool = pf.getString("school", null);
//        mContext = MainActivity.this;
//
//        new Thread(mRunnable).start();
//        // 活动大厅
//        txtbtn_hd.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//
//                startActivity(new Intent(MainActivity.this, HDongActivity.class));
//                MainActivity.this.overridePendingTransition(R.anim.push_up_in,
//                        R.anim.push_up_out);
//            }
//        });
//
//        // 全体社团成员
//        txtbtn_allst.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                startActivity(new Intent(MainActivity.this,
//                        ClubManActivity.class));
//                MainActivity.this.overridePendingTransition(R.anim.push_up_in,
//                        R.anim.push_up_out);
//            }
//        });
//
//        // 匿名区
//        txtbtn_nm.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                startActivity(new Intent(MainActivity.this,
//                        NmChatActivity.class));
//                MainActivity.this.overridePendingTransition(R.anim.push_up_in,
//                        R.anim.push_up_out);
//            }
//        });
//        // 个人留言板
//        txt_lyb.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                startActivity(new Intent(MainActivity.this, BBSActivity.class));
//                MainActivity.this.overridePendingTransition(R.anim.push_up_in,
//                        R.anim.push_up_out);
//            }
//        });
//
//        // 右上角pop菜单
//        btn_img_myside.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                if (tovip.equals("Y")) {
//                    showPopupWindow(arg0);
//                    // Toast.makeText(MainActivity.this, "这是vip-",
//                    // Toast.LENGTH_SHORT).show();
//                } else {
//                    showPopupWindow2(arg0);
//                    // Toast.makeText(MainActivity.this, "这不是vip-",
//                    // Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        timer = new Timer();
//        final Handler handlerg = new Handler(){
//
//            public void handleMessage(Message msg) {
//                switch (msg.what) {
//                    case 1:
//                        new Thread(mRunnable2).start();
//                        break;
//                }
//                super.handleMessage(msg);
//            }
//
//        };
//        task = new TimerTask(){
//
//            public void run() {
//                Message message = new Message();
//                message.what = 1;
//                handlerg.sendMessage(message);
//            }
//
//        };
//        timer.schedule(task,0,5*10000);
//    }
//    // Runnable实现
//    Runnable mRunnable = new Runnable() {
//        @Override
//        public void run() {
//
//            try {
//                Thread.sleep(1000);// 暂停2秒再发送消息
//
//                getGG();
//                //chackgg();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            // }
//        }
//    };
//
//    // Runnable实现
//    Runnable mRunnable2 = new Runnable() {
//        @Override
//        public void run() {
//
//            try {
//                Thread.sleep(3000);// 暂停2秒检测是否有新的公告
//
//                //getGG();
//                chackgg();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            // }
//        }
//    };
//
//    // 右上角ppop菜单
//    private void showPopupWindow(View v) {
//        View pop = LayoutInflater.from(mContext).inflate(R.layout.pop_menu,
//                null);
//        now_user = (TextView) pop.findViewById(R.id.now_name);
//        xingbie = (TextView) pop.findViewById(R.id.xingbie);
//        xibie = (TextView) pop.findViewById(R.id.xibie);
//        pop_change = (Button) pop.findViewById(R.id.pop_change);
//        pop_hd_add = (Button) pop.findViewById(R.id.pop_hd_add);
//        pop_hd_set = (Button) pop.findViewById(R.id.pop_hd_set);
//        pop_myfriends = (Button) pop.findViewById(R.id.pop_myfriends);
//        pop_gg = (Button) pop.findViewById(R.id.pop_gonggao);
//        Log.e(TAG, "-------------------------" + toname + tosex);
//        now_user.setText(toname);
//        xingbie.setText(tosex);
//        xibie.setText(toclue);
//
//        pop_change.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                startActivity(new Intent(MainActivity.this, ChangeData.class));
//                MainActivity.this.overridePendingTransition(
//                        R.anim.in_from_right, R.anim.out_to_left);
//            }
//        });
//        pop_hd_add.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                startActivity(new Intent(MainActivity.this, AddHdong.class));
//                MainActivity.this.overridePendingTransition(
//                        R.anim.in_from_right, R.anim.out_to_left);
//            }
//        });
//        pop_hd_set.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                startActivity(new Intent(MainActivity.this, HDongdelete.class));
//                MainActivity.this.overridePendingTransition(
//                        R.anim.in_from_right, R.anim.out_to_left);
//            }
//        });
//        pop_myfriends.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                startActivity(new Intent(MainActivity.this,
//                        MyfriendActivity.class));
//                MainActivity.this.overridePendingTransition(
//                        R.anim.in_from_right, R.anim.out_to_left);
//            }
//        });
//        pop_gg.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                startActivity(new Intent(MainActivity.this, GgaoUpdata.class));
//                MainActivity.this.overridePendingTransition(
//                        R.anim.in_from_right, R.anim.out_to_left);
//                // finish();
//            }
//        });
//
//        final PopupWindow popwd = new PopupWindow(pop,
//                GridLayout.LayoutParams.WRAP_CONTENT,
//                GridLayout.LayoutParams.WRAP_CONTENT, true);
//        popwd.setTouchable(true);
//        popwd.setTouchInterceptor(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View arg0, MotionEvent arg1) {
//                // TODO Auto-generated method stub
//                return false;
//            }
//        });
//
//        popwd.setBackgroundDrawable(getResources().getDrawable(
//                R.drawable.ic_hd_set));
//        popwd.setAnimationStyle(R.style.PopupAnimation);
//        // popwd.showAtLocation(findViewById(R.id.parent), Gravity.CENTER|
//        // Gravity.CENTER, 0, 0);
//        popwd.update();
//        popwd.setWidth(450);
//        // popwd.setHeight(500);
//        popwd.showAsDropDown(v);
//
//    }
//
//    // 右上角ppop菜单
//    private void showPopupWindow2(View v) {
//        View pop = LayoutInflater.from(mContext).inflate(R.layout.pop_menu2,
//                null);
//        now_user = (TextView) pop.findViewById(R.id.now_name2);
//        xingbie = (TextView) pop.findViewById(R.id.xingbie2);
//        xibie = (TextView) pop.findViewById(R.id.xibie2);
//        pop_change = (Button) pop.findViewById(R.id.pop_change2);
//        pop_hd_add = (Button) pop.findViewById(R.id.pop_hd_add2);
//        pop_hd_set = (Button) pop.findViewById(R.id.pop_hd_set2);
//        pop_myfriends = (Button) pop.findViewById(R.id.pop_myfriends2);
//        pop_forvip = (Button) pop.findViewById(R.id.pop_forvip);
//        // pop_gg=(Button)pop.findViewById(R.id.pop_gonggao);
//        Log.e(TAG, "-------------------------" + toname + tosex);
//        now_user.setText(toname);
//        xingbie.setText(tosex);
//        xibie.setText(toclue);
//
//        pop_change.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                startActivity(new Intent(MainActivity.this, ChangeData.class));
//                MainActivity.this.overridePendingTransition(
//                        R.anim.in_from_right, R.anim.out_to_left);
//            }
//        });
//        pop_hd_add.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                startActivity(new Intent(MainActivity.this, AddHdong.class));
//                MainActivity.this.overridePendingTransition(
//                        R.anim.in_from_right, R.anim.out_to_left);
//            }
//        });
//        pop_hd_set.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                startActivity(new Intent(MainActivity.this, HDongdelete.class));
//                MainActivity.this.overridePendingTransition(
//                        R.anim.in_from_right, R.anim.out_to_left);
//            }
//        });
//        pop_myfriends.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                startActivity(new Intent(MainActivity.this,
//                        MyfriendActivity.class));
//                MainActivity.this.overridePendingTransition(
//                        R.anim.in_from_right, R.anim.out_to_left);
//            }
//        });
//        pop_forvip.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                startActivity(new Intent(MainActivity.this, Forvip.class));
//                MainActivity.this.overridePendingTransition(
//                        R.anim.in_from_right, R.anim.out_to_left);
//            }
//        });
//
//        final PopupWindow popwd = new PopupWindow(pop,
//                GridLayout.LayoutParams.WRAP_CONTENT,
//                GridLayout.LayoutParams.WRAP_CONTENT, true);
//        popwd.setTouchable(true);
//        popwd.setTouchInterceptor(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View arg0, MotionEvent arg1) {
//                // TODO Auto-generated method stub
//                return false;
//            }
//        });
//
//        popwd.setBackgroundDrawable(getResources().getDrawable(
//                R.drawable.ic_hd_set));
//        popwd.setAnimationStyle(R.style.PopupAnimation);
//        // popwd.showAtLocation(findViewById(R.id.parent), Gravity.CENTER|
//        // Gravity.CENTER, 0, 0);
//        popwd.update();
//        popwd.setWidth(450);
//        // popwd.setHeight(500);
//        popwd.showAsDropDown(v);
//
//    }
//
//    private void chackgg() {
//
//        Log.e(TAG, "---chack---");
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("jclue", toclue);
//        map.put("jschool", toschool);
//        String aa = HttpUtils.submitPostData(map, "utf-8",
//                Config.url_main_gg);
//
//        List<Gonggao> us = GgaoUtil.gonggao(aa);
//        if(aa.toString().length() == 0)
//        {
//            // android多线程规定：子线程中不可以更改UI
//            // 从系统的消息池中拿消息，节省资源
//            Intent intent = new Intent();
//            intent.setAction("android.intent.action.BroadcastReceiverTest");
//            intent.putExtra("content", "网络已经断开，请检查重试~！");
//            sendBroadcast(intent);
//
//        }else
//        {
//            Iterator<Gonggao> it = us.iterator();
//            while (it.hasNext()) {
//                Gonggao user = (Gonggao) it.next();
//                chack = user.getGonggao();
//            }
//            SharedPreferences pfget=getSharedPreferences("ggchack", Activity.MODE_PRIVATE);
//            String lastgg=pfget.getString("gg",null);
//            if(chack.length()==lastgg.length())
//            {
//                Log.e(TAG, "---<没有新公告>--");
//
//            }else
//            {
//                Log.e(TAG, "--<有新消息>:" + chack);
//                SharedPreferences sp = getSharedPreferences("ggchack",Activity.MODE_PRIVATE);
//                Editor ed = sp.edit();
//                ed.putString("gg", chack);
//                ed.commit();
//
//                Message message = new Message();
//                Bundle bundle = new Bundle();
//                // android多线程规定：子线程中不可以更改UI
//                // 从系统的消息池中拿消息，节省资源
//                bundle.putString("msg", chack);
//                message.setData(bundle);
//                handler.sendMessage(message);
//
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.BroadcastReceiverTest");
//                intent.putExtra("content", chack);
//                sendBroadcast(intent);
//            }
//        }
//
//
//
//    }
//    private void getGG() {
//
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("jclue", toclue);
//        map.put("jschool", toschool);
//        String aa = HttpUtils.submitPostData(map, "utf-8",
//                Config.url_main_gg);
//        Log.e(TAG, "-------------------------" + aa);
//        List<Gonggao> us = GgaoUtil.gonggao(aa);
//
//        Iterator<Gonggao> it = us.iterator();
//        while (it.hasNext()) {
//            Gonggao user = (Gonggao) it.next();
//            gid = user.getGid();
//            gname = user.getGname();
//            gclue = user.getGclue();
//            gonggao = user.getGonggao();
//            school = user.getOfschool();
//
//        }
//        SharedPreferences sp = getSharedPreferences("ggchack",Activity.MODE_PRIVATE);
//        Editor ed = sp.edit();
//        ed.putString("gg", gonggao);
//        ed.commit();
//
//        Message message = new Message();
//        Bundle bundle = new Bundle();
//        // android多线程规定：子线程中不可以更改UI
//        // 从系统的消息池中拿消息，节省资源
//        bundle.putString("msg", gonggao);
//        message.setData(bundle);
//        handler.sendMessage(message);
//
//    }
//
//    private void init() {
//        // TODO Auto-generated method stub
//        txtbtn_allst = (TextView) findViewById(R.id.btn_txt_allst);
//        txtbtn_hd = (TextView) findViewById(R.id.btn_txt_hd);
//        txt_gonggao = (TextView) findViewById(R.id.txt_gg_xibu);
//        txt_lyb = (TextView) findViewById(R.id.txt_lyb);
//        txtbtn_nm = (TextView) findViewById(R.id.btn_txt_nmchat);
//
//        btn_img_myside = (ImageButton) findViewById(R.id.img_myside);
//        // txt_gonggao = (TextView) findViewById(R.id.txt_gonggao);
//        // username = (TextView) findViewById(R.id.username);
//    }
//
//    // 显示toast线程
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message message) {
//            super.handleMessage(message);
//            Bundle bundle = message.getData();
//            String msg = bundle.getString("msg");
//            txt_gonggao.setText(msg);
//            // Toast.makeText(getApplicationContext(), msg,
//            // Toast.LENGTH_LONG).show();
//        }
//    };
//    // 显示toast线程
//    private Handler handler2 = new Handler() {
//        @Override
//        public void handleMessage(Message message) {
//            super.handleMessage(message);
//            Bundle bundle = message.getData();
//            String msg = bundle.getString("msg");
//
//            Toast.makeText(getApplicationContext(), msg,
//                    Toast.LENGTH_SHORT).show();
//        }
//    };
//
//
//
//
//
//    /**
//     * 菜单、返回键响应
//     */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // TODO Auto-generated method stub
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            exitBy2Click(); // 调用双击退出函数
//        }
//        return false;
//    }
//
//    /**
//     * 双击退出函数
//     */
//    private static Boolean isExit = false;
//
//    private void exitBy2Click() {
//        Timer tExit = null;
//        if (isExit == false) {
//            isExit = true; // 准备退出
//            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
//            tExit = new Timer();
//            tExit.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    isExit = false; // 取消退出
//                }
//            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
//
//        } else {
//            finish();
//            System.exit(0);
//        }
//    }
//}
//
