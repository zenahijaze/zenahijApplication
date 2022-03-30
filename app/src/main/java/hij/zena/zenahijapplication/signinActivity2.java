package hij.zena.zenahijapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;

public class signinActivity2 extends AppCompatActivity {
    private TextInputEditText etEmail,etPassword;
    private Button btnLogin,btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin2);
        //اذا عندو حساب بنقل على الmainActivity
        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
       btnregister=findViewById(R.id.btnregister);
        btnLogin=findViewById(R.id.btnLogin);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),signupActivity2.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }

            private void validate() {
            }

        });

    }

}