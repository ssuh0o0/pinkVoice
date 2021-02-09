package com.example.myapplication3;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.myapplication3.data.model.Cert;
import com.example.myapplication3.data.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText nameText;
    EditText idText;
    EditText passwordText;
    Button certificate;
    Button registerButton;
    View mainBack;

    private static final String TAB = "RegisterActivity";

    private DatabaseReference mDatabase;

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
                //String getUserPW = passwordText.getText().toString();
                String getNameText = nameText.getText().toString();

                Log.d("TAB", "회원인증 버튼을 눌렀습니다.");

                if(getNameText.equals("")) {
                    Toast.makeText(RegisterActivity.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else if(getUserID.equals("")) {
                    Toast.makeText(RegisterActivity.this, "ID(임산부 고유 번호)를 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    certificateUser("1", getUserID,  getNameText);
                }

            }
        });

        // 회원가입 버튼
        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String getUserID = idText.getText().toString();
                String getUserPW = passwordText.getText().toString();
                String getNameText = nameText.getText().toString();

                Log.d("TAB", "회원가입 버튼을 눌렀습니다.");

                if(getNameText.equals("")) {
                    Toast.makeText(RegisterActivity.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else if(getUserID.equals("")) {
                    Toast.makeText(RegisterActivity.this, "ID(임산부 고유 번호)를 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else if(getUserPW.equals("")) {
                    Toast.makeText(RegisterActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    // 먼저 인증이 되어있는 지 확인 후 회원가입 진행
                    readIsCert("1", getUserID, getUserPW, getNameText);
                }
            }
        });
    }

    // 인증 버튼 -> isCert: false => true
    private void certificateUser(String userIndex, String userID, String userName) {

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
                    HashMap data = (HashMap) dataSnapshot.getValue();
                    Long dbCertNumber = (Long) data.get("certNumber");
                    String dbCertName = (String) data.get("certName");
                    Boolean dbIsCert = (Boolean) data.get("isCert");

                    Cert cert = new Cert(dbCertNumber, dbCertName, dbIsCert);
                    // 인증이 일치하는 경우
                    if(userID.equals(cert.getCertNumber().toString()) && userName.equals(cert.getCertName())) {
                        Toast.makeText(RegisterActivity.this, "임산부 인증이 완료되었습니다", Toast.LENGTH_SHORT).show();
                        // writeNewUser("1", userID, password, userName);

                        final DatabaseReference isCertRef = mDatabase.child("Cert").child(userIndex).child("isCert");
                        isCertRef.setValue(true)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // Write was successful!
                                        //Toast.makeText(RegisterActivity.this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Write failed
                                        Toast.makeText(RegisterActivity.this, "저장을 실패했습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    } else {
                        // 인증번호만 일치하는 경우
                        if(userID.equals(cert.getCertNumber().toString()) && !userName.equals(cert.getCertName())) {
                            Toast.makeText(RegisterActivity.this, "이름이 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                        }
                        // 이름만 일치하는 경우
                        else if (!userID.equals(cert.getCertNumber().toString()) && userName.equals(cert.getCertName())) {
                            Toast.makeText(RegisterActivity.this, "인증번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                        }
                        // 둘 다 일치하지 않은 경우
                        else {
                            Toast.makeText(RegisterActivity.this, "일치하는 정보가 없습니다", Toast.LENGTH_SHORT).show();
                        }

                    }

                } else {
                    Toast.makeText(RegisterActivity.this, "데이터가 없음", Toast.LENGTH_SHORT).show();
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

        mDatabase.child("Users").child(userIndex).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        //Toast.makeText(RegisterActivity.this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show();
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

    private void readIsCert(String userIndex, String userID, String password, String userName){

        final DatabaseReference isCertRef = mDatabase.child("Cert").child("1").child("isCert");
        isCertRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    Boolean dbIsCert = (Boolean) snapshot.getValue();
                    if(dbIsCert) {

                        final DatabaseReference isExistUser = mDatabase.child("Users").child("1").child("userID");

                        isExistUser.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshotdata) {
                                if (snapshotdata.exists()) {
                                    String dbUserID = (String) snapshotdata.getValue();
                                    // 이미 Users에 일치하는 정보가 있을 경우
                                    if(dbUserID.equals(userID)) {
                                        Toast.makeText(RegisterActivity.this, "이미 가입된 회원입니다", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    Toast.makeText(RegisterActivity.this, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show();
                                    writeNewUser(userIndex, userID, password, userName);
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    RegisterActivity.this.startActivity(intent);
                                    finish();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    } else {
                        Toast.makeText(RegisterActivity.this, "인증을 먼저 완료해주세요", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "데이터가 없음", Toast.LENGTH_SHORT).show();
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