package lins.com.stgl.easyadapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import lins.com.stgl.R;
import lins.com.stgl.bean.NmChatData;

/**
 * Created by LINS on 2017/4/11.
 */

public class NmChatAdapter extends RecyclerArrayAdapter<NmChatData.DataBean>{
    public NmChatAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new NmChatHolder(parent);
    }
    class NmChatHolder extends BaseViewHolder<NmChatData.DataBean>{

        private TextView say;
        public NmChatHolder(ViewGroup parent) {
            super(parent, R.layout.item_nmchat);
            say = $(R.id.txt_say);
        }

        @Override
        public void setData(NmChatData.DataBean data) {
            super.setData(data);
            say.setText(data.getSaytext());
        }
    }
}
