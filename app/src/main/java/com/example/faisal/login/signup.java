package com.example.faisal.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class signup extends AppCompatActivity implements View.OnClickListener {
    EditText username ,password;
    TextView textView2;
    ProgressBar progressBar2;
    Button btn;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        username=findViewById(R.id.editText);
        password=findViewById(R.id.editText2);
        textView2=findViewById(R.id.textView2);
        btn=findViewById(R.id.button2);
        progressBar2=findViewById(R.id.progressBar2);
        auth=FirebaseAuth.getInstance();
        textView2.setOnClickListener(this);
        btn.setOnClickListener(this);
    }
    public void UserSignUp(){
        String email=username.getText().toString();
        String pass=password.getText().toString();
        progressBar2.setVisibility(View.VISIBLE);
        if(email.isEmpty()){
            username.setError("Plz Put Email");
            username.requestFocus();
            return;
        }
        if(pass.isEmpty()) {
            password.setError("Plz Put Password");
            password.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            username.setError("Email");
            username.requestFocus();
            return;
        }
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar2.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Sign Up Complete", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textView2:
                Intent i=new Intent(signup.this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.button2:
                UserSignUp();
        }

    }
}
