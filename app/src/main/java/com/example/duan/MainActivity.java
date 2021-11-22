package com.example.duan;

import androidx.appcompat.app.AppCompatActivity;
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
import java.net.URI;
import java.util.ArrayList;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.duan.adapter.FoodAdapter;


public class MainActivity extends AppCompatActivity {
    ListView lvBai;
    FoodAdapter adapterBai;
    ArrayList<Food> dsBai;
    TabHost tabHost;

    ListView lvBaiYeuThich;
    FoodAdapter adapterBaiYeuThich;
    ArrayList<Food> dsBaiYeuThich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dsBai = new ArrayList<>();


        addControls();
        addEvens();
        new Connection().execute();


    }

    private void addEvens() {

    }

    private void addControls() {

        tabHost = findViewById(R.id.tabHost);
        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("",getResources().getDrawable(R.drawable.ic_home));
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("",getResources().getDrawable(R.drawable.ic_favorite));
        tabHost.addTab(tab2);


        lvBai = findViewById(R.id.lvBai);
        adapterBai = new FoodAdapter(MainActivity.this,R.layout.item,dsBai);
        lvBai.setAdapter(adapterBai);

        lvBaiYeuThich = findViewById(R.id.lvBaiYeuThich);
        dsBaiYeuThich = new ArrayList<>();
        adapterBaiYeuThich = new FoodAdapter(MainActivity.this,R.layout.item,dsBaiYeuThich);
        lvBaiYeuThich.setAdapter(adapterBaiYeuThich);

    }

    class Connection extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String result="";
            String host= "http://localhost/postPhp/getdata.php";
            try{
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(host));
                HttpResponse response= client.execute(request);
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer stringBuffer= new StringBuffer("");
                String line="";
                while((line=reader.readLine())!=null){
                    stringBuffer.append(line);
                    break;
                }
                reader.close();
                result=stringBuffer.toString();

            }
            catch (Exception e){
                return new String("There exception: "+e.getMessage());
            }


            return result;
        }
        @Override
        protected void onPostExecute(String result){
            try {

                JSONObject jsonResult= new JSONObject(result);
                int success =jsonResult.getInt("success");
                if(success==1){
                    //Toast.makeText(getApplicationContext(),"Oke, there are result",Toast.LENGTH_SHORT).show();
                    JSONArray cars = jsonResult.getJSONArray("cars");
                    for(int i=0;i<cars.length();i++){
                        JSONObject car= cars.getJSONObject(i);
                        int id = car.getInt("id");
                        String food = car.getString("food");
                        String address = car.getString("address");
                        String description = car.getString("description");
                        String username= car.getString("owner");
                        dsBai.add(new Food(username,food,address,description,false,id));
                    }
                    adapterBai.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getApplicationContext(),"No cars yet",Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }


}


