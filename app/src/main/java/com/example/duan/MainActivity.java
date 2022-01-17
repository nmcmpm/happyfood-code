package com.example.duan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.duan.adapter.FoodAdapter;


public class MainActivity extends AppCompatActivity {


    ListView lvBai;
    FoodAdapter adapterBai;
    ArrayList<Food> dsBai;
    ArrayList<Food> dsBaiYeuThich;
    TabHost tabHost;
    ListView lvBaiYeuThich;
    FoodAdapter adapterBaiYeuThich;
    int STT;
    int like;
    int[] img = {R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvens();

    }

    private void addEvens() {
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tabId.equalsIgnoreCase("tab2")){
                    xuLyHienThiMonYeuThich();
                }
                else if(tabId.equalsIgnoreCase("tab1")){
                    xuLyHienThiMon();
                }
            }
        });



        Intent i1 = this.getIntent();

//        Intent i =  new Intent(getApplicationContext(),MainActivity2.class);
//
        if (i1 != null){
            ArrayList dsBai1 = i1.getParcelableArrayListExtra("dsBai");
            if (dsBai1!=null){
                dsBai.clear();

                for (Food e: (ArrayList<Food>) dsBai1) {
                    if (e.isLike()){
                        dsBai.add(e);
                    }
                }
                adapterBai.notifyDataSetChanged();
                System.out.println("STT: "+dsBai1+"lk: "+like);
            }

        }
//        lvBai.setClickable(true);
        lvBai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,
                        "Item in position " + position + " clicked", Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this,MainActivity2.class);
                i.putExtra("Mon", dsBai.get(position).getTenMon());
                i.putExtra("DC", dsBai.get(position).getDiaChi());
                i.putExtra("ND", dsBai.get(position).getNoiDung());
                i.putExtra("STT", String.valueOf(position));
                i.putExtra("NH", dsBai.get(position).getTenNH());
                i.putExtra("IMG",img[position%5]);
                i.putExtra("dsBai", dsBai);
                i.putExtra("dsBai1", dsBaiYeuThich);
                startActivity(i);
            }
        });


        lvBaiYeuThich.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,
                        "Item in position " + position + " clicked", Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this,MainActivity2.class);
                i.putExtra("Mon", dsBai.get(position).getTenMon());
                i.putExtra("DC", dsBai.get(position).getDiaChi());
                i.putExtra("ND", dsBai.get(position).getNoiDung());
                i.putExtra("STT", String.valueOf(position));
                i.putExtra("NH", dsBai.get(position).getTenNH());
                i.putExtra("IMG",img[position%5]);
                i.putExtra("dsBai", dsBai);
                i.putExtra("dsBai1", dsBaiYeuThich);
                startActivity(i);
            }
        });





    }


    private void xuLyHienThiMon() {

    }

    private void xuLyHienThiMonYeuThich() {

        dsBaiYeuThich.clear();

        for (Food e:dsBai) {
            if (e.isLike()){
                dsBaiYeuThich.add(e);
            }
        }
        adapterBaiYeuThich.notifyDataSetChanged();
    }

    private void addControls() {

        tabHost = findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("",getResources().getDrawable(R.drawable.ic_home));
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("tab2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("",getResources().getDrawable(R.drawable.ic_favorite));
        tabHost.addTab(tab2);

        lvBai = findViewById(R.id.lvBai);
        dsBai = new ArrayList<Food>();
        adapterBai = new FoodAdapter(this,R.layout.item,dsBai);
        lvBai.setAdapter(adapterBai);

        lvBaiYeuThich = findViewById(R.id.lvBaiYeuThich);
        dsBaiYeuThich = new ArrayList<Food>();
        adapterBaiYeuThich = new FoodAdapter(this,R.layout.item,dsBaiYeuThich);
        lvBaiYeuThich.setAdapter(adapterBaiYeuThich);



        getJSON("http://192.168.1.13/postPhp/getdata.php");

    }


    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String owner = obj.getString("owner");
            String food = obj.getString("food");
            String description = obj.getString("description");
            String id = obj.getString("id");
            String address = obj.getString("address");
            dsBai.add(new Food(owner,food,address,description,false, Integer.parseInt(id)));
        }

        adapterBai.notifyDataSetChanged();

    }
}

