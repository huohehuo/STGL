package lins.com.stgl.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import lins.com.stgl.App;
import lins.com.stgl.R;
import lins.com.stgl.bean.MsgBoardData;
import lins.com.stgl.databinding.ActivityBbsBinding;
import lins.com.stgl.easyadapter.MsgBoardAdapter;
import lins.com.stgl.rxservice.MySubscribe;
import lins.com.stgl.ui.base.BaseActivity;

public class MsgBoardActivity extends BaseActivity {

    ActivityBbsBinding binding;
    MsgBoardAdapter adapter;
    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bbs);
        binding.toolbar.tvTopTitle.setText("留言板");
        binding.rvMsg.setAdapter(adapter = new MsgBoardAdapter(this));
        binding.rvMsg.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void initEvent() {
        binding.rvMsg.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.rvMsg.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.clear();
                        getData();
                    }
                },1000);
                binding.rvMsg.setRefreshing(false);
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String name = adapter.getAllData().get(position).getJfromname();
                Intent dd = new Intent();
                dd.setClass(MsgBoardActivity.this, ReplyActivity.class);
                dd.putExtra("toFriend", name);
                startActivity(dd);
            }
        });
    }

    @Override
    protected void getData() {
        showDialog("加载中");
        App.getService().getMsgList(App.getName(), new MySubscribe<MsgBoardData>() {
            @Override
            public void onNext(MsgBoardData msgBoardData) {
                super.onNext(msgBoardData);
                closeDialog();
                App.setMsgNum(msgBoardData.getNum()+"");//更新留言板数量
                adapter.addAll(msgBoardData.getData());
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                closeDialog();
            }
        });
    }
}
