package lins.com.stgl.easyadapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import lins.com.stgl.R;
import lins.com.stgl.bean.HdDeleteListData;

/**
 * Created by LINS on 2017/4/12.
 */

public class HdDeleteAdapter extends RecyclerArrayAdapter<HdDeleteListData.DataBean>{
    public HdDeleteAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new HdDeleteHolder(parent);
    }
    class HdDeleteHolder extends BaseViewHolder<HdDeleteListData.DataBean>{
        private TextView title;
        private TextView word;
        public HdDeleteHolder(ViewGroup parent) {
            super(parent, R.layout.item_hd_del);
            title = $(R.id.tv_title);
            word = $(R.id.tv_word);
        }

        @Override
        public void setData(HdDeleteListData.DataBean data) {
            super.setData(data);
            title.setText(data.getHtitle());
            word.setText(data.getHword());
        }
    }
}
