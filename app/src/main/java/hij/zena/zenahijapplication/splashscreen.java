package hij.zena.zenahijapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class splashscreen extends AppCompatActivity {
    private ImageView imview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        //Thread.1
        Thread th=new Thread(){
            //Thread.2
            @Override
            public void run() {
                //هنا المقطع الذي سيعمل بالتزامن مع مقاطع اخرى
                //thread.3
                int ms=3*1000;//milliseconnds
                try {
                    sleep(ms);
                    finish();
                    //فحص هل تم الدخول مسبقا
                    FirebaseAuth auth=FirebaseAuth.getInstance();
                    if(auth.getCurrentUser()!=null)
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    else
                    {
                        startActivity(new Intent(getApplicationContext(),signinActivity2.class));
                    }

                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        th.start();
        imview=findViewById(R.id.imview);
    }
}