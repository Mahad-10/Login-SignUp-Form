package pk.codebase.firstfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginForm extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        Button regBtn = findViewById(R.id.registerBtn);
        final EditText emailTxt = findViewById(R.id.email_txt);
        final EditText passTxt = findViewById(R.id.pass_txt);
        Button btnLogin = findViewById(R.id.btn_login);
        final FirebaseAuth firebaseAuth;

        firebaseAuth = FirebaseAuth.getInstance();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailTxt.getText().toString().trim();
                String pass = passTxt.getText().toString().trim();
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(LoginForm.this,"Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    Toast.makeText(LoginForm.this,"Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(LoginForm.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                startActivity(new Intent(LoginForm.this,MainActivity.class));

                            }else{
                                    Toast.makeText(LoginForm.this,"Username or password dont match.\nEnter Again",Toast.LENGTH_SHORT).show();
                                }

                                }
                        });

            }
        });
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginForm.this,Signup_Form.class);
                startActivity(intent);
            }
        });

    }
}
