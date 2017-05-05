package lins.com.stgl.ui;

import android.app.AlertDialog;
import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import lins.com.stgl.App;
import lins.com.stgl.R;
import lins.com.stgl.bean.BaseData;
import lins.com.stgl.bean.HdDeleteListData;
import lins.com.stgl.databinding.ActivityHdDelBinding;
import lins.com.stgl.easyadapter.HdDeleteAdapter;
import lins.com.stgl.rxservice.MySubscribe;
import lins.com.stgl.ui.base.BaseActivity;

public class HDongDeleteActivity extends BaseActivity {
    ActivityHdDelBinding binding;
    HdDeleteAdapter adapter;
    View view;
    @Override
    protected void initView() {
        binding= DataBindingUtil.setContentView(this, R.layout.activity_hd_del);
        binding.toolbar.ivTopArrow.setImageResource(R.drawable.btn_return_selector);
        binding.toolbar.tvTopTitle.setText("活动管理");
        binding.rvHdDel.setAdapter(adapter = new HdDeleteAdapter(this));
        binding.rvHdDel.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void initEvent() {
        closeActivity(binding.toolbar.ivTopArrow);
        binding.rvHdDel.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.rvHdDel.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.clear();
                        getData();
                    }
                },1000);
                binding.rvHdDel.setRefreshing(false);
            }
        });
            adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    final int pos = position;
                    LayoutInflater inf = LayoutInflater.from(HDongDeleteActivity.this);
                    View mydl = inf.inflate(R.layout.hd_dialog, null);
                    TextView ys = (TextView) mydl.findViewById(R.id.pop_yes);
                    TextView no = (TextView) mydl.findViewById(R.id.pop_no);

                    final AlertDialog.Builder bd = new AlertDialog.Builder(
                            HDongDeleteActivity.this);
                    bd.setTitle(null);
                    bd.setView(mydl);
                    bd.create().show();
                    ys.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View arg0) {
                            App.getService().getDelHd(
                                    adapter.getAllData().get(pos).getHid()+"",
                                    new MySubscribe<BaseData>() {
                                        @Override
                                        public void onNext(BaseData baseData) {
                                            super.onNext(baseData);
                                            adapter.clear();
                                            showToast("删除成功");
                                            getData();
                                        }
                                    }
                            );
                            showToast("操作成功");
                        }
                    });
                    no.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View arg0) {
                            showToast("无操作");
                        }
                    });
                }
            });
    }

    @Override
    protected void getData() {
        showDialog("正在加载...");
        App.getService().getHdDeleteList(App.getName(), App.getClub(), App.getSchool(), new MySubscribe<HdDeleteListData>() {
            @Override
            public void onNext(HdDeleteListData hdDeleteListData) {
                super.onNext(hdDeleteListData);
                adapter.addAll(hdDeleteListData.getData());
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
