package lins.com.stgl.rxservice;


import lins.com.stgl.bean.AdData;
import lins.com.stgl.bean.BaseData;
import lins.com.stgl.bean.ClubManData;
import lins.com.stgl.bean.FriendData;
import lins.com.stgl.bean.GongGaoData;
import lins.com.stgl.bean.HdData;
import lins.com.stgl.bean.HdDeleteListData;
import lins.com.stgl.bean.MsgBoardData;
import lins.com.stgl.bean.NmChatData;
import lins.com.stgl.bean.PLData;
import lins.com.stgl.bean.UserData;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by LINS on 2017/2/22.
 */

interface ServiceRequest {

    //http://route.showapi.com/341-3?showapi_appid=32450&showapi_sign=2b8b8a8c02cd4543827b6c3e4ea36976&page=1&maxResult=10

    @FormUrlEncoded
    @POST("as_show_clubman")
    Observable<ClubManData> getClubMan(
            @Field("jclue") String club,
            @Field("jschool") String school
    );
    @GET("as_show_nmchat")
    Observable<NmChatData> getNmChatList(
    );
    @FormUrlEncoded
    @POST("as_show_allhd")
    Observable<HdData> getHdList(
            @Field("school") String school
    );

    @FormUrlEncoded
    @POST("as_show_ggby")
    Observable<GongGaoData> getGongGao(
            @Field("jclub") String club,
            @Field("jschool") String school
    );

    @FormUrlEncoded
    @POST("as_login")
    Observable<UserData> login(
            @Field("name") String name,
            @Field("pwd") String pwd
    );

    @FormUrlEncoded
    @POST("as_forget")
    Observable<BaseData> getForget(
            @Field("jname") String name,
            @Field("jmishi") String mishi,
            @Field("jpwd") String pwd
    );

    @FormUrlEncoded
    @POST("as_register")
    Observable<BaseData> getRegister(
            @Field("name") String name,
            @Field("pwd") String pwd,
            @Field("sex") String sex,
            @Field("age") String age,
            @Field("club") String club,
            @Field("school") String school
    );
    @FormUrlEncoded
    @POST("as_updata_gonggao")
    Observable<BaseData> getUpdataGG(
            @Field("jgname") String name,
            @Field("jgclub") String club,
            @Field("jgonggao") String gg,
            @Field("jschool") String school
    );

    @FormUrlEncoded
    @POST("as_updata_user")
    Observable<BaseData> getUpdataInfo(
            @Field("name") String name,
            @Field("pwd") String pwd,
            @Field("sex") String sex,
            @Field("age") String age,
            @Field("club") String club,
            @Field("mishi") String mishi
    );

    @FormUrlEncoded
    @POST("as_show_hd_delete_list")
    Observable<HdDeleteListData> getHdDeleteList(
            @Field("name") String name,
            @Field("club") String club,
            @Field("school") String gg
    );

    @FormUrlEncoded
    @POST("as_hd_del")
    Observable<BaseData> getDelHd(
            @Field("jhid") String name
    );
    @FormUrlEncoded
    @POST("as_show_friend")
    Observable<FriendData> getFriend(
            @Field("jrid") String id
    );


    @FormUrlEncoded
    @POST("as_for_vip")
    Observable<BaseData> getForVip(
            @Field("juserid") String id,
            @Field("jusername") String name,
            @Field("jforvip") String res
    );

    @FormUrlEncoded
    @POST("as_add_nmchat")
    Observable<BaseData> addNmChat(
            @Field("jname") String name,
            @Field("jsex") String sex,
            @Field("jclub") String club,
            @Field("jschool") String school,
            @Field("jsaytext") String txt
    );

    @FormUrlEncoded
    @POST("as_add_hd")
    Observable<BaseData> addHd(
            @Field("jrid") String rid,
            @Field("jname") String name,
            @Field("jtitle") String title,
            @Field("jctime") String ctime,
            @Field("jtime") String time,
            @Field("jword") String word,
            @Field("jschool") String school,
            @Field("jclub") String club
    );

    @FormUrlEncoded
    @POST("as_show_pl")
    Observable<PLData> getPLList(
            @Field("jhid") String hid
    );

    @FormUrlEncoded
    @POST("as_add_pl")
    Observable<BaseData> addPlun(
            @Field("jhid") String rid,
            @Field("juserid") String name,
            @Field("jusername") String title,
            @Field("jpword") String word
    );

    @FormUrlEncoded
    @POST("as_reply")
    Observable<BaseData> reply(
            @Field("jisname") String froname,
            @Field("jistext") String text,
            @Field("jtoname") String toname
    );


    @FormUrlEncoded
    @POST("as_show_msgboard")
    Observable<MsgBoardData> getMsgList(
            @Field("jisname") String froname
    );

    @FormUrlEncoded
    @POST("as_ad")
    Observable<AdData> getAd(
            @Field("club") String club,
            @Field("school") String school
    );
    @FormUrlEncoded
    @POST("as_add_friend")
    Observable<BaseData> addFriend(
            @Field("jrid") String id,
            @Field("jfriendid") String fid,
            @Field("jfriendname") String fname
    );
}
