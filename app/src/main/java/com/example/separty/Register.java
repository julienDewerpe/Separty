package com.example.separty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * created by separty team on <Date du jour>
 **/
public class Register extends AppCompatActivity{
     /*
    TODO
        Button SignUp a faire comme le Button Login mais avec un traitement
     */

    private Button login;
    private Button signUp;
    private EditText password;;
    private EditText confPass;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        password = (EditText) findViewById(R.id.password);
        confPass = (EditText) findViewById(R.id.confPass);

        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

        signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginWithSignUp();
            }
        });
    }

    public void openLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void openLoginWithSignUp(){
        if (isValidPassword(password.getText().toString().trim())) {
            Toast.makeText(Register.this, "Valid", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Register.this, "InValid", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isValidPassword(String password){
        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

}
