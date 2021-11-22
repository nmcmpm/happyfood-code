package com.example.duan.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan.Food;
import com.example.duan.R;

import java.util.List;

public class FoodAdapter extends ArrayAdapter<Food> {
    Activity context;
    int resource;
    List<Food> objects;
    int i = 0;
    public FoodAdapter(@NonNull Activity context, int resource, @NonNull List<Food> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row =  inflater.inflate(this.resource,null);

        TextView txtSTT = row.findViewById(R.id.txtSTT);
        TextView txtQuan = row.findViewById(R.id.txtQuan);
        TextView txtMon =  row.findViewById(R.id.txtMon);
        TextView txtDiaChi = row.findViewById(R.id.txtDiaChi);
        TextView txtDanhGia = row.findViewById(R.id.txtDanhGia);
        ImageButton imgLike = row.findViewById(R.id.imgLike);
        ImageButton imgDislike = row.findViewById(R.id.imgDislike);

        Food food = this.objects.get(position);

        txtDanhGia.setText(food.getNoiDung());
        txtDiaChi.setText(food.getDiaChi());
        txtMon.setText(food.getTenMon());
        txtSTT.setText(position);
        txtQuan.setText(food.getTenNH());

        return row;
    }
}
