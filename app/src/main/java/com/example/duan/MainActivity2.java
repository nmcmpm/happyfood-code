package com.example.duan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {


    TextView txtMon,txtDC,txtND,txtNH,txtSTT;
    ImageView img;
    ImageButton imglike,imgdislike;
    String STT;
    ArrayList dsBai;
    ArrayList dsBaiYeuThich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtMon = findViewById(R.id.txtMonDetail);
        txtDC = findViewById(R.id.txtDiachiaDetail);
        txtND = findViewById(R.id.txtDescriptionDetai);
        txtNH = findViewById(R.id.txtQuanDetail);
        img = findViewById(R.id.imgDetail);
        imgdislike = findViewById(R.id.imgdislike);
        imglike = findViewById(R.id.imglike);

        Intent intent = this.getIntent();

        if (intent != null){

            String Mon = intent.getStringExtra("Mon");
            String DC = intent.getStringExtra("DC");
            String NH = intent.getStringExtra("NH");
            String ND = intent.getStringExtra("ND");
            STT = intent.getStringExtra("STT");
            int imageid = intent.getIntExtra("IMG",R.drawable.img1);
            dsBai = intent.getParcelableArrayListExtra("dsBai");
            dsBaiYeuThich = intent.getParcelableArrayListExtra("dsBai1");
            System.out.println("a "+dsBai);
            txtMon.setText(Mon);
            txtDC.setText(DC);
            txtNH.setText(NH);
            txtND.setText(ND);
            txtMon.setText(Mon);
            img.setImageResource(imageid);
        }

        imglike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer stt = Integer.parseInt(STT);
                Food e = (Food) dsBai.get(stt);
                e.setLike(true);
                Intent i = new Intent(MainActivity2.this,MainActivity.class);
                i.putExtra("dsBai",dsBai);
                Toast.makeText(getApplicationContext(),"You liked it",Toast.LENGTH_LONG).show();
                startActivity(i);
                System.out.println(dsBai);
            }
        });
//
        imgdislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer stt = Integer.parseInt(STT);
                Food e = (Food) dsBai.get(stt);
                e.setLike(false);
                Intent i = new Intent(MainActivity2.this,MainActivity.class);
                i.putExtra("dsBai",dsBai);
                Toast.makeText(getApplicationContext(),"You Disliked it",Toast.LENGTH_LONG).show();
                startActivity(i);
            }
        });

    }
}