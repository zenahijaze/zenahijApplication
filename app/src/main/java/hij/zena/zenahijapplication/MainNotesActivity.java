package hij.zena.zenahijapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import hij.zena.zenahijapplication.MyData.MyNote;
import hij.zena.zenahijapplication.MyData.MyNoteAdapter;

// listener 1.
public class MainNotesActivity extends AppCompatActivity implements DialogInterface.OnClickListener {
///lkjhhkhjk
    private FloatingActionButton fabAdd;
    //read: 1
    private ListView lstv;
    private MyNoteAdapter myNoteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //read 2
        lstv=findViewById(R.id.lstv);
        myNoteAdapter =new MyNoteAdapter(this,R.layout.msg_item_layout);
        //read 3: set adapter to listview (connect the the data ro list view
        lstv.setAdapter(myNoteAdapter);
        readTasksFromFireBase("");//بتقرا الرسائل الي وصلت من ال firebase وبتنزلهن على ال listview
//
//        fabAdd=findViewById(R.id.fabAdd);
//        fabAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),Mynotificationservice.class));
//            }
//        });
    }
    //read 4:


    @Override
    protected void onResume() {
        super.onResume();//تشتغل عندما ندخل الى fragment
                         //يبدا يتفاعل مع المستخدم,يستمر النشاط في هذة الحالة حتى يحدث شيء لاخذ التركيز من التطبيق

        readTasksFromFireBase(""); //وظيفتها القراءة من مصدر البيانات وتنزيل المعطيات
    }

    //read 5:
    /**
     * read tasks rom firebase and fill the adapter data structure
     * s- is text to search, if it is empty the method show all results
     * @param s
     */
    private void readTasksFromFireBase(String s)
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        String uid = FirebaseAuth.getInstance().getUid();// cuurent user id.

                      //اضافة امكانية "التحتلن" بكل تغيير سيحصل على القيم في قاعدة البيانات
        ref.child("myNotes").child(uid).addValueEventListener(new ValueEventListener() {//معالج حدث يعيد كل الذي تحت العنوان children
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myNoteAdapter.clear();
                for (DataSnapshot d:dataSnapshot.getChildren())//dataSnapshotهي نسخة عن كل البيانات الموجودة
                {
                    MyNote t=d.getValue(MyNote.class);
                    if(t.isIsnecessity()==false && t.isIsimportant()==false)
                         myNoteAdapter.add(t);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {// تعمل هذة الدالة عندما نعطل التنزيل

            }
        });

    }

    //1. build menu xml
    //2. to add menu (3 point right-top corner) to the current activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
                                 //the xml menu file
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return true;
    }
    //3. select item event handler
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        return true;
    }

    //listener 2. implemnet method

     /**
      * event handler
      * @param dialogInterface the active dialog
      * @param which the button id which cause the event
      */
    @Override
    public void onClick(DialogInterface dialogInterface, int which) {
        ////listener 4. react for each action
        if(which==dialogInterface.BUTTON_POSITIVE)
        {

            Toast.makeText(getApplicationContext(), "loging out", Toast.LENGTH_SHORT).show();
            dialogInterface.cancel();
            //للتسجيل الخروج
            FirebaseAuth auth=FirebaseAuth.getInstance();
            auth.signOut();

            finish();//to close current activity
        }
        if(which==dialogInterface.BUTTON_NEGATIVE)
        {
            Toast.makeText(getApplicationContext(), "loging out canceled", Toast.LENGTH_SHORT).show();
            dialogInterface.cancel();
        }
    }


}