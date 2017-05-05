package lins.com.stgl.easyadapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import lins.com.stgl.R;
import lins.com.stgl.bean.FriendData;

/**
 * Created by LINS on 2017/4/13.
 */

public class FriendAdapter extends RecyclerArrayAdapter<FriendData.DataBean>{
    public FriendAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new FriendHolder(parent);
    }
    class FriendHolder extends BaseViewHolder<FriendData.DataBean>{
        private TextView name;
        public FriendHolder(ViewGroup parent) {
            super(parent, R.layout.item_myfriend);
            name = $(R.id.tv_friend_name);
        }

        @Override
        public void setData(FriendData.DataBean data) {
            super.setData(data);
            name.setText(data.getFriendname());
        }
    }
}
