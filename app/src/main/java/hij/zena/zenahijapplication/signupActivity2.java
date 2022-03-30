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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class signupActivity2 extends AppCompatActivity {
    private TextInputEditText etEmail, etPassword, etRepassword, etPhone, etFullName;
    private Button btnsave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etRepassword = findViewById(R.id.etRepassword);
        etPhone = findViewById(R.id.etPhone);
        etFullName = findViewById(R.id.etFullName);
        btnsave = findViewById(R.id.btnsave);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    validate();
            }
        });

    }


        private void validate ()
        {
            boolean isOk = true;
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            String rePassword =  etRepassword.getText().toString();
            String phone = etPhone.getText().toString();
            String fullName = etFullName.getText().toString();
            if (email.length() < 5 || email.indexOf('@') <= 0) {
                etEmail.setError("wrong email syntax");
                isOk = false;
            }
            if(password.length()<8) {
                etPassword.setError("at lest 8 chars");
                isOk = false;
            }
            if(password.equals(rePassword)==false) {
                etRepassword.setError("not equal passwords");
                isOk = false;
            }
            if(fullName.length()==0) {
                etFullName.setError("must to enter full name");
                isOk = false;
            }
            if(isOk)//isOK==true
            {
                createtAccount(email, password);
            }
    }

    private void createtAccount(String email, String password)
    {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        //request
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            //response handler
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful()==true)
                {
                    finish();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
                else
                {
                    //dialog
                    Toast.makeText(getApplicationContext(), ""+task.isSuccessful()+'\n'+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}