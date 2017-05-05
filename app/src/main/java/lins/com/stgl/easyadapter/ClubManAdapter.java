package lins.com.stgl.easyadapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import lins.com.stgl.R;
import lins.com.stgl.bean.ClubManData;

/**
 * Created by LINS on 2017/4/10.
 */

public class ClubManAdapter extends RecyclerArrayAdapter<ClubManData.DataBean>{
    public ClubManAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ClubManHolder(parent);
    }
    class ClubManHolder extends BaseViewHolder<ClubManData.DataBean>{
        private TextView name;
        private TextView sex;
        public ClubManHolder(ViewGroup parent) {
            super(parent, R.layout.item_allmen);
            name = $(R.id.txt_allname);
            sex = $(R.id.txt_allsex);
        }

        @Override
        public void setData(ClubManData.DataBean data) {
            super.setData(data);
            name.setText(data.getName());
            sex.setText(data.getSex());
        }
    }
}
