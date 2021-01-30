package com.example.nodemcutest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    final String TAG = "MainActivity";
    ImageView ledImg;
    ToggleButton ledButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ledButton = (ToggleButton)findViewById(R.id.toggleButton);
        //ledImg = (ImageView)findViewById(R.id.imageView);

        //토글버튼 클릭 이벤트
        ledButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ledStatus;
                if(ledButton.isChecked()){
                    //ledImg.setImageResource(R.drawable.nodemcuon);
                    Toast.makeText(MainActivity.this, "ON", Toast.LENGTH_SHORT).show();
                    ledStatus = "ledOn";
                } else {
                    //ledImg.setImageResource(R.drawable.nodemcuoff);
                    Toast.makeText(MainActivity.this, "OFF", Toast.LENGTH_SHORT).show();
                    ledStatus = "ledOff";
                }

                //클릭하면 http request
                String serverAdress = "your_nodeMcu_webserver_ip:80";//서버의 ip와 port를 적어 준다.
                HttpRequestTask requestTask = new HttpRequestTask(serverAdress);
                requestTask.execute(ledStatus);
            }
        });
    }

    //AsyncTask
    public class HttpRequestTask extends AsyncTask<String, Void, String> {
        private String serverAdress;

        public HttpRequestTask(String serverAdress) {
            this.serverAdress = serverAdress;
        }

        @Override
        protected String doInBackground(String... params) {
            String val = params[0];
            final String url = "http://"+serverAdress + "/" + val;

            //okHttp 라이브러리를 사용한다.
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                //Log.d(TAG, response.body().string());
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

    }

}