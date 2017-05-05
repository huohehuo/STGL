package lins.com.stgl.bean;

/**
 * Created by LINS on 2017/4/12.
 */

public class UserData extends BaseData{

    /**
     * data : {"rid":2,"name":"vip1","pwd":"1","sex":"男","age":23,"clue":"轮滑社","vip":"Y","mishi":"飞机飞上天","school":"广西科技大学鹿山学院"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * rid : 2
         * name : vip1
         * pwd : 1
         * sex : 男
         * age : 23
         * clue : 轮滑社
         * vip : Y
         * mishi : 飞机飞上天
         * school : 广西科技大学鹿山学院
         */

        private int rid;
        private String name;
        private String pwd;
        private String sex;
        private int age;
        private String clue;
        private String vip;
        private String mishi;
        private String school;

        public int getRid() {
            return rid;
        }

        public void setRid(int rid) {
            this.rid = rid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getClue() {
            return clue;
        }

        public void setClue(String clue) {
            this.clue = clue;
        }

        public String getVip() {
            return vip;
        }

        public void setVip(String vip) {
            this.vip = vip;
        }

        public String getMishi() {
            return mishi;
        }

        public void setMishi(String mishi) {
            this.mishi = mishi;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }
    }
}
