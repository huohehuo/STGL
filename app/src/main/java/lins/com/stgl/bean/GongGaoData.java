package lins.com.stgl.bean;

/**
 * Created by LINS on 2017/4/12.
 */

public class GongGaoData extends BaseData{

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * gid : 4
         * gname : vip1
         * gclue : 轮滑社
         * ofschool : 广西科技大学鹿山学院
         * gonggao : poiu
         */

        private int gid;
        private String gname;
        private String gclue;
        private String ofschool;
        private String gonggao;

        public int getGid() {
            return gid;
        }

        public void setGid(int gid) {
            this.gid = gid;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public String getGclue() {
            return gclue;
        }

        public void setGclue(String gclue) {
            this.gclue = gclue;
        }

        public String getOfschool() {
            return ofschool;
        }

        public void setOfschool(String ofschool) {
            this.ofschool = ofschool;
        }

        public String getGonggao() {
            return gonggao;
        }

        public void setGonggao(String gonggao) {
            this.gonggao = gonggao;
        }
    }
}
