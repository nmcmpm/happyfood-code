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

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class ChangePassword extends AppCompatActivity {
    TextInputEditText txtUsername, txtPassword, txtNewPassword, txtNewPasswordConfirm;
    Button btnChange, btnDark, btnLight, btnDefault;
    ProgressBar prBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        addControl();
        addEven();


    }

    private void addControl() {
        txtUsername = (TextInputEditText) findViewById(R.id.txtUsername);
        txtPassword = (TextInputEditText) findViewById(R.id.txtPassword);
        txtNewPassword = (TextInputEditText) findViewById(R.id.txtNewPassword);
        txtNewPasswordConfirm = (TextInputEditText) findViewById(R.id.txtNewPasswordConfirm);
        btnChange = (Button) findViewById(R.id.btnChange);
        prBar = (ProgressBar) findViewById(R.id.prBar);
        btnDark = (Button) findViewById(R.id.btnDark);
        btnLight = (Button) findViewById(R.id.btnLight);
        btnDefault = (Button) findViewById(R.id.btnDefault);
        prBar = (ProgressBar) findViewById(R.id.prBar);
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

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, password, newPassword, newPasswordConfirm;

                username = String.valueOf(txtUsername.getText());
                password = String.valueOf(txtPassword.getText());
                newPassword = String.valueOf(txtNewPassword.getText());
                newPasswordConfirm = String.valueOf(txtNewPasswordConfirm.getText());
                if(!username.equals("") && !password.equals("") && !newPassword.equals("") && !newPasswordConfirm.equals("") && newPassword.equals(newPasswordConfirm)) {
//                    Start ProgressBar first (Set visibility VISIBLE)
                    prBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[3];
                            field[0] = "username";
                            field[1] = "password";
                            field[2] = "newPassword";

                            //Creating array for data
                            String[] data = new String[3];
                            data[0] = username;
                            data[1] = password;
                            data[2] = newPassword;

                            PutData putData = new PutData("http://192.168.1.10/postPhp/change.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {

                                    prBar.setVisibility(View.GONE);
                                    String result = putData.getResult();

                                    if(result.equals("Changed Success"))
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
                    Toast.makeText(getApplicationContext(),"Pass wrong",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}




