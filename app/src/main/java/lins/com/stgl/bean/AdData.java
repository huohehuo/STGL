package lins.com.stgl.bean;

import java.util.ArrayList;
import java.util.List;

import lins.com.stgl.utils.Config;

/**
 * Created by LINS on 2017/4/15.
 */

public class AdData extends BaseData{
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<String> getImgUrls() {
        List<String> images = new ArrayList<>();
        for (DataBean dataBean :
                data) {
            images.add(Config.server_http+dataBean.getAdurl());
        }
        return images;
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

        private int adid;
        private String name;
        private String adword;
        private String adlink;
        private String adurl;
        private String adclub;
        private String adschool;

        public int getAdid() {
            return adid;
        }

        public void setAdid(int adid) {
            this.adid = adid;
        }

        public String getAdword() {
            return adword;
        }

        public void setAdword(String adword) {
            this.adword = adword;
        }

        public String getAdlink() {
            return adlink;
        }

        public void setAdlink(String adlink) {
            this.adlink = adlink;
        }

        public String getAdclub() {
            return adclub;
        }

        public void setAdclub(String adclub) {
            this.adclub = adclub;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAdurl() {
            return adurl;
        }

        public void setAdurl(String adurl) {
            this.adurl = adurl;
        }

        public String getAdschool() {
            return adschool;
        }

        public void setAdschool(String adschool) {
            this.adschool = adschool;
        }
    }

    }
