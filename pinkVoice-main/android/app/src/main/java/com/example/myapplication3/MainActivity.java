package com.example.myapplication3;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.integration.android.IntentIntegrator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    Fragment qr;
    Fragment home;
    Fragment mypage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //큐알코드 - main activity
//        IntentIntegrator integrator = new IntentIntegrator(this);
//
//        integrator.setOrientationLocked(false);
//        integrator.setPrompt("Scan QR code");
//        integrator.setBeepEnabled(false); //beep sound when the QR code is scanned
//
//        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
//
//        integrator.initiateScan();



        //하단 탭
        qr = new QRFragment();
        home = new HomeFragment();
        mypage = new MypageFragment();

        //초기 첫 화면
        getSupportFragmentManager().beginTransaction().replace(R.id.container, home).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.navigation_qr:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, qr).commit();
                                return true;

                            case R.id.navigation_home:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, home).commit();
                                return true;

                            case R.id.navigation_mypage:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, mypage).commit();
                                return true;
                        }
                        return false;
                    }
                }
        );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
    }

}