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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import hij.zena.zenahijapplication.MyData.MyNote;

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
        saveNote(packageName,title,text);
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

    private void saveNote(String packageName, String title, String text) {
        MyNote t = new MyNote();
        t.setTitle(title);
        t.setText(text);
        t.setPkgname(packageName);
        ///current user uid
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        t.setOwner(uid);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference();
        String key = ref.child("myNotes").push().getKey();
        t.setKey(key);
        //add tasks to current user.
        //just this user can see/read this tasks
        ref.child("myNotes").child(uid).child(key).setValue(t).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {//response
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "successfuly adding", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "add not successful", Toast.LENGTH_SHORT).show();

                }
            }
        });
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
