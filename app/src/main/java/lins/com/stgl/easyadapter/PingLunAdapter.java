package lins.com.stgl.easyadapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import lins.com.stgl.R;
import lins.com.stgl.bean.PLData;

/**
 * Created by LINS on 2017/4/14.
 */

public class PingLunAdapter extends RecyclerArrayAdapter<PLData.DataBean>{
    public PingLunAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new PLHolder(parent);
    }
    class PLHolder extends BaseViewHolder<PLData.DataBean>{
        private TextView text_name;
        private TextView text_plun;
        public PLHolder(ViewGroup parent) {
            super(parent, R.layout.item_pl);
            text_name = $(R.id.txt_pname);
            text_plun = $(R.id.txt_pword);
        }

        @Override
        public void setData(PLData.DataBean data) {
            super.setData(data);
            text_name.setText(data.getPname());
            text_plun.setText(data.getPword());
        }
    }
}
