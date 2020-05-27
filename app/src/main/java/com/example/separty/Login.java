package com.example.separty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.separty.example.EXTRA_TEXT";

    private Button signUp;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    openRegister();
            }
        });

        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });
    }


    public void openRegister(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void openProfile(){

        EditText username = (EditText) findViewById(R.id.username);
        String text = username.getText().toString() ;

        Intent intent = new Intent(this, Profile.class);
        intent.putExtra(EXTRA_TEXT, text);
        startActivity(intent);
    }

}
