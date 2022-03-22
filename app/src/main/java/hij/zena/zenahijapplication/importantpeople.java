package hij.zena.zenahijapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class importantpeople extends AppCompatActivity {
    private ImageView imgv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_importantpeople);
        imgv1 = findViewById(R.id.imgv1);

    }
}