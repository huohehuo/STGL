package lins.com.stgl.bean;

import java.util.List;

/**
 * Created by LINS on 2017/4/11.
 */

public class HdData extends BaseData{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * hid : 10
         * rid : 2
         * hname : vip1
         * htitle : 户外踏青
         * htime : 2016/4/01/3:8
         * hword : 龙潭公园走起
         * ofschool : 广西科技大学鹿山学院
         * ofclue : 轮滑社
         */

        private int hid;
        private int rid;
        private String hname;
        private String htitle;
        private String ctime;
        private String htime;
        private String hword;
        private String ofschool;
        private String ofclue;
        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
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

        public String getHname() {
            return hname;
        }

        public void setHname(String hname) {
            this.hname = hname;
        }

        public String getHtitle() {
            return htitle;
        }

        public void setHtitle(String htitle) {
            this.htitle = htitle;
        }

        public String getHtime() {
            return htime;
        }

        public void setHtime(String htime) {
            this.htime = htime;
        }

        public String getHword() {
            return hword;
        }

        public void setHword(String hword) {
            this.hword = hword;
        }

        public String getOfschool() {
            return ofschool;
        }

        public void setOfschool(String ofschool) {
            this.ofschool = ofschool;
        }

        public String getOfclue() {
            return ofclue;
        }

        public void setOfclue(String ofclue) {
            this.ofclue = ofclue;
        }
    }
}
