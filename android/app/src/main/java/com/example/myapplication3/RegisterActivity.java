package com.example.myapplication3;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.myapplication3.data.LoginRepository;
import com.example.myapplication3.data.model.Cert;
import com.example.myapplication3.data.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity<DatabaseReference> extends AppCompatActivity {

    EditText nameText;
    EditText idText;
    EditText passwordText;
    Button certificate;
    Button registerButton;
    View mainBack;

    private static final String TAB = "RegisterActivity";

    private DatabaseReference mDatabase;
    private LoginRepository FirebaseDatabase;
    

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final InputMethodManager keyboardManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

        nameText = findViewById(R.id.nameText);
        idText = findViewById(R.id.idText);
        passwordText = findViewById(R.id.passwordText);
        mainBack = findViewById(R.id.mainBack);
        certificate = findViewById(R.id.certificate);
        registerButton = findViewById(R.id.registerButton);

        // firebase 정의
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // 배경 터치시 키보드 내리기
        mainBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyboardManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });

        // 입력칸 터치시 키보드 올리기
        idText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 입력 테스트 키보드 올리기
                keyboardManager.showSoftInput(idText, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        // 인증하기 버튼
        certificate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String getUserID = idText.getText().toString();
                String getUserPW = passwordText.getText().toString();
                String getNameText = nameText.getText().toString();

                Log.d("TAB", "회원인증 버튼을 눌렀습니다.");

                certificateUser("1", getUserID, getUserPW, getNameText);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String getUserID = idText.getText().toString();
                String getUserPW = passwordText.getText().toString();
                String getNameText = nameText.getText().toString();

                Log.d("TAB", "회원가입 버튼을 눌렀습니다.");

                readUser("1", getUserID, getUserPW, getNameText);

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(intent);
                finish();
            }
        });
    }

    private void certificateUser(String userIndex, String userID, String password, String userName) {
        User user = new User(userID, "", userName);

        mDatabase.child("Cert").child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

//               if(dataSnapshot.getValue(User.class) != null){
//                    User post = dataSnapshot.getValue(User.class);
//                    Log.w("FireBaseData", "getData" + post.toString());
//                } else {
//                    Toast.makeText(MainActivity.this, "데이터가 없음", Toast.LENGTH_LONG).show();
//                }

                if(dataSnapshot.getValue() != null){
                    Cert post = dataSnapshot.getValue(Cert.class);
                    //Log.w("FireBaseData", "getData" + post.toString());
                    Log.d("TAB", "TEST::"+post.getCertName()+post.getCertNumber());

                    if(userID.equals(post.getCertNumber().toString())) {
                        Toast.makeText(RegisterActivity.this, "임산부 인증이 완료되었습니다", Toast.LENGTH_LONG).show();
                        writeNewUser("1", userID, password, userName);
                    } else {
                        Toast.makeText(RegisterActivity.this, "인증번호와 이름이 일치하지 않습니다", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(RegisterActivity.this, "데이터가 없음", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("FireBaseData", "loadPost:onCancelled", databaseError.toException());
            }
        });

    }

    private void writeNewUser(String userIndex, String userID, String password, String userName) {
        User user = new User(userID, password, userName);

        mDatabase.child("users").child(userIndex).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        Toast.makeText(RegisterActivity.this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        Toast.makeText(RegisterActivity.this, "저장을 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void readUser(String userIndex, String userID, String password, String userName){
        mDatabase.child("Cert").child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

//               if(dataSnapshot.getValue(User.class) != null){
//                    User post = dataSnapshot.getValue(User.class);
//                    Log.w("FireBaseData", "getData" + post.toString());
//                } else {
//                    Toast.makeText(MainActivity.this, "데이터가 없음", Toast.LENGTH_LONG).show();
//                }

                if(dataSnapshot.getValue() != null){
                    Cert post = dataSnapshot.getValue(Cert.class);
                    //Log.w("FireBaseData", "getData" + post.toString());
                    Log.d("TAB", "TEST::"+post.getCertName()+post.getCertNumber());

                    if(userID.equals(post.getCertNumber().toString())) {
                        Toast.makeText(RegisterActivity.this, "임산부 인증이 완료되었습니다", Toast.LENGTH_LONG).show();
                        writeNewUser("1", userID, password, userName);
                    } else {
                        Toast.makeText(RegisterActivity.this, "인증번호 존재하지 않음", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(RegisterActivity.this, "데이터가 없음", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("FireBaseData", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }
}