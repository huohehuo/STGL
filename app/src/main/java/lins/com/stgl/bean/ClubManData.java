package lins.com.stgl.bean;

import java.util.List;

/**
 * Created by LINS on 2017/4/10.
 */

public class ClubManData extends BaseData{


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * rid : 1
         * name : jk
         * sex : 男
         * age : 41
         * clue : 轮滑社
         * school : null
         */

        private int rid;
        private String name;
        private String sex;
        private int age;
        private String clue;
        private Object school;

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

        public Object getSchool() {
            return school;
        }

        public void setSchool(Object school) {
            this.school = school;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "rid=" + rid +
                    ", name='" + name + '\'' +
                    ", sex='" + sex + '\'' +
                    ", age=" + age +
                    ", clue='" + clue + '\'' +
                    ", school=" + school +
                    '}';
        }
    }
}
