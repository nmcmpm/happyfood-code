package com.example.duan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class about_us extends AppCompatActivity {

    ImageView img,img1,img2,img3;
    TextView txtTen,txtTen1,txtTen2,txtTen3;
    TextView txtLop,txtLop1,txtLop2,txtLop3;
    TextView txtMon,txtMon1,txtMon2,txtMon3;
    TextView txtRole,txtRole1,txtRole2,txtRole3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        addcontrol();
        addEvent();

    }

    private void addEvent() {
        img.setImageResource(R.drawable.lan);
        txtTen.setText("Đoàn Nguyễn Hương Lan");
        txtLop.setText("19CLC6");
        txtMon.setText("Nhập môn công nghệ phần mềm");
        txtRole.setText("Leader");

        img1.setImageResource(R.drawable.anh);
        txtTen1.setText("Phạm Ngọc Anh");
        txtLop1.setText("19CLC6");
        txtMon1.setText("Nhập môn công nghệ phần mềm");
        txtRole1.setText("Coder");

        img2.setImageResource(R.drawable.son);
        txtTen2.setText("Nguyễn Sơn");
        txtLop2.setText("19CLC6");
        txtMon2.setText("Nhập môn công nghệ phần mềm");
        txtRole2.setText("Coder");

        img3.setImageResource(R.drawable.quang);
        txtTen3.setText("Hoàng Đức Quang");
        txtLop3.setText("19CLC6");
        txtMon3.setText("Nhập môn công nghệ phần mềm");
        txtRole3.setText("Coder");




    }

    private void addcontrol() {
        img     = findViewById(R.id.img);
        txtTen = findViewById(R.id.txtTen);
        txtLop = findViewById(R.id.txtLop);
        txtMon = findViewById(R.id.txtMon);
        txtRole = findViewById(R.id.txtRole);

        img1  = findViewById(R.id.img1);
        txtTen1 = findViewById(R.id.txtTen1);
        txtLop1 = findViewById(R.id.txtLop1);
        txtMon1 = findViewById(R.id.txtMon1);
        txtRole1 = findViewById(R.id.txtRole1);

        img2  = findViewById(R.id.img2);
        txtTen2 = findViewById(R.id.txtTen2);
        txtLop2 = findViewById(R.id.txtLop2);
        txtMon2 = findViewById(R.id.txtMon2);
        txtRole2 = findViewById(R.id.txtRole2);

        img3  = findViewById(R.id.img3);
        txtTen3 = findViewById(R.id.txtTen3);
        txtLop3 = findViewById(R.id.txtLop3);
        txtMon3 = findViewById(R.id.txtMon3);
        txtRole3 = findViewById(R.id.txtRole3);
    }
}