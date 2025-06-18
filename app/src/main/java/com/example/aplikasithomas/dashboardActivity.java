package com.example.aplikasithomas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class dashboardActivity extends AppCompatActivity {
    TextView txvWelcome;
    LinearLayout llyProfil, llyJadwal, llyLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txvWelcome = findViewById(R.id.txvWelcome);
        llyProfil = findViewById(R.id.llyProfil);
        llyJadwal = findViewById(R.id.llyJadwal);
        llyLogout = findViewById(R.id.llyLogout);

        String username = getIntent().getStringExtra("username");
        if (!username.isEmpty()) {
            txvWelcome.setText("Selamat datang, " + username + "!");
        } else {
            txvWelcome.setText("Selamat datang!");
        }

        llyProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toProfil();
            }
        });

        llyJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(dashboardActivity.this, "Fitur dalam proses implementasi", Toast.LENGTH_SHORT).show();
            }
        });

        llyLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLogout();
            }
        });
    }

    public void toProfil(){
        Intent intent = new Intent(this,profilActivity.class);
        startActivity(intent);
    }

    public void toLogout() {
        Intent intent = new Intent(dashboardActivity.this, loginActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}