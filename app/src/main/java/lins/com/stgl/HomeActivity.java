package lins.com.stgl;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.youth.banner.listener.OnBannerClickListener;

import java.lang.reflect.Field;

import lins.com.stgl.bean.AdData;
import lins.com.stgl.bean.GongGaoData;
import lins.com.stgl.bean.MsgBoardData;
import lins.com.stgl.databinding.ActivityHomeBinding;
import lins.com.stgl.rxservice.MySubscribe;
import lins.com.stgl.ui.AddHdActivity;
import lins.com.stgl.ui.ChangeInfoActivity;
import lins.com.stgl.ui.ClubManActivity;
import lins.com.stgl.ui.ForVipActivity;
import lins.com.stgl.ui.FriendActivity;
import lins.com.stgl.ui.HDongActivity;
import lins.com.stgl.ui.HDongDeleteActivity;
import lins.com.stgl.ui.MsgBoardActivity;
import lins.com.stgl.ui.NmChatActivity;
import lins.com.stgl.ui.UpdataGGActivity;
import lins.com.stgl.ui.base.BaseActivity;
import lins.com.stgl.ui.login.LoginActivity;
import lins.com.stgl.utils.Config;
import lins.com.stgl.utils.GlideImageLoader;
import lins.com.stgl.utils.KeyValueStorage;
import lins.com.stgl.utils.MyIntentService;

import static lins.com.stgl.utils.Config.A;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ActivityHomeBinding binding;
    private View view_nav_head;
    private TextView tv_name,tv_club;

    private Handler updataHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //收到广播的数据，并更新公告
            String gg = (String) msg.obj;
            binding.addxml.tvGonggao.setText(gg);
        }
    };
    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.navView.setNavigationItemSelectedListener(this);
        binding.addxml.topbar.ivTopArrow.setImageResource(R.drawable.home_ic_menu);
        binding.addxml.topbar.tvTopTitle.setText("主页");
        binding.addxml.topbar.tvTopTitle.setTextColor(Color.BLACK);
        setDrawerLeftEdgeSize(this, binding.drawerLayout, 0.2f);//设置抽屉滑动响应范围

        if ("Y".equals(App.getIsVip())){
            binding.addxml.ivUpdataGg.setVisibility(View.VISIBLE);
            binding.tvNavForvip.setVisibility(View.GONE);
        }else{
            binding.addxml.ivUpdataGg.setVisibility(View.GONE);
            binding.tvNavForvip.setVisibility(View.VISIBLE);
        }
        //为侧滑NavigationView添加一个头部布局
        view_nav_head = binding.navView.inflateHeaderView(R.layout.item_nav_head);
        tv_name= (TextView) view_nav_head.findViewById(R.id.tv_nav_name);
        tv_club = (TextView) view_nav_head.findViewById(R.id.tv_nav_club);
        tv_name.setText(App.getName());
        tv_club.setText(App.getClub());

    }


    @Override
    protected void initEvent() {
        //按钮点击事件
        startActivityWith(HDongActivity.class, binding.addxml.ivHd);

//        binding.addxml.ivHd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sendBroadcast(new Intent("gonggao"));
//            }
//        });


        startActivityWith(ClubManActivity.class, binding.addxml.ivAllman);
        startActivityWith(NmChatActivity.class, binding.addxml.ivNm);
        startActivityWith(MsgBoardActivity.class, binding.addxml.cvBoard);
        startActivityWith(ForVipActivity.class,binding.tvNavForvip);
        startActivityWith(UpdataGGActivity.class,binding.addxml.ivUpdataGg);


        binding.addxml.topbar.ivTopArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //左上角打开侧栏菜单
                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    binding.drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        binding.addxml.banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                showToast(position+"");
            }
        });

        //执行公告循环检测
        updataHandler.post(updataRun);
    }

    Runnable updataRun = new Runnable() {
        @Override
        public void run() {
            //启动公告检测的Service，由Service来区分公告的更新，并通过广播传递更新数据
            MyIntentService.startActionFoo(HomeActivity.this,"asdf","asdf");
            getMsgNum();
            updataHandler.postDelayed(updataRun, 2000);
        }
    };

    @Override
    protected void getData() {
            getAd();
    }

    //用于检测留言板数量（将在进入留言板后本地保存数量）
    private void getMsgNum(){
        App.getService().getMsgList(App.getName(), new MySubscribe<MsgBoardData>() {
            @Override
            public void onNext(MsgBoardData msgBoardData) {
                super.onNext(msgBoardData);
                Log.e("获取到的数量",msgBoardData.getNum()+"");
                Log.e("保存的数量",App.getMsgNum()+"");
                if (App.getMsgNum()<msgBoardData.getNum()){
                    binding.addxml.tvBoardNewnum.setVisibility(View.VISIBLE);
                    binding.addxml.tvBoardNewnum.setText(msgBoardData.getNum()-App.getMsgNum()+"");
                }else{
                    binding.addxml.tvBoardNewnum.setVisibility(View.GONE);
                }

            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }

    private void getGongGao() {
        App.getService().getGongGao(App.getClub(), App.getSchool(), new MySubscribe<GongGaoData>() {
            @Override
            public void onNext(GongGaoData gongGaoData) {
                super.onNext(gongGaoData);
                String gg = gongGaoData.getData().getGonggao();
                if (!TextUtils.isEmpty(gg) && !gg.equals(App.getGG())) {
                    binding.addxml.tvGonggao.setText(gg);
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.BroadcastReceiverTest");
                    intent.putExtra("content", gg);
                    sendBroadcast(intent);
//                    sendBroadcast(new Intent("gonggao"));
                    MyIntentService.startActionFoo(HomeActivity.this,"asdf","asdf");
                    App.setGG(gg);
                } else {
                    binding.addxml.tvGonggao.setText(gongGaoData.getData().getGonggao());
                }
            }
        });
    }

    private void getAd(){
        App.getService().getAd(App.getClub(), App.getSchool(), new MySubscribe<AdData>() {
            @Override
            public void onNext(AdData adData) {
                super.onNext(adData);
                binding.addxml.ivNoAd.setVisibility(View.GONE);
                binding.addxml.banner.setImageLoader(new GlideImageLoader())
                        .setImages(adData.getImgUrls())
                        .start();
            }

            @Override
            public void onError(Throwable e) {
                binding.addxml.ivNoAd.setVisibility(View.VISIBLE);
                getAd();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        updataHandler.removeCallbacks(updataRun);
        unregisterReceiver(this.changeGongGao);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            startActivity(ChangeInfoActivity.class);
        } else if (id == R.id.nav_gallery) {
            startActivity(AddHdActivity.class);
        } else if (id == R.id.nav_slideshow) {
            startActivity(HDongDeleteActivity.class);
        } else if (id == R.id.nav_manage) {
            startActivity(FriendActivity.class);
        } else if (id == R.id.nav_share) {
            KeyValueStorage keyValueStorage = new KeyValueStorage(this);
            keyValueStorage.putString(Config.IS_AUTO_LOGIN,"1");
            startActivity(LoginActivity.class);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 设置全屏滑动
     *
     * @param activity
     * @param drawerLayout
     * @param displayWidthPercentage
     */
    private void setDrawerLeftEdgeSize(Activity activity, DrawerLayout drawerLayout, float displayWidthPercentage) {
        if (activity == null || drawerLayout == null) return;
        try {
            // 找到 ViewDragHelper 并设置 Accessible 为true
            Field leftDraggerField =
                    drawerLayout.getClass().getDeclaredField("mLeftDragger");//Right
            leftDraggerField.setAccessible(true);
            ViewDragHelper leftDragger = (ViewDragHelper) leftDraggerField.get(drawerLayout);

            // 找到 edgeSizeField 并设置 Accessible 为true
            Field edgeSizeField = leftDragger.getClass().getDeclaredField("mEdgeSize");
            edgeSizeField.setAccessible(true);
            int edgeSize = edgeSizeField.getInt(leftDragger);

            // 设置新的边缘大小
            Point displaySize = new Point();
            activity.getWindowManager().getDefaultDisplay().getSize(displaySize);
            edgeSizeField.setInt(leftDragger, Math.max(edgeSize, (int) (displaySize.x *
                    displayWidthPercentage)));
        } catch (NoSuchFieldException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }
    }


    //用于接收公告的更新数据，传递给Handler并更新UI
    private BroadcastReceiver changeGongGao = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String gg = intent.getExtras().getString("gg");
            Message message = Message.obtain();
            message.obj = gg;
            updataHandler.sendMessage(message);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        //注册本类的广播接收器，
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("gonggao");
        registerReceiver(this.changeGongGao,intentFilter);
    }

    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                if (System.currentTimeMillis() - firstTime > 2000) {
                    showBottomToast(binding.getRoot(), "再按一次退出");
                    firstTime = System.currentTimeMillis();
                } else {
                    finish();
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
