package lins.com.stgl.easyadapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import lins.com.stgl.R;
import lins.com.stgl.bean.HdData;

import static com.orhanobut.logger.Logger.d;

/**
 * Created by LINS on 2017/4/11.
 */

public class HdAdapter extends RecyclerArrayAdapter<HdData.DataBean>{
    public HdAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new HdHolder(parent);
    }
    class HdHolder extends BaseViewHolder<HdData.DataBean>{
        private TextView title;
        private TextView word;
        private TextView club;
        private TextView time;
        public HdHolder(ViewGroup parent) {
            super(parent, R.layout.item_hd);
            title=$(R.id.txt_htitle);
            word=$(R.id.txt_hword);
            club = $(R.id.hd_clue);
            time = $(R.id.hd_time);
        }

        @Override
        public void setData(HdData.DataBean data) {
            super.setData(data);
            title.setText(data.getHtitle());
            word.setText(data.getHword());
            club.setText(data.getOfclue());
            time.setText(data.getHtime());
        }
    }
}
