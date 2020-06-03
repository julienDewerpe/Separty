package com.example.separty.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.separty.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * created by separty team on <Date du jour>
 **/
public class Register extends AppCompatActivity{

    public static final String EXTRA_TEXT = "com.example.separty.example.EXTRA_TEXT";
    private Button login;
    private Button signUp;
    private EditText username;
    private EditText password;
    private EditText confPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confPass = findViewById(R.id.confPass);

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

    public void openLogin(String username){
        Intent intent = new Intent(this, Login.class);
        intent.putExtra(EXTRA_TEXT, username);
        startActivity(intent);
    }

    public void openLoginWithSignUp(){
        String user = username.getText().toString().trim();
        String pwd = password.getText().toString().trim();
        String cfpwd = confPass.getText().toString().trim();
        if (isValidPassword(pwd)) {
            Toast.makeText(Register.this, "Valid password", Toast.LENGTH_SHORT).show();
            if (isSamePassword(pwd, cfpwd)){
                //DB
                openLogin(user);
            }
            else{
                Toast.makeText(Register.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(Register.this, "Invalid password", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isValidPassword(String pwd){
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(pwd);
        return matcher.matches();
    }

    public boolean isSamePassword(String pwd, String cfpwd){
        return (pwd.equals(cfpwd));
    }

}
