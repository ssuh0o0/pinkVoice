package com.example.myapplication3;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication3.data.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SeatView extends AppCompatActivity {

    Button button1_3_1;
    Button button1_3_5;
    Button button2_3_1;
    Button button2_3_5;
    Button button3_3_1;
    Button button3_3_5;
    Button button4_3_1;
    Button button4_3_5;
    Button button5_3_1;
    Button button5_3_5;
    Button button6_3_1;
    Button button6_3_5;
    Button button7_3_1;
    Button button7_3_5;
    Button button8_3_1;
    Button button8_3_5;
    Button button9_3_1;
    Button button9_3_5;
    Button button10_3_1;
    Button button10_3_5;

    private DatabaseReference mDatabase;

    private String deviceToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_view);

        button1_3_1 = findViewById(R.id.button1_3_1);
        button1_3_5 = findViewById(R.id.button1_3_5);

        button2_3_1 = findViewById(R.id.button2_3_1);
        button2_3_5 = findViewById(R.id.button2_3_5);

        button3_3_1 = findViewById(R.id.button3_3_1);
        button3_3_5 = findViewById(R.id.button3_3_5);

        button4_3_1 = findViewById(R.id.button4_3_1);
        button4_3_5 = findViewById(R.id.button4_3_5);

        button5_3_1 = findViewById(R.id.button5_3_1);
        button5_3_5 = findViewById(R.id.button5_3_5);

        button6_3_1 = findViewById(R.id.button6_3_1);
        button6_3_5 = findViewById(R.id.button6_3_5);

        button7_3_1 = findViewById(R.id.button7_3_1);
        button7_3_5 = findViewById(R.id.button7_3_5);

        button8_3_1 = findViewById(R.id.button8_3_1);
        button8_3_5 = findViewById(R.id.button8_3_5);

        button9_3_1 = findViewById(R.id.button9_3_1);
        button9_3_5 = findViewById(R.id.button9_3_5);

        button10_3_1 = findViewById(R.id.button10_3_1);
        button10_3_5 = findViewById(R.id.button10_3_5);


        SharedPreferences sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
        deviceToken = sharedPreferences.getString("inputToken", "");

        // firebase 정의
        mDatabase = FirebaseDatabase.getInstance().getReference();

        showSeatStatus("1");

//        button1_3_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SeatView.this);
//                builder.setTitle("좌석 선택");
//                builder.setMessage("1-3의 1번 좌석을 이용하시겠습니까?");
//                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    @RequiresApi(api = Build.VERSION_CODES.M)
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SeatView.this, "좌석 이용이 시작됩니다", Toast.LENGTH_SHORT).show();
//                        useSeatID("1", deviceToken);
//                        useSeatToken("1", deviceToken);
//                    }
//                });
//                builder.setNegativeButton("취소", null);
//                builder.create().show();
//            }
//        });
//
//        button1_3_5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SeatView.this);
//                builder.setTitle("좌석 선택");
//                builder.setMessage("1-3의 2번 좌석을 이용하시겠습니까?");
//                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SeatView.this, "좌석 이용이 시작됩니다", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.setNegativeButton("취소", null);
//                builder.create().show();
//            }
//        });
//
//
//        button2_3_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SeatView.this);
//                builder.setTitle("좌석 선택");
//                builder.setMessage("2-3의 1번 좌석을 이용하시겠습니까?");
//                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SeatView.this, "좌석 이용이 시작됩니다", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.setNegativeButton("취소", null);
//                builder.create().show();
//            }
//        });
//
//
//        button2_3_5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SeatView.this);
//                builder.setTitle("좌석 선택");
//                builder.setMessage("2-3의 2번 좌석을 이용하시겠습니까?");
//                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SeatView.this, "좌석 이용이 시작됩니다", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.setNegativeButton("취소", null);
//                builder.create().show();
//            }
//        });
//
//
//        button3_3_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SeatView.this);
//                builder.setTitle("좌석 선택");
//                builder.setMessage("3-3의 1번 좌석을 이용하시겠습니까?");
//                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SeatView.this, "좌석 이용이 시작됩니다", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.setNegativeButton("취소", null);
//                builder.create().show();
//            }
//        });
//
//
//        button3_3_5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SeatView.this);
//                builder.setTitle("좌석 선택");
//                builder.setMessage("3-3의 2번 좌석을 이용하시겠습니까?");
//                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SeatView.this, "좌석 이용이 시작됩니다", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.setNegativeButton("취소", null);
//                builder.create().show();
//            }
//        });
//
//
//        button4_3_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SeatView.this);
//                builder.setTitle("좌석 선택");
//                builder.setMessage("4-3의 1번 좌석을 이용하시겠습니까?");
//                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SeatView.this, "좌석 이용이 시작됩니다", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.setNegativeButton("취소", null);
//                builder.create().show();
//            }
//        });
//
//
//        button4_3_5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SeatView.this);
//                builder.setTitle("좌석 선택");
//                builder.setMessage("4-3의 2번 좌석을 이용하시겠습니까?");
//                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SeatView.this, "좌석 이용이 시작됩니다", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.setNegativeButton("취소", null);
//                builder.create().show();
//            }
//        });
//
//
//        button5_3_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SeatView.this);
//                builder.setTitle("좌석 선택");
//                builder.setMessage("5-3의 1번 좌석을 이용하시겠습니까?");
//                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SeatView.this, "좌석 이용이 시작됩니다", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.setNegativeButton("취소", null);
//                builder.create().show();
//            }
//        });
//
//
//        button5_3_5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SeatView.this);
//                builder.setTitle("좌석 선택");
//                builder.setMessage("5-3의 2번 좌석을 이용하시겠습니까?");
//                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SeatView.this, "좌석 이용이 시작됩니다", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.setNegativeButton("취소", null);
//                builder.create().show();
//            }
//        });
//
//
//        button6_3_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SeatView.this);
//                builder.setTitle("좌석 선택");
//                builder.setMessage("6-3의 1번 좌석을 이용하시겠습니까?");
//                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SeatView.this, "좌석 이용이 시작됩니다", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.setNegativeButton("취소", null);
//                builder.create().show();
//            }
//        });
//
//
//        button6_3_5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SeatView.this);
//                builder.setTitle("좌석 선택");
//                builder.setMessage("6-3의 2번 좌석을 이용하시겠습니까?");
//                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SeatView.this, "좌석 이용이 시작됩니다", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.setNegativeButton("취소", null);
//                builder.create().show();
//            }
//        });
//
//
//        button7_3_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SeatView.this);
//                builder.setTitle("좌석 선택");
//                builder.setMessage("7-3의 1번 좌석을 이용하시겠습니까?");
//                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SeatView.this, "좌석 이용이 시작됩니다", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.setNegativeButton("취소", null);
//                builder.create().show();
//            }
//        });
//
//
//        button7_3_5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SeatView.this);
//                builder.setTitle("좌석 선택");
//                builder.setMessage("7-3의 2번 좌석을 이용하시겠습니까?");
//                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SeatView.this, "좌석 이용이 시작됩니다", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.setNegativeButton("취소", null);
//                builder.create().show();
//            }
//        });
//
//
//        button8_3_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SeatView.this);
//                builder.setTitle("좌석 선택");
//                builder.setMessage("8-3의 1번 좌석을 이용하시겠습니까?");
//                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SeatView.this, "좌석 이용이 시작됩니다", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.setNegativeButton("취소", null);
//                builder.create().show();
//            }
//        });
//
//
//        button8_3_5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SeatView.this);
//                builder.setTitle("좌석 선택");
//                builder.setMessage("8-3의 2번 좌석을 이용하시겠습니까?");
//                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SeatView.this, "좌석 이용이 시작됩니다", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.setNegativeButton("취소", null);
//                builder.create().show();
//            }
//        });
//
//
//
//        button9_3_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SeatView.this);
//                builder.setTitle("좌석 선택");
//                builder.setMessage("9-3의 1번 좌석을 이용하시겠습니까?");
//                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SeatView.this, "좌석 이용이 시작됩니다", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.setNegativeButton("취소", null);
//                builder.create().show();
//            }
//        });
//
//
//        button9_3_5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SeatView.this);
//                builder.setTitle("좌석 선택");
//                builder.setMessage("9-3의 2번 좌석을 이용하시겠습니까?");
//                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SeatView.this, "좌석 이용이 시작됩니다", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.setNegativeButton("취소", null);
//                builder.create().show();
//            }
//        });
//
//
//
//        button10_3_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SeatView.this);
//                builder.setTitle("좌석 선택");
//                builder.setMessage("10-3의 1번 좌석을 이용하시겠습니까?");
//                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SeatView.this, "좌석 이용이 시작됩니다", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.setNegativeButton("취소", null);
//                builder.create().show();
//            }
//        });
//
//
//        button10_3_5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SeatView.this);
//                builder.setTitle("좌석 선택");
//                builder.setMessage("10-3의 2번 좌석을 이용하시겠습니까?");
//                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(SeatView.this, "좌석 이용이 시작됩니다", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.setNegativeButton("취소", null);
//                builder.create().show();
//            }
//        });

    }

    private void useSeat(String seatNumber, String userID) {

        final DatabaseReference seatRef = mDatabase.child("Seats").child("seat"+seatNumber).child("seatUser");

        seatRef.setValue(userID)
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
                        Toast.makeText(SeatView.this, "저장을 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void useSeatToken(String seatNumber, String deviceToken) {

        final DatabaseReference seatRef = mDatabase.child("Seats").child("seat"+seatNumber).child("seatToken");

        seatRef.setValue(deviceToken)
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
                        Toast.makeText(SeatView.this, "저장을 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void useSeatID(String seatNumber, String deviceToken) {

        final DatabaseReference userRef = mDatabase.child("Users").child(deviceToken);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    User user = snapshot.getValue(User.class);

                    useSeat(seatNumber, user.getUserID());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }


    private void showSeatStatus(String seatNumber) {

        final DatabaseReference seatRef = mDatabase.child("Seats").child("seat"+seatNumber).child("seatUser");

        seatRef.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    String seatUser = (String) snapshot.getValue();

                    if (!seatUser.equals("")) {
                        button1_3_1.setBackgroundColor(getColor(R.color.usedSeat));
                        Toast.makeText(SeatView.this, "좌석 이용이 시작됩니다", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

}