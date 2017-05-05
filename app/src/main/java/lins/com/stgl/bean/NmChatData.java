package lins.com.stgl.bean;

import java.util.List;

/**
 * Created by LINS on 2017/4/11.
 */

public class NmChatData extends BaseData{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * nid : 5
         * name : vip1
         * sex : 男
         * clue : 轮滑社
         * school : 广西科技大学鹿山学院
         * saytext : 昨天天气真是太好了，只是有点晒
         */

        private int nid;
        private String name;
        private String sex;
        private String clue;
        private String school;
        private String saytext;

        public int getNid() {
            return nid;
        }

        public void setNid(int nid) {
            this.nid = nid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getClue() {
            return clue;
        }

        public void setClue(String clue) {
            this.clue = clue;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getSaytext() {
            return saytext;
        }

        public void setSaytext(String saytext) {
            this.saytext = saytext;
        }
    }
}
