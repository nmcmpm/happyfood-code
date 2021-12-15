package com.example.duan.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.duan.Food;
import com.example.duan.R;

import java.util.List;

public class FoodAdapter extends ArrayAdapter<Food> {
    Activity context;
    int resource;
    List<Food> objects;
    int i = 0;
    public FoodAdapter(Activity context, int resource, List<Food> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row =  inflater.inflate(this.resource,null);

        TextView txtSTT = row.findViewById(R.id.txtSTT);
        TextView txtQuan = row.findViewById(R.id.txtQuan);
        TextView txtMon =  row.findViewById(R.id.txtMonDetail);
        TextView txtDiaChi = row.findViewById(R.id.txtDiaChi);
        TextView txtDanhGia = row.findViewById(R.id.txtDanhGia);
//        ImageButton imgLike = row.findViewById(R.id.imgLike);
//        ImageButton imgDislike = row.findViewById(R.id.imgDislike);

        Food food = this.objects.get(position);

        txtDanhGia.setText(food.getNoiDung());
        txtDiaChi.setText(food.getDiaChi());
        txtMon.setText(food.getTenMon());
        txtSTT.setText(""+position);
        txtQuan.setText(food.getTenNH());

//        if (food.isLike()){
//            imgLike.setVisibility(View.INVISIBLE);
//            imgDislike.setVisibility(View.VISIBLE);
//        }
//        imgLike.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                xuLyThich(food);
//            }
//        });
//        imgDislike.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                xuLyKhongThich(food);
//            }
//        });
        return row;
    }

    private void xuLyKhongThich(Food food) {
        food.setLike(false);
    }

    private void xuLyThich(Food food) {
        food.setLike(true);
    }
}
