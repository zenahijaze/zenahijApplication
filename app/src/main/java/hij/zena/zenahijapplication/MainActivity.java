package hij.zena.zenahijapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import hij.zena.zenahijapplication.MyData.MyNote;
import hij.zena.zenahijapplication.MyData.MyNoteAdapter;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.app.NotificationChannel ;
import android.app.NotificationManager ;
import android.content.Intent ;
import android.view.Menu ;
import android.view.MenuItem ;
import android.view.View ;
import android.widget.Button ;
import android.widget.ListView;
import android.widget.TextView ;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements MyListener {
    private ImageButton imgbtnO;
    private ImageButton imgbtnT;
    private ImageButton imgbtnF;
    private TextView txtView ;
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    //read: 1
    private ListView lstv;
    private MyNoteAdapter myNoteAdapter;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgbtnO = findViewById(R.id.imgbtnO);
        imgbtnT = findViewById(R.id.imgbtnT);
        imgbtnF = findViewById(R.id.imgbtnF);
        //read 2
        lstv=findViewById(R.id.lstv);
        myNoteAdapter =new MyNoteAdapter(this,R.layout.msg_item_layout);
        //read 3: set adapter to listview (connect the the data ro list view
        lstv.setAdapter(myNoteAdapter);
        readNotfsFromFireBase("");
//
        imgbtnO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }

        });

        imgbtnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),importantmassege.class));
            }

        });
        imgbtnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),importantpeople.class));
            }

        });

        new Mynotificationservice().setListener(this);
        txtView = findViewById(R.id.itmText);
        Button btnCreateNotification = findViewById(R.id.btnCreateNotification);
        btnCreateNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager mNotificationManager = (NotificationManager) getSystemService( NOTIFICATION_SERVICE ) ;
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity. this, default_notification_channel_id ) ;
                mBuilder.setContentTitle( "My Notification" ) ;
                mBuilder.setContentText( "Notification Listener Service Example" ) ;
                mBuilder.setTicker( "Notification Listener Service Example" ) ;
                mBuilder.setSmallIcon(R.drawable. ic_launcher_foreground ) ;
                mBuilder.setAutoCancel( true ) ;
                if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
                    int importance = NotificationManager. IMPORTANCE_HIGH ;
                    NotificationChannel notificationChannel = new NotificationChannel( NOTIFICATION_CHANNEL_ID , "NOTIFICATION_CHANNEL_NAME" , importance) ;
                    mBuilder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
                    assert mNotificationManager != null;
                    mNotificationManager.createNotificationChannel(notificationChannel) ;
                }
                assert mNotificationManager != null;
                mNotificationManager.notify(( int ) System. currentTimeMillis () , mBuilder.build()) ;
            }
        }) ;
    }


    //read 5:
    /**
     * read tasks rom firebase and fill the adapter data structure
     * s- is text to search, if it is empty the method show all results
     * @param s
     */
    private void readNotfsFromFireBase(String s)
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        String uid = FirebaseAuth.getInstance().getUid();// cuurent user id.

        //اضافة امكانية "التحتلن" بكل تغيير سيحصل على القيم في قاعدة البيانات
        ref.child("myNotes").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myNoteAdapter.clear();
                for (DataSnapshot d:dataSnapshot.getChildren())
                {
                    MyNote t=d.getValue(MyNote.class);
                    myNoteAdapter.add(t);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

@Override
public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu. my_menu, menu) ; //Menu Resource, Menu
        return true;
        }
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id. action_settings :
                Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS" ) ;
                startActivity(intent) ;
                return true;
            default :
                return super .onOptionsItemSelected(item) ;
        }
    }


    @Override
    public void setValue(String packageName) {


            //txtView.append(" \n " + packageName);
        Log.d("ZENA"," \n " + packageName);
    }
}