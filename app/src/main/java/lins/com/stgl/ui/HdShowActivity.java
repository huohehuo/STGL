package lins.com.stgl.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import lins.com.stgl.App;
import lins.com.stgl.R;
import lins.com.stgl.bean.PLData;
import lins.com.stgl.databinding.HdShowBinding;
import lins.com.stgl.easyadapter.PingLunAdapter;
import lins.com.stgl.rxservice.MySubscribe;
import lins.com.stgl.ui.base.BaseActivity;

public class HdShowActivity extends BaseActivity {
    String tohid,torid,tohname,tohtitle,tohtime,tohword,username,userid;
    SharedPreferences pf;
    HdShowBinding binding;
    PingLunAdapter adapter;
    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this,R.layout.hd_show);
        binding.toolbar.tvTopTitle.setText("活动详情");
        pf = getSharedPreferences("usertext", Activity.MODE_PRIVATE);
        userid = pf.getString("rid", null);
        username = pf.getString("rname", null);

        tohid =pf.getString("hid", null);
        tohname = pf.getString("hname", null);
        tohtitle =pf.getString("htitle", null);
        tohtime=pf.getString("htime", null);
        tohword=pf.getString("hword", null);

        binding.txtName.setText("发布人: "+tohname);
        binding.txtTime.setText("活动日期: "+tohtime);
        binding.txtTitle.setText(tohtitle);
        binding.txtWord.setText(tohword);

        binding.rvPl.setAdapter(adapter = new PingLunAdapter(this));
        binding.rvPl.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void initEvent() {
        startActivityWith(AddPlunActivity.class,binding.btnPl);
        binding.rvPl.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                getPl();
            }
        });
    }

    @Override
    protected void getData() {
//        getPl();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPl();
    }

    private void getPl(){
        binding.pbPl.setVisibility(View.VISIBLE);
        App.getService().getPLList(tohid, new MySubscribe<PLData>() {
            @Override
            public void onNext(PLData plData) {
                super.onNext(plData);
                binding.pbPl.setVisibility(View.GONE);
                adapter.addAll(plData.getData());
            }
        });
    }
}
