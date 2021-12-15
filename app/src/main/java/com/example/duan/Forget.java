package com.example.duan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Forget extends AppCompatActivity {
    TextInputEditText txtUsername, txtEmail;
    Button btnRemember, btnDark, btnLight, btnDefault;
    ProgressBar prBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        addControl();
        addEven();

    }

    private void addControl() {
        txtUsername = (TextInputEditText) findViewById(R.id.txtUsername);
        txtEmail = (TextInputEditText) findViewById(R.id.txtEmail);
        btnRemember = (Button) findViewById(R.id.btnRemember);
        prBar = (ProgressBar) findViewById(R.id.prBar);
        btnDark = (Button) findViewById(R.id.btnDark);
        btnLight = (Button) findViewById(R.id.btnLight);
        btnDefault = (Button) findViewById(R.id.btnDefault);
    }

    private void addEven() {


        btnDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            }
        });
        btnDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        });
        btnLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        btnRemember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username, email;

                username = String.valueOf(txtUsername.getText());
                email = String.valueOf(txtEmail.getText());


                if(!username.equals("") && !email.equals("")) {
                    //Start ProgressBar first (Set visibility VISIBLE)
                    prBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "email";
                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = username;
                            data[1] = email;

                            PutData putData = new PutData("http://192.168.1.11/postPhp/forget.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {

                                    prBar.setVisibility(View.GONE);
                                    String result = putData.getResult();

                                    if(result.equals("Forget Success"))
                                    {
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                                    }


                                    Log.i("PutData", result);
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(),"All Field required",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
