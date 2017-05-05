package lins.com.stgl;

import android.app.Application;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import lins.com.stgl.rxservice.RxService;
import lins.com.stgl.utils.KeyValueStorage;
import lins.com.stgl.utils.Config;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LINS on 2017/4/8.
 */

public class App extends Application {
    private static OkHttpClient okHttpClient;
    private static HttpLoggingInterceptor interceptor;
    private static Gson gson;
    private static Retrofit retrofit;
    private static RxService mRxService;
    private static Context mContext;
    static WifiInfo wifiInfo;
    public static String ip;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        wifiInfo = wifiManager.getConnectionInfo();

        gson = new Gson();
        interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Config.server_http)
                .build();
        mRxService = new RxService();

    }
    public static RxService getService() {
        return mRxService;
    }

    public static void setService(RxService mRxService) {
        App.mRxService = mRxService;
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static void setRetrofit(Retrofit retrofit) {
        App.retrofit = retrofit;
    }

    public static Context getContext() {
        return mContext;
    }

    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public static Gson getGson() {
        return gson;
    }

    public static String getIp(){
        return new KeyValueStorage(getContext()).getString(Config.SERVER_IP,"404");
    }
    //获取wifi连接的IP地址
    public static void setIp(int add) {
        int ipAddress = wifiInfo.getIpAddress();
        //此处格式化ip地址，app端应与服务器连接在统一网络，并且ip要改成服务器电脑一样的ip地址才能有效连接
      ip=(ipAddress & 0xFF)+ "." +((ipAddress >> 8) & 0xFF)+ "." + ((ipAddress >> 16) & 0xFF)+ "."
                + (((ipAddress >> 24) & 0xFF)+add);//一般改这里，加或减
        new KeyValueStorage(getContext()).putString(Config.SERVER_IP,ip);
        Log.e("aa--",ip+"");
    }
    public static void gsetIp() {
        int ipAddress = wifiInfo.getIpAddress();
        //此处格式化ip地址，app端应与服务器连接在统一网络，并且ip要改成服务器电脑一样的ip地址才能有效连接
        String ip=(ipAddress & 0xFF)+ "." +((ipAddress >> 8) & 0xFF)+ "." + ((ipAddress >> 16) & 0xFF)+ "."
                + (((ipAddress >> 24) & 0xFF));//一般改这里，加或减
        Toast.makeText(mContext, "初始ip:"+ip, Toast.LENGTH_SHORT).show();
    }

    public static String getName(){
        return new KeyValueStorage(getContext()).getString(Config.USER_NAME,"");
    }
    public static String getPwd(){
        return new KeyValueStorage(getContext()).getString(Config.USER_PWD,"");
    }
    public static String getSex(){
        return new KeyValueStorage(getContext()).getString(Config.USER_SEX,"");
    }
    public static String getClub(){
        return new KeyValueStorage(getContext()).getString(Config.USER_CLUB,"");
    }
    public static String getSchool(){
        return new KeyValueStorage(getContext()).getString(Config.USER_SCHOOL,"");
    }
    public static String getUserId(){
        return new KeyValueStorage(getContext()).getString(Config.USER_ID,"");
    }
    public static String getGG(){
        return new KeyValueStorage(getContext()).getString(Config.CLUB_GG,"");
    }
    public static void setGG(String str){
        new KeyValueStorage(getContext()).putString(Config.CLUB_GG,str);
    }
    public static String getIsVip(){
        return new KeyValueStorage(getContext()).getString(Config.IS_VIP,"");
    }
    public static void setIsVip(){
        new KeyValueStorage(getContext()).putString(Config.IS_VIP,"");
    }
    public static void setMsgNum(String num){
        new KeyValueStorage(getContext()).putString(getName(),num);
    }
    public static int getMsgNum(){
        String str=new KeyValueStorage(getContext()).getString(getName(),"0");
        if (str!=null&&!"".equals(str)){
            return Integer.valueOf(str);
        }else{
            return 0;
        }

    }
}
