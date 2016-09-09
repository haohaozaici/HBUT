package com.example.hao.hbut;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button logon;
    private TextView ioeText;
    private Bitmap bitmap;
    private ImageView code;
    private final OkHttpClient client = new OkHttpClient();

    private Handler myHandler;

    private final static String HBUT = "http://202.114.176.37/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logon = (Button) findViewById(R.id.logon);
        logon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    run();
                } catch (Exception ioe) {
                    return;
                }

            }
        });

        ioeText = (TextView) findViewById(R.id.ioe);
        code = (ImageView) findViewById(R.id.code);

        myHandler = new FHandler();

    }

    public void run() throws Exception {
        final Request request = new Request.Builder()
                .url("http://run.hbut.edu.cn/Account/GetValidateCode")
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    Log.e("response11111", responseHeaders.name(i) + ": " + responseHeaders.value(i));

                }
                Log.e("request", request.toString());
                Log.e("response", response.toString());
//                Log.e("response", response.body().string());

                bitmap = BitmapFactory.decodeStream(response.body().byteStream());

                Message msg = Message.obtain();
                msg.what = 1;//用于消息的标识
                msg.obj = bitmap;//用于消息的存放
                //传入主线程的Handler并其MessageQueue发送消息
                myHandler.sendMessage(msg);

//                isRemember=1&Role=Student&UserName=1310200128&Password=1310200128&ValidateCode=0664
            }
        });
    }

    class FHandler extends Handler{
        @Override
        public void handleMessage(Message msg){
            if (msg.what == 1){
                code.setImageBitmap(bitmap);
            }

        }
    }

}
