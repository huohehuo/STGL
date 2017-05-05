package lins.com.stgl.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import lins.com.stgl.App;
import lins.com.stgl.R;
import lins.com.stgl.bean.BaseData;
import lins.com.stgl.bean.ClubManData;
import lins.com.stgl.bean.FriendData;
import lins.com.stgl.databinding.ActivityAllPepleBinding;
import lins.com.stgl.easyadapter.ClubManAdapter;
import lins.com.stgl.rxservice.MySubscribe;
import lins.com.stgl.ui.base.BaseActivity;

public class ClubManActivity extends BaseActivity {
    ActivityAllPepleBinding binding;
    private ClubManAdapter adapter;
    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_all_peple);
        binding.toolbar.tvTopTitle.setText("社团成员");
        binding.rvAllPeople.setAdapter(adapter=new ClubManAdapter(ClubManActivity.this));
        binding.rvAllPeople.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        binding.rvAllPeople.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.rvAllPeople.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.clear();
                        getData();
                    }
                },1000);
                binding.rvAllPeople.setRefreshing(false);
            }
        });

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                final int ppid = adapter.getAllData().get(position).getRid();
                if (App.getName().equals(adapter.getAllData().get(position).getName())){
                    showToast("不能添加自己为好友");
                    return;
                }
//
//                App.getService().getFriend(App.getUserId(), new MySubscribe<FriendData>() {
//                        @Override
//                        public void onNext(FriendData friendData) {
//                            super.onNext(friendData);
//                            if (friendData.getData().get(position).getFriendid()==ppid){
//                                showToast("对方已经是您的好友");
//                                return;
//                            }else{
//
//                                Log.e("ccc","KDDD");
////                            LayoutInflater inf = LayoutInflater.from(ClubManActivity.this);
////                            View mydl = inf.inflate(R.layout.dialog_add_friend, null);
////                            TextView ys = (TextView) mydl.findViewById(R.id.friend_yes);
////                            TextView no = (TextView) mydl.findViewById(R.id.friend_no);
////
////                            final AlertDialog.Builder dlg=new AlertDialog.Builder(ClubManActivity.this);
////                            dlg.setView(mydl);
////                            dlg.create().show();
////                            ys.setOnClickListener(new View.OnClickListener() {
////                                @Override
////                                public void onClick(View arg0) {
////                                    showDialog("处理中...");
////
////                                }
////                            });
//                            }
//                        }
//                        @Override
//                        public void onError(Throwable e) {
//                            showToast("出现错误");
//                            return;
//                        }
//                    });
                App.getService().addFriend(App.getUserId(),
                        adapter.getAllData().get(position).getRid()+"",
                        adapter.getAllData().get(position).getName(),
                        new MySubscribe<BaseData>() {
                            @Override
                            public void onNext(BaseData baseData) {
                                super.onNext(baseData);
                                if (baseData.getError().equals("0")){
                                    showToast("添加成功");
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                showToast("添加失败");
                            }
                        });
                }



        });

    }
    @Override
    protected void initEvent() {

    }
    @Override
    protected void getData(){
        showDialog("加载中..");
        App.getService().getClubMan(App.getClub(), App.getSchool(), new MySubscribe<ClubManData>() {
            @Override
            public void onNext(ClubManData clubManData) {
                super.onNext(clubManData);
                adapter.addAll(clubManData.getData());
                closeDialog();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                closeDialog();
            }
        });
    }

}
