package com.example.separty.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.separty.R;
import com.example.separty.Tools.Requests;
import com.example.separty.Tools.VolleySingelton;

public class Login extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.separty.example.EXTRA_TEXT";

    private Button signUp;
    private Button login;
    private EditText password;
    private EditText username;
    private RequestQueue queue;
    private Requests request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

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

        Intent intent = getIntent();
        String username = intent.getStringExtra(Register.EXTRA_TEXT);
        TextView userTextView = (TextView) findViewById(R.id.username);
        userTextView.setText(username);

        queue = VolleySingelton.getInstance(this).getRequestQueue();
        request = new Requests(this, queue);
    }


    public void openRegister() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void openProfile() {
        String user = username.getText().toString().trim();
        String pwd = password.getText().toString().trim();
        request.connection(user, pwd, new Requests.LoginCallback() {
            @Override
            public void onSuccess(String id, String pseudo) {
                EditText username = (EditText) findViewById(R.id.username);
                String text = username.getText().toString();

                Intent intent;
                intent = new Intent(getApplicationContext(), Profile.class);
                intent.putExtra(EXTRA_TEXT, text);
                startActivity(intent);
            }

            @Override
            public void onError(String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
