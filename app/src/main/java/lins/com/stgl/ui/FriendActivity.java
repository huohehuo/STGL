package lins.com.stgl.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import lins.com.stgl.App;
import lins.com.stgl.R;
import lins.com.stgl.bean.FriendData;
import lins.com.stgl.databinding.ActivityMyfriendBinding;
import lins.com.stgl.easyadapter.FriendAdapter;
import lins.com.stgl.rxservice.MySubscribe;
import lins.com.stgl.ui.base.BaseActivity;

public class FriendActivity extends BaseActivity {
    ActivityMyfriendBinding binding;
    FriendAdapter adapter;

    @Override
    protected void initView() {
        binding= DataBindingUtil.setContentView(this,R.layout.activity_myfriend);
        binding.toolbar.tvTopTitle.setText("我的好友");
        binding.rvFriend.setAdapter(adapter = new FriendAdapter(this));
        binding.rvFriend.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

    }

    @Override
    protected void initEvent() {
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent dd = new Intent();
                dd.setClass(FriendActivity.this, ReplyActivity.class);
                dd.putExtra("toFriend", adapter.getAllData().get(position).getFriendname());
                startActivity(dd);
            }
        });
    }

    @Override
    protected void getData() {
        showDialog("正在加载...");
        App.getService().getFriend(App.getUserId(), new MySubscribe<FriendData>() {
            @Override
            public void onNext(FriendData friendData) {
                super.onNext(friendData);
                adapter.addAll(friendData.getData());
                closeDialog();
            }
            @Override
            public void onError(Throwable e) {
                showToast("出现错误");
                closeDialog();
            }
        });
    }
}
