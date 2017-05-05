package lins.com.stgl.rxservice;

import android.util.Log;
import android.widget.Toast;

import java.net.ConnectException;

import lins.com.stgl.App;
import lins.com.stgl.bean.BaseData;
import lins.com.stgl.bean.ClubManData;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by LINS on 2017/2/22.
 */

//观察者模式
public abstract class MySubscribe<T extends BaseData> extends Subscriber<T> {
    @Override
    public void onNext(T t) {
        if (!"0".equals(t.getError())){
            Toast.makeText(App.getContext(), t.getMsg(), Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    public void onCompleted() {

    }
    @Override
    public void onError(Throwable e) {
        if (e instanceof ConnectException|e instanceof HttpException){
            Log.e("MySubscribe Error"+"++++",e.toString());
        }else{
            Toast.makeText(App.getContext(), "网络异常，请检查网络", Toast.LENGTH_SHORT).show();
        }
    }
}
