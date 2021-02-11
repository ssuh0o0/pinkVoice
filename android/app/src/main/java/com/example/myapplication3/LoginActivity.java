package com.example.myapplication3;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.myapplication3.data.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends Activity {

    View loginBack;
    EditText idText;
    EditText passwordText;

    private DatabaseReference mDatabase;

    private static final String LOGINTAB = "LoginActivity";

    private String deviceToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final InputMethodManager keyboardManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

        loginBack = findViewById(R.id.loginBack);
        idText = findViewById(R.id.idText);
        passwordText = findViewById(R.id.passwordText);

        // firebase 정의
        mDatabase = FirebaseDatabase.getInstance().getReference();

        SharedPreferences sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
        deviceToken = sharedPreferences.getString("inputToken", "");

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

        // 로그인 버튼 클릭
        final Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> {

            String getIdText = idText.getText().toString();
            String getPasswordText = passwordText.getText().toString();

            if(getIdText.equals("")) {
                Toast.makeText(LoginActivity.this, "ID(임산부 고유 번호)를 입력해주세요", Toast.LENGTH_SHORT).show();
            }
            else if(getPasswordText.equals("")) {
                Toast.makeText(LoginActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
            }
            else {
                checkLogin(getIdText, getPasswordText);
            }
        });

    }

    // 회원 정보 있는 지 확인
    private void checkLogin(String idText, String passwordText) {

        final DatabaseReference isExistUser = mDatabase.child("Users").child(deviceToken);

        //ArrayList<User> users = new ArrayList<User>();

        isExistUser.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    User user = snapshot.getValue(User.class);

                    if(user.getUserID().equals(idText) && user.getPassword().equals(passwordText)) {
                        Toast.makeText(LoginActivity.this, "로그인 완료", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getBaseContext(), SeatView.class);
                        startActivity(intent);
                        finish();
                    }
                    else if (!user.getUserID().equals(idText) && user.getPassword().equals(passwordText)) {
                        Toast.makeText(LoginActivity.this, "일치하는 ID(임산부 인증 번호)가 없습니다", Toast.LENGTH_SHORT).show();
                    }
                    else if (user.getUserID().equals(idText) && !user.getPassword().equals(passwordText)) {
                        Toast.makeText(LoginActivity.this, "일치하는 비밀번호가 없습니다", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "회원가입 먼저 진행해주세요", Toast.LENGTH_SHORT).show();
                    }
//                    Integer userCount = Math.toIntExact(snapshot.getChildrenCount());
//
//                    ArrayList users = (ArrayList) snapshot.getValue();
//
//                    for(int i = 1; i <= userCount; i ++ ) {
//                        Log.d(LOGINTAB, "USERS:" + users.get(i));
//                        HashMap user = (HashMap) users.get(i);
//                        String dbUserID = (String) user.get("userID");
//                        String dbPassword = (String) user.get("password");
//
//                        if(dbUserID.equals(idText) && dbPassword.equals(passwordText)) {
//                            Toast.makeText(LoginActivity.this, "로그인 완료", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(getBaseContext(), MainActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                    }
//                    Toast.makeText(LoginActivity.this, "일치하는 회원 정보가 없습니다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}