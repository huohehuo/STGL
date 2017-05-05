package lins.com.stgl.bean;

import java.util.List;

/**
 * Created by LINS on 2017/4/14.
 */

public class PLData extends BaseData{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pid : 39
         * hid : 11
         * rid : 1
         * pname : jk
         * pword : 可是下雨吗
         */

        private int pid;
        private int hid;
        private int rid;
        private String pname;
        private String pword;

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getHid() {
            return hid;
        }

        public void setHid(int hid) {
            this.hid = hid;
        }

        public int getRid() {
            return rid;
        }

        public void setRid(int rid) {
            this.rid = rid;
        }

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public String getPword() {
            return pword;
        }

        public void setPword(String pword) {
            this.pword = pword;
        }
    }
}
