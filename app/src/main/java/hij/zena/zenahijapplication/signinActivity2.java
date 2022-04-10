package hij.zena.zenahijapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import hij.zena.zenahijapplication.MyData.MyNote;

public class signinActivity2 extends AppCompatActivity {
    private TextInputEditText etEmail, etPassword;
    private Button btnLogin, btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin2);
        //اذا عندو حساب بنقل على الmainActivity
        if (FirebaseAuth.getInstance().getCurrentUser() != null)
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnregister = findViewById(R.id.btnregister);
        btnLogin = findViewById(R.id.btnLogin);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), signupActivity2.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });


    }

    private void validate()
    {
        boolean isOk=true;
        String email=etEmail.getText().toString();
        String passw=etPassword.getText().toString();
        if(email.length()==0 )
        {
            etEmail.setError("enter email");
            isOk=false;

        }
        if(passw.length()<8 )
        {
            etPassword.setError("pass word at least 8 letters");
            isOk=false;
        }

        if(isOk)
        {
            signiningIn(email,passw);
        }
    }

    private void signiningIn(String email, String passw)
    {
        FirebaseAuth auth=FirebaseAuth.getInstance();

        auth.signInWithEmailAndPassword(email,passw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(), "signin in successsful", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
                else
                    Toast.makeText(getApplicationContext(), "signin in Error:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}