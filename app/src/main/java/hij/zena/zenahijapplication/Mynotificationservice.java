package hij.zena.zenahijapplication;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class Mynotificationservice extends NotificationListenerService {
    public Mynotificationservice() {
    }

//    @Override
//    public IBinder onBind(Intent intent) {
//        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
//    }

    private String TAG = this.getClass().getSimpleName();
    Context context;
    static MyListener myListener;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    @Override
    public void onNotificationPosted (StatusBarNotification sbn) {
        Log. i ( TAG , "********** onNotificationPosted" ) ;
        Log. i ( TAG , "ID :" + sbn.getId() + " \t " + sbn.getNotification(). tickerText + " \t " + sbn.getPackageName()) ;
        //
        Bundle extras = sbn.getNotification().extras;
        String packageName = sbn.getPackageName();
        Object oTitle = extras.get(Notification.EXTRA_TITLE);
        Object oText = extras.get(Notification.EXTRA_TEXT);
        if (oTitle == null || oText == null)
            return;
        String title = oTitle.toString();
        String text = oText.toString();
        Log.d(TAG, "Notification - : " +
                " \npackageName: " + packageName +
                " \nTitle      : " + title +
                " \nText       : " + text);
//        switch (packageName) {
//            case pkgWX:
//                new WXClient(getApplicationContext()).onNotification(title, text);
//                break;
//            case pkgThis:
//                App.timeCheckNotificationListenerServiceIsWorking = new Date().getTime();
//                Log.i(TAG, "onNotificationPosted: time: " + App.timeCheckNotificationListenerServiceIsWorking);
//                break;
//        }
        //
        myListener .setValue( "Post: " +"Notification - : " +
                " \npackageName: " + packageName +
                " \nTitle      : " + title +
                " \nText       : " + text );//sbn.getPackageName()+'\n'+sbn.getNotification(). tickerText) ;
    }
    @Override
    public void onNotificationRemoved (StatusBarNotification sbn) {
        Log. i ( TAG , "********** onNotificationRemoved" ) ;
        Log. i ( TAG , "ID :" + sbn.getId() + " \t " + sbn.getNotification(). tickerText + " \t " + sbn.getPackageName()) ;
        myListener .setValue( "Remove: " + sbn.getPackageName()) ;
    }
    public void setListener (MyListener myListener) {
        Mynotificationservice. myListener = myListener ;
    }
}
