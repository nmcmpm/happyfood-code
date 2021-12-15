package com.example.duan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {


    TextView txtMon,txtDC,txtND,txtNH,txtSTT;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtMon = findViewById(R.id.txtMonDetail);
        txtDC = findViewById(R.id.txtDiachiaDetail);
        txtND = findViewById(R.id.txtDescriptionDetai);
        txtNH = findViewById(R.id.txtQuanDetail);
        img = findViewById(R.id.imgDetail);
        Intent intent = this.getIntent();

        if (intent != null){

            System.out.println(intent.getStringExtra("Mon"));
            String Mon = intent.getStringExtra("Mon");
            String DC = intent.getStringExtra("DC");
            String NH = intent.getStringExtra("NH");
            String ND = intent.getStringExtra("ND");
            int imageid = intent.getIntExtra("IMG",R.drawable.img1);
//
//            i.putExtra("Mon", dsBai.get(position).getTenMon());
//            i.putExtra("DC", dsBai.get(position).getDiaChi());
//            i.putExtra("ND", dsBai.get(position).getNoiDung());
//            i.putExtra("STT", dsBai.get(position).getStt());
//            i.putExtra("NH", dsBai.get(position).getTenNH());
//            i.putExtra("IMG",img[position%5]);

            txtMon.setText(Mon);
            txtDC.setText(DC);
            txtNH.setText(NH);
            txtND.setText(ND);
            txtMon.setText(Mon);
            img.setImageResource(imageid);
            startActivity(intent);
        }




    }
}