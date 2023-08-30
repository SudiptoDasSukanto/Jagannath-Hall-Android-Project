package com.example.singin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextInputLayout userName;
    private TextInputLayout Password;
    private Button signInButton ;
    private TextView GotoSignUp ;
    private ProgressBar progressBar ;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        userName = findViewById(R.id.userNameEdittext);
        Password = findViewById(R.id.PasswordEdittext);
        signInButton = findViewById(R.id.buttonSignIn);
        GotoSignUp = findViewById(R.id.goToSignUp);
        progressBar = findViewById(R.id.signInProgressbar);

        signInButton.setOnClickListener(this);
        GotoSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.buttonSignIn){
            userLogin();
        }

        if(view.getId()==R.id.goToSignUp){
            Intent intent = new Intent(getApplicationContext(),SignUp.class);
            startActivity(intent);
        }
    }

    private void userLogin() {
        progressBar.setVisibility(View.VISIBLE);
        String name = userName.getEditText().getText().toString().trim();
        String password = Password.getEditText().getText().toString().trim();

        if(name.isEmpty()){
            userName.setError("Must need Email Address");
            userName.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(name).matches()){
            userName.setError("Enter valid Email addrass");
        }

        if(password.isEmpty()){
            Password.setError("Enter password");
            Password.requestFocus();
            return;
        }

        if(password.length()<6){
            Password.setError("Minimum length of Password Must above 6");
            Password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(name,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(getApplicationContext(),dashboard.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Login Unsuccessfull",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}