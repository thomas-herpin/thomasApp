package com.example.aplikasithomas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class profilActivity extends AppCompatActivity {

    EditText edtNama, edtStudentID, edtFakultas, edtPassword;
    Button btnSimpan;
    RadioGroup rdgProdi;
    RadioButton rdbTI, rdbSI, rdbHukum;
    CheckBox cbxSyarat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSimpan = findViewById(R.id.btnSimpan);
        edtNama = findViewById(R.id.edtNama);
        edtStudentID = findViewById(R.id.edtStudentID);
        edtFakultas = findViewById(R.id.edtFakultas);
        edtPassword = findViewById(R.id.edtPassword);
        cbxSyarat = findViewById(R.id.cbxSyarat);
        rdgProdi = findViewById(R.id.rdgProdi);
        rdbTI = findViewById(R.id.rdbTI);
        rdbSI = findViewById(R.id.rdbSI);
        rdbHukum = findViewById(R.id.rdbHukum);

        edtNama.setText(getIntent().getStringExtra("username"));
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                simpan();
            }
        });

        cbxSyarat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbxSyarat.isChecked()) {
                    Toast.makeText(profilActivity.this, "Anda menyetujui syarat dan ketentuan", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public boolean isValidated() {
        String prodi = "";
        String fakultas = edtFakultas.getText().toString().trim().toLowerCase();
        boolean isFakultasValid = false;

        if (rdbTI.isChecked()) prodi = rdbTI.getText().toString();
        else if (rdbSI.isChecked()) prodi = rdbSI.getText().toString();
        else if (rdbHukum.isChecked()) prodi = rdbHukum.getText().toString();

        if((prodi.equals("Teknologi Informasi") || prodi.equals("Sistem Informasi")) && fakultas.contains("teknologi informasi")){
            isFakultasValid = true;
        }else if (prodi.equals("Hukum") && fakultas.contains("hukum")){
            isFakultasValid = true;
        }

        if(!isFakultasValid){
            String expectedFakultas = "";
            if (prodi.equals("Teknologi Informasi") || prodi.equals("Sistem Informasi")) {
                expectedFakultas = "Teknologi Informasi";
            } else if (prodi.equals("Hukum")) {
                expectedFakultas = "Hukum";
            }

            Toast.makeText(this, "Fakultas tidak sesuai, sebaiknya isi dengan " + expectedFakultas + "!", Toast.LENGTH_LONG).show();
            return false;
        }

        if (edtNama.getText().toString().equals("")) {
            edtNama.setError("Nama wajib di isi");
            return false;
        } else if (edtStudentID.getText().toString().equals("")) {
            edtStudentID.setError("Student ID wajib di isi");
            return false;
        } else if (edtPassword.getText().toString().equals("")) {
            edtPassword.setError("Password wajib di isi");
            return false;
        }else if (!cbxSyarat.isChecked()) {
            Toast.makeText(this, "Anda belum menyetujui syarat dan ketentuan", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public void simpan() {
        if (isValidated()) {
            Toast.makeText(this, "Data disimpan", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, dashboardActivity.class);
            startActivity(intent);
        }
    }

    public void bersihkan() {
        edtNama.setText("");
        edtStudentID.setText("");
        edtFakultas.setText("");
        rdgProdi.clearCheck();
        edtPassword.setText("");
        cbxSyarat.setChecked(false);
    }

//    btnBersih.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            rdgProdi.clearCheck();
//            edtFakultas.setText("");
//            edtNama.setText("");
//            edtStudentID.setText("");
//            edtPassword.setText("");
//        }
//    });

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.mSimpan){
            simpan();
            return true;
        }else if(item.getItemId()==R.id.mBersihkan){
            bersihkan();
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}