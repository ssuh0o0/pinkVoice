package com.example.myapplication3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.ScrollView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    ScrollView scrollView;
    ImageView imageView;
    BitmapDrawable bitmap;

    XmlPullParser xpp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                 R.id.navigation_qr, R.id.navigation_home, R.id.navigation_mypage)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        SharedPreferences sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
        if(sharedPreferences.getString("inputToken", "").equals("")) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("inputToken", getUUID());
            editor.commit();
        }

        //지하철 노선도 사진 HorizontalScrollView
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        imageView = (ImageView) findViewById(R.id.imageView);
        scrollView.setHorizontalScrollBarEnabled(true);

        Resources res = getResources();
        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.route);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().width = bitmapWidth;
        imageView.getLayoutParams().height = bitmapHeight;

        Button button=findViewById(R.id.Chungmuro);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this,SubwayTimeActivity.class);
                startActivity(intent);

            }
        });



        //getAbsCoord(R.id.imageView);

    }

//    private void getAbsCoord(int resId) {
//        View v = findViewById(resId);
//        if (v == null) throw new IllegalArgumentException("this is not a view");
//        Rect r = new Rect(); v.getGlobalVisibleRect(r); //RootView 레이아웃을 기준으로한 좌표.
//        // custom Log
//        //Log.i(v.getResources().getResourceName(resId).split(":")[1] + " 의절대좌표::", r.left, r.top, r.right, r.bottom);
//    }

    // 식별자 지정
    private String getUUID() {
        return UUID.randomUUID().toString();
    }


}