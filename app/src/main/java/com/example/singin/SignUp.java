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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
    private EditText userName;
    private EditText Password;
    private Button signInButton ;
    private TextView GotoSignUp ;
    private ProgressBar progressBar ;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        userName = findViewById(R.id.userNameEdittext);
        Password = findViewById(R.id.PasswordEdittext);
        signInButton = findViewById(R.id.buttonSignUp);
        GotoSignUp = findViewById(R.id.goToSignIn);
        progressBar = findViewById(R.id.signUpProgressbar);

        signInButton.setOnClickListener(this);
        GotoSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.buttonSignUp){
            userRegister() ;
        }

        if(view.getId()==R.id.goToSignIn){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
    }

    private void userRegister() {

        progressBar.setVisibility(View.VISIBLE);
        String name = userName.getText().toString().trim();
        String password = Password.getText().toString().trim();

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

        mAuth.createUserWithEmailAndPassword(name,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"Register is Successfull",Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.GONE);
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"User Already Resistered" , Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Error : " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

}