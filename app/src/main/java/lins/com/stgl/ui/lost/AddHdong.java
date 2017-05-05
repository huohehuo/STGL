//package lins.com.stgl.ui;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.view.View;
//import android.widget.DatePicker;
//import android.widget.DatePicker.OnDateChangedListener;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.TimePicker;
//import android.widget.TimePicker.OnTimeChangedListener;
//import android.widget.Toast;
//
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.Map;
//
//import lins.com.stgl.R;
//import lins.com.stgl.utils.HttpUtils;
//import lins.com.stgl.utils.Config;
//
//public class AddHdong extends Activity {
//    //Config urldata;
//    EditText htitle, hword,htime;
//    TextView btnInSaveButton;
//    Intent intent;
//    String toname,toschool,toclue;
//    String torid;
//    SharedPreferences pf;
//    private static final String TAG = "MainActivity";
//
//    //String url = "http://192.168.1.102:8080/STGLserver/wr_hd";
//    private int year;
//    private int month;
//    private int day;
//    private int hour;
//    private int minute;
//    private String gettime;
//    private DatePicker datepick;
//    private TimePicker timepick;
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_hd);
//        initView();
//
//        datepick.init(year,month,day,new OnDateChangedListener() {
//
//            @Override
//            public void onDateChanged(DatePicker arg0, int year, int month, int day) {
//                // TODO Auto-generated method stub
//                AddHdong.this.year = year;
//                AddHdong.this.month = month;
//                AddHdong.this.day = day;
//                //showDate(year,month+1,day,hour,minute);
//            }
//        });
//        timepick.setOnTimeChangedListener(new OnTimeChangedListener() {
//
//            @Override
//            public void onTimeChanged(TimePicker arg0, int hour, int minute) {
//                // TODO Auto-generated method stub
//                AddHdong.this.hour=hour;
//                AddHdong.this.minute=minute;
//                //showDate(year,month+1,day,hour,minute);
//            }
//        });
//
//        btnInSaveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(htitle.getText().length()==0 && hword.getText().length() == 0)
//                {
//                    Toast.makeText(getApplicationContext(), "发布失败，请确认发布信息的完整性。", Toast.LENGTH_SHORT)
//                            .show();
//                }else
//                {
//                    new Thread() {
//                        public void run() {
//                            addhd();
//                        }
//                    }.start();
//
//                }
//            }
//        });
//
//    }
//
//    //	private void showDate(int year,int month,int day,int hour,int minute)
////	{
////		gettime=new String("日期为："+year+"/"+month+"/"+day+"/"+hour+"/"+minute);
////	}
//    private void addhd() {
//        pf = getSharedPreferences("usertext", Activity.MODE_PRIVATE);
//        torid = pf.getString("rid", null);
//        toname = pf.getString("rname", null);
//        toclue = pf.getString("clue", null);
//        toschool= pf.getString("school", null);
//
//
//        String title = htitle.getText().toString();
//        //String time = htime.getText().toString();
//        String word = hword.getText().toString();
//        int mon=month+1;
//        gettime =new String(year+"/"+mon+"/"+day+"/"+hour+":"+minute);
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("jrid", torid);
//        map.put("jname", toname);
//        map.put("jtitle", title);
//        map.put("jtime", gettime);
//        map.put("jword", word);
//        map.put("jschool", toschool);
//        map.put("jclue", toclue);
//        String aa = HttpUtils.submitPostData(map, "utf-8", Config.url_add_huodong);
//        Message message = new Message();
//        Bundle bundle2 = new Bundle();
//        bundle2.putString("msg", "发布成功");
//        message.setData(bundle2);
//        handler.sendMessage(message);
//
//
//        finish();
//
//
//        // startActivity(new Intent(LoginActivity.this,MainActivity.class));
//
//
//
//
//    }
//    // 显示toast线程
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message message) {
//            super.handleMessage(message);
//            Bundle bundle = message.getData();
//            String msg = bundle.getString("msg");
//            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT)
//                    .show();
//        }
//    };
//
//    public void initView()
//    {
//        htitle = (EditText) findViewById(R.id.title);
//        //htime = (EditText) findViewById(R.id.time);
//        hword = (EditText) findViewById(R.id.word);
//
//        datepick = (DatePicker) findViewById(R.id.datepicker);
//        timepick = (TimePicker) findViewById(R.id.timepicker);
//        Calendar c = Calendar.getInstance();
//        year = c.get(Calendar.YEAR);
//        month = c.get(Calendar.MONTH);
//        day = c.get(Calendar.DAY_OF_MONTH);
//        hour = c.get(Calendar.HOUR);
//        minute = c.get(Calendar.MINUTE);
//
//
//        btnInSaveButton = (TextView) findViewById(R.id.send);
//
//    }
//    //返回键
//    public void btn_back(View v) {
//        finish();
//    }
//
//}
//
