package lins.com.stgl.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import lins.com.stgl.HomeActivity;
import lins.com.stgl.R;

//广播：用于发送通知到任务栏，场景：社团公告
public class GgBroadcastReceiver extends BroadcastReceiver {

    private Context context;
    // Notification标示ID
    private static final int ID = 1;
    NotificationManager mNotificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        this.context = context;
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        showNotiication(intent);//发送通知
    }
    //发送状态栏通知
    private void showNotiication(Intent intent) {
        //当点击状态栏通知时跳到主界面
        long[] vibrate = {0, 100, 200, 200};//震动模式
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, HomeActivity.class), 0);//设置点击时跳转的页面
        NotificationCompat.Builder notif = new NotificationCompat.Builder(context)
                .setAutoCancel(true)
                .setVibrate(vibrate)
                .setSmallIcon(R.drawable.icon_avatar_login)
                .setContentTitle("公告提醒：")
                .setContentText(intent.getExtras().getString("content"))
                .setContentIntent(pendingIntent);
        mNotificationManager.notify(R.layout.activity_main, notif.build());

    }

}
