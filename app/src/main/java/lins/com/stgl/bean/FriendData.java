package lins.com.stgl.bean;

import java.util.List;

/**
 * Created by LINS on 2017/4/13.
 */

public class FriendData extends BaseData{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * fid : 11
         * rid : 2
         * friendid : 1
         * friendname : jk
         */

        private int fid;
        private int rid;
        private int friendid;
        private String friendname;

        public int getFid() {
            return fid;
        }

        public void setFid(int fid) {
            this.fid = fid;
        }

        public int getRid() {
            return rid;
        }

        public void setRid(int rid) {
            this.rid = rid;
        }

        public int getFriendid() {
            return friendid;
        }

        public void setFriendid(int friendid) {
            this.friendid = friendid;
        }

        public String getFriendname() {
            return friendname;
        }

        public void setFriendname(String friendname) {
            this.friendname = friendname;
        }
    }
}
