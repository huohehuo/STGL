package lins.com.stgl.easyadapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import lins.com.stgl.R;
import lins.com.stgl.bean.MsgBoardData;

/**
 * Created by LINS on 2017/4/14.
 */

public class MsgBoardAdapter extends RecyclerArrayAdapter<MsgBoardData.DataBean>{
    public MsgBoardAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MsgHolder(parent);
    }
    class MsgHolder extends BaseViewHolder<MsgBoardData.DataBean>{
        private TextView friendname;
        private TextView friendtxt;
        public MsgHolder(ViewGroup parent) {
            super(parent, R.layout.item_bbs);
            friendname = $(R.id.friend_name);
            friendtxt = $(R.id.friend_txt);
        }

        @Override
        public void setData(MsgBoardData.DataBean data) {
            super.setData(data);
            friendname.setText(data.getJfromname());
            friendtxt.setText(data.getJfromtext());
        }
    }
}
