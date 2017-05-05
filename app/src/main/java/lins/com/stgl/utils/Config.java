package lins.com.stgl.utils;


import lins.com.stgl.App;

public class Config {
    public static final String SERVER_IP="server_ip";

    public static String USER_ID="user_id";
    public static String USER_NAME="user_name";
    public static String USER_PWD="user_pwd";
    public static String USER_SEX="user_sex";
    public static String USER_CLUB="user_club";
    public static String USER_SCHOOL="user_school";
    public static String IS_AUTO_LOGIN="is_auto_login";
    public static String CLUB_GG="club_gonggao";
    public static String IS_VIP="is_vip";
    public static String Msg_NUM_OF_USER="msg_num";

    public static String A = "http://";
//    public static String B = App.getIp();//获取ip地址
    public static String B = "192.168.1.103";//获取ip地址
    public static String C = ":8080/STGLserver/";

    public static String server_http = A+B+C;

    public static String url_add_huodong = A + B + C + "wr_hd";
    public static String url_add_nmchat = A + B + C + "wr_nmchat";
    public static String url_add_plun = A + B + C + "wr_pl";
    public static String url_bbs = A + B + C + "show_chat";
    public static String url_changedata = A + B + C + "changedata";
    public static String url_allmen = A + B + C + "show_allst";

    public static String url_findpwd = A + B + C + "find_pwd";
    public static String url_forvip = A + B + C + "forvip";
    public static String url_ggaoupdata = A + B + C + "updata_ggao";
    public static String url_hdong = A + B + C + "show_allhd";
    public static String url_hdong_del_show = A + B + C + "show_hd";
    public static String url_hdong_del = A + B + C + "hd_del";
    public static String url_hdong_show = A + B + C + "show_pl";

    public static String url_login = A + B + C + "login";
    public static String url_main_gg = A + B + C + "show_ggby";
    public static String url_mine = A + B + C + "chackdata";
    public static String url_myfriend = A + B + C + "show_friend";
    public static String url_nmchat = A + B + C + "show_nmchat";
    public static String url_register = A + B + C + "register";
    public static String url_reply = A + B + C + "reply";
    public static String url_friend_add = A + B + C + "friend_add";



}
