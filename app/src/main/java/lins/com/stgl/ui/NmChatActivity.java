package lins.com.stgl.ui;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import lins.com.stgl.App;
import lins.com.stgl.R;
import lins.com.stgl.bean.NmChatData;
import lins.com.stgl.databinding.ActivityNmchatBinding;
import lins.com.stgl.easyadapter.NmChatAdapter;
import lins.com.stgl.rxservice.MySubscribe;
import lins.com.stgl.ui.base.BaseActivity;

/**
 * Created by LINS on 2017/4/11.
 */

public class NmChatActivity extends BaseActivity{
    ActivityNmchatBinding binding;
    NmChatAdapter  adapter;
    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_nmchat);
        binding.toolbar.tvTopTitle.setText("匿名吐槽区");
        binding.toolbar.tvTopRight.setText("我要吐槽");
        binding.rvNm.setAdapter(adapter = new NmChatAdapter(this));
        binding.rvNm.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initEvent() {
        startActivityWith(AddNmChatActivity.class,binding.toolbar.tvTopRight);

        binding.rvNm.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.rvNm.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.clear();
                        getData();
                        binding.rvNm.setRefreshing(false);
                    }
                },1000);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void getData() {
        showDialog("加载中...");
        App.getService().getNmChatList(new MySubscribe<NmChatData>() {
            @Override
            public void onNext(NmChatData nmChatData) {
                super.onNext(nmChatData);
                closeDialog();
                adapter.addAll(nmChatData.getData());
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                closeDialog();
            }
        });
    }

}
