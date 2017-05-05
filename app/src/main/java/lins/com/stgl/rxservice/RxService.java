package lins.com.stgl.rxservice;


import lins.com.stgl.App;
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
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by LINS on 2017/2/22.
 */

public class RxService {
    private ServiceRequest request;
    public RxService(){
        request = App.getRetrofit().create(ServiceRequest.class);
    }

    //获取笑话
    public void login(String name,String pwd,MySubscribe<UserData> subscribe){
        Observable<UserData> observable =request.login(name,pwd);
        toSubscribe(observable,subscribe);
    }
    //获取笑话
    public void getForget(String name,String mishi,String pwd,MySubscribe<BaseData> subscribe){
        Observable<BaseData> observable =request.getForget(name,mishi,pwd);
        toSubscribe(observable,subscribe);
    }
    //获取笑话
    public void getRegister(String name,String pwd,String sex,String age,String club,String school,MySubscribe<BaseData> subscribe){
        Observable<BaseData> observable =request.getRegister(name,pwd,sex,age,club,school);
        toSubscribe(observable,subscribe);
    }
    //获取笑话
    public void getClubMan(String club,String school,MySubscribe<ClubManData> subscribe){
        Observable<ClubManData> observable =request.getClubMan(club,school);
        toSubscribe(observable,subscribe);
    }
    public void getNmChatList(MySubscribe<NmChatData> subscribe){
        Observable<NmChatData> observable =request.getNmChatList();
        toSubscribe(observable,subscribe);
    }
    public void getHdList(String school,MySubscribe<HdData> subscribe){
        Observable<HdData> observable =request.getHdList(school);
        toSubscribe(observable,subscribe);
    }
    public void getGongGao(String club,String school,MySubscribe<GongGaoData> subscribe){
        Observable<GongGaoData> observable =request.getGongGao(club,school);
        toSubscribe(observable,subscribe);
    }

    //获取笑话
    public void getUpdataGG(String name,String club,String gg,String school,MySubscribe<BaseData> subscribe){
        Observable<BaseData> observable =request.getUpdataGG(name,club,gg,school);
        toSubscribe(observable,subscribe);
    }
    //获取笑话
    public void getUpdataInfo(String name,String pwd,String sex,String age,String club,String mishi,MySubscribe<BaseData> subscribe){
        Observable<BaseData> observable =request.getUpdataInfo(name,pwd,sex,age,club,mishi);
        toSubscribe(observable,subscribe);
    }
    //获取笑话
    public void getHdDeleteList(String name,String club,String school,MySubscribe<HdDeleteListData> subscribe){
        Observable<HdDeleteListData> observable =request.getHdDeleteList(name,club,school);
        toSubscribe(observable,subscribe);
    }
    //获取笑话
    public void getDelHd(String hid,MySubscribe<BaseData> subscribe){
        Observable<BaseData> observable =request.getDelHd(hid);
        toSubscribe(observable,subscribe);
    }
    //获取笑话
    public void getFriend(String rid,MySubscribe<FriendData> subscribe){
        Observable<FriendData> observable =request.getFriend(rid);
        toSubscribe(observable,subscribe);
    }

    //获取笑话
    public void getForVip(String uid,String name,String res,MySubscribe<BaseData> subscribe){
        Observable<BaseData> observable =request.getForVip(uid,name,res);
        toSubscribe(observable,subscribe);
    }
    //获取笑话
    public void addNmChat(String name,String sex,String club,String school,String txt,MySubscribe<BaseData> subscribe){
        Observable<BaseData> observable =request.addNmChat(name,sex,club,school,txt);
        toSubscribe(observable,subscribe);
    }
    //获取笑话
    public void addHd(String rid,String name,String title,String ctime,String time,String word,String school,String club,MySubscribe<BaseData> subscribe){
        Observable<BaseData> observable =request.addHd(rid,name,title,ctime,time,word,school,club);
        toSubscribe(observable,subscribe);
    }
    //获取笑话
    public void getPLList(String hid,MySubscribe<PLData> subscribe){
        Observable<PLData> observable =request.getPLList(hid);
        toSubscribe(observable,subscribe);
    }
    //获取笑话
    public void addPlun(String hid,String userid,String username,String word,MySubscribe<BaseData> subscribe){
        Observable<BaseData> observable =request.addPlun(hid,userid,username,word);
        toSubscribe(observable,subscribe);
    }
    //获取笑话
    public void reply(String fromname,String text,String toname,MySubscribe<BaseData> subscribe){
        Observable<BaseData> observable =request.reply(fromname,text,toname);
        toSubscribe(observable,subscribe);
    }
    //获取笑话
    public void getMsgList(String name,MySubscribe<MsgBoardData> subscribe){
        Observable<MsgBoardData> observable =request.getMsgList(name);
        toSubscribe(observable,subscribe);
    }

    public void getAd(String club,String school,MySubscribe<AdData> subscribe){
        Observable<AdData> observable =request.getAd(club,school);
        toSubscribe(observable,subscribe);
    }
    public void addFriend(String id,String fid,String fname,MySubscribe<BaseData> subscribe){
        Observable<BaseData> observable =request.addFriend(id,fid,fname);
        toSubscribe(observable,subscribe);
    }




    /**
     * retrofit 线程管理
     */
    private static <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }



}
