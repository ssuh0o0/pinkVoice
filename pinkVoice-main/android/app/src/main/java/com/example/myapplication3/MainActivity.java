package com.example.myapplication3;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        //search_menu.xml 등록
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem mSearch = menu.findItem(R.id.search);


        //menuItem을 이용해서 SearchView 변수 생성
        SearchView sv=(SearchView)mSearch.getActionView();
        //확인버튼 활성화
        sv.setSubmitButtonEnabled(true);

        //SearchView의 검색 이벤트
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            //검색버튼을 눌렀을 경우
            @Override
            public boolean onQueryTextSubmit(String query) {
                TextView text = (TextView)findViewById(R.id.txtsearch);
                text.setText(query + "를 검색합니다.");
                return true;
            }

            //텍스트가 바뀔때마다 호출
            @Override
            public boolean onQueryTextChange(String newText) {
                TextView text = (TextView)findViewById(R.id.txtsearch);
                text.setText("검색식 : "+newText);
                return true;
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.navigation_qr:
                                mSearch.setVisible(false);
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, qr).commit();
                                return true;

                            case R.id.navigation_mypage:
                                mSearch.setVisible(false);
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, mypage).commit();
                                return true;

                            case R.id.navigation_home:
                                mSearch.setVisible(true);
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, home).commit();

                                return true;
                        }
                        return false;
                    }
                }
        );


        return true;
    }

}