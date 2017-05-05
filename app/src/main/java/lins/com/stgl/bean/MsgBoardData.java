package lins.com.stgl.bean;

import java.util.ArrayList;
import java.util.List;

import lins.com.stgl.App;
import lins.com.stgl.utils.Config;

/**
 * Created by LINS on 2017/4/14.
 */

public class MsgBoardData extends BaseData{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }
    public int getNum(){
        int num = 0;
        for (int i = 0;i<data.size();i++){
            num=i;
        }
        return num;
    }

    public static class DataBean {
        /**
         * jfcid : 9
         * jfromname : vip1
         * jfromtext : 你好啊
         * jfromtoname : jk
         */

        private int jfcid;
        private String jfromname;
        private String jfromtext;
        private String jfromtoname;

        public int getJfcid() {
            return jfcid;
        }

        public void setJfcid(int jfcid) {
            this.jfcid = jfcid;
        }

        public String getJfromname() {
            return jfromname;
        }

        public void setJfromname(String jfromname) {
            this.jfromname = jfromname;
        }

        public String getJfromtext() {
            return jfromtext;
        }

        public void setJfromtext(String jfromtext) {
            this.jfromtext = jfromtext;
        }

        public String getJfromtoname() {
            return jfromtoname;
        }

        public void setJfromtoname(String jfromtoname) {
            this.jfromtoname = jfromtoname;
        }
    }
}
