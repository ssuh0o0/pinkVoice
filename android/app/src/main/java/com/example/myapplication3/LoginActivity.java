package com.example.myapplication3;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends Activity {

    View loginBack;

    private DatabaseReference mDatabase;

    private static final String LOGINTAB = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final InputMethodManager keyboardManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

        loginBack = findViewById(R.id.loginBack);

        // firebase 정의
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // 배경 터치시 키보드 내리기
        loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyboardManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });

        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(v -> {
            Intent registerIntent = new Intent(getBaseContext(), RegisterActivity.class);
            startActivity(registerIntent);
            finish();
        });

        final Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}