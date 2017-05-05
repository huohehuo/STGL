package lins.com.stgl.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import lins.com.stgl.App;
import lins.com.stgl.R;
import lins.com.stgl.bean.HdData;
import lins.com.stgl.databinding.ActivityHdongBinding;
import lins.com.stgl.easyadapter.HdAdapter;
import lins.com.stgl.rxservice.MySubscribe;
import lins.com.stgl.ui.base.BaseActivity;

public class HDongActivity extends BaseActivity {
    ActivityHdongBinding binding;
    HdAdapter adapter;
    SharedPreferences pf;
    String userid, username, school;
    int hid, rid;
    String hname, htitle, htime, hword;
    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hdong);
        binding.toolbar.tvTopTitle.setText("活动大厅");
        binding.rvHd.setAdapter(adapter = new HdAdapter(this));
        binding.rvHd.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void initEvent() {
        binding.rvHd.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.rvHd.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.clear();
                        getData();
                    }
                },1000);
                binding.rvHd.setRefreshing(false);
            }
        });

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                SharedPreferences sp = getSharedPreferences("usertext", Activity.MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.putString("hid", Integer.toString(adapter.getAllData().get(position).getHid()));
                ed.putString("hname", adapter.getAllData().get(position).getHname());
                ed.putString("htitle", adapter.getAllData().get(position).getHtitle());
                ed.putString("htime", adapter.getAllData().get(position).getHtime());
                ed.putString("hword", adapter.getAllData().get(position).getHword());

                ed.commit();

                startActivity(HdShowActivity.class);

            }
        });
    }

    @Override
    protected void getData() {
        showDialog("加载中...");
        App.getService().getHdList(App.getSchool(), new MySubscribe<HdData>() {
            @Override
            public void onNext(HdData hdData) {
                super.onNext(hdData);
                closeDialog();
                adapter.addAll(hdData.getData());

            }

            @Override
            public void onError(Throwable e) {
                showBottomToast(binding.getRoot(),"获取信息失败");
                closeDialog();
            }
        });
    }
}
