package hij.zena.zenahijapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class importantmassege extends AppCompatActivity {
    private ImageView imgv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_importantmassege);
        imgv2 = findViewById(R.id.imgv1);
    }
}