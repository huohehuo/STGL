//package lins.com.stgl.ui;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import lins.com.stgl.R;
//import lins.com.stgl.utils.HttpUtils;
//import lins.com.stgl.utils.Config;
//import lins.com.stgl.json.UserUtil;
//
//public class MineActivity extends Activity implements Runnable{
//    private ListView tlist;
//    private Intent intent;
//    private TextView username,headname;
//    private Thread thread;
//    String url = "http://192.168.1.102:8080/STGLserver/chackdata";
//    String toname,torid;
//    String rname,rpwd,rsex,rclue;
//    int rage;
//    SharedPreferences pf;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_myside);
//        headname=(TextView)findViewById(R.id.txt_my_name);
//        //findViewById(R.id.button1).setVisibility(View.INVISIBLE);
//
//        pf = getSharedPreferences("usertext", Activity.MODE_PRIVATE);
//        torid = pf.getString("rid", null);
//        toname = pf.getString("rname", null);
//
//
//        headname.setText(toname);
//
//        thread= new Thread(MineActivity.this);
//        thread.start();
//        findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                startActivity(new Intent(MineActivity.this,HDongDeleteActivity.class));
//            }
//        });
//        findViewById(R.id.button2).setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                startActivity(new Intent(MineActivity.this,AddHdActivity.class));
//
//            }
//        });
//
//
//        findViewById(R.id.fgtwo_data).setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//
//                // TODO Auto-generated method stub
//                Intent intent=new Intent();
//                intent.setClass(MineActivity.this,ChangeInfoActivity.class);
//                intent.putExtra("name", toname);
//                intent.putExtra("pwd", rpwd);
//                intent.putExtra("sex", rsex);
//                intent.putExtra("age", rage);
//                intent.putExtra("clue", rclue);
//                startActivity(intent);
//			/*startActivity(new Intent(getActivity()
//                    .getApplicationContext(),ChangeData.class));*/
//
//            }
//        });
//
//    }
//    private void login() {
//        //String outname = username.getText().toString();
//
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("name", toname);
//
//        String aa = HttpUtils.submitPostData(map, "utf-8", Config.url_mine);
//        List<User> us = UserUtil
//                .user(aa);
//        // android多线程规定：子线程中不可以更改UI
//        // 从系统的消息池中拿消息，节省资源
//        Iterator<User> it = us.iterator();
//        while(it.hasNext())
//        {
//            User user = (User) it.next();
//            //rid = user.getRid();
//            rname= user.getName();
//            rpwd =user.getPassword();
//            rsex = user.getSex();
//            rage = user.getAge();
//            rclue = user.getClue();
//        }
//
//    }
//    @Override
//    public void run() {
//        // TODO Auto-generated method stub
//
//        login();
//    }
//
//
//}
