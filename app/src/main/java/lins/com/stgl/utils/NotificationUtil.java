package lins.com.stgl.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import lins.com.stgl.HomeActivity;
import lins.com.stgl.R;

/**
 * Created by LINS on 2017/4/15.
 */

public class NotificationUtil {
    private static NotificationManager mNotificationManager;
    public static void setNotif(Context context,String title,String text){
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //当点击状态栏通知时跳到主界面
        long[] vibrate = {0, 100, 200, 200};//震动模式
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, HomeActivity.class), 0);//设置点击时跳转的页面
        NotificationCompat.Builder notif = new NotificationCompat.Builder(context)
                .setAutoCancel(true)
                .setVibrate(vibrate)
                .setSmallIcon(R.drawable.icon_avatar_login)
                .setContentTitle(title)
                .setContentText(text);
//                .setContentIntent(pendingIntent);
        mNotificationManager.notify(R.layout.activity_main, notif.build());
    }

}
