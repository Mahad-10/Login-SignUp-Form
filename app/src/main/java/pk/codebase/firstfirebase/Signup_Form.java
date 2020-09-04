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

public class Signup_Form extends AppCompatActivity {
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_form);
        final EditText emailTxt = findViewById(R.id.email_edit_txt);
        final EditText passTxt = findViewById(R.id.pass_edit_txt);
        final EditText cnfrmPassTxt = findViewById(R.id.confirm_pass_edit_txt);
        Button btnReg = findViewById(R.id.btn_Register);

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailTxt.getText().toString().trim();
                String password = passTxt.getText().toString().trim();
                String cnfrmPass = cnfrmPassTxt.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Signup_Form.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Signup_Form.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(cnfrmPass)){
                    Toast.makeText(Signup_Form.this,"Please Enter Confirm Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6){
                    Toast.makeText(Signup_Form.this,"Password too short.Must be greater than 6",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.equals(cnfrmPass)){
                    firebaseAuth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(Signup_Form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        Toast.makeText(Signup_Form.this,"Registeration Successful"
                                                ,Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(Signup_Form.this,"Authentication Failed"
                                        ,Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(Signup_Form.this,"Passwords do not match"
                            ,Toast.LENGTH_SHORT).show();
                }
            }
        });
        }
}
