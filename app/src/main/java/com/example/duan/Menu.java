package com.example.duan;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;

public class Menu extends AppCompatActivity {


    private ImageButton btnOut,btnHome,btnPost,btnChangePassword,btnAboutUs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        addControls();
        addEvents();

    }

    private void addEvents() {
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Post.class);
                startActivity(intent);
            }
        });
        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ChangePassword.class);
                startActivity(intent);
            }
        });

        btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AboutUs.class);
                startActivity(intent);
            }
        });
    }


    private void addControls() {

        btnHome = findViewById(R.id.btnHome);
        btnOut = findViewById(R.id.btnOut);
        btnPost = findViewById(R.id.btnPost);
        btnChangePassword = findViewById(R.id.btnChange);
        btnAboutUs= findViewById(R.id.btnAboutUs);
    }
}
