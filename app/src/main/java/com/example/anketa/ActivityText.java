package com.example.anketa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class ActivityText extends AppCompatActivity {
     SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_text);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        pref=getSharedPreferences(getLocalClassName(),MODE_PRIVATE);
        EditText eime=findViewById(R.id.editIme);
        EditText eprezime=findViewById(R.id.editPrezime);
        EditText eemail=findViewById(R.id.editEmail);
        EditText elozinka=findViewById(R.id.editLozinka);
        String ime =pref.getString("IME","");
        String prezime= pref.getString("PREZIME","");
        String email=pref.getString("EMAIL","");
        String lozinka= pref.getString("LOZINKA","");
        eime.setText(ime);
        eprezime.setText(prezime);
        eemail.setText(email);
        elozinka.setText(lozinka);


        Button Nazad=findViewById(R.id.btnNazad);
        Button Naredni=findViewById(R.id.btnNaredni);
        Nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityText.this.finish();
            }
        });

        Naredni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i=new Intent(ActivityText.this,ActivityNumeric.class);
            Bundle paket=new Bundle();
                EditText eime = findViewById(R.id.editIme);
                EditText eprezime = findViewById(R.id.editPrezime);
                EditText eemail = findViewById(R.id.editEmail);
                EditText elozinka = findViewById(R.id.editLozinka);
                String ime = eime.getText().toString();
                String prezime = eprezime.getText().toString();
                String email = eemail.getText().toString();
                String lozinka = elozinka.getText().toString();
                paket.putString("IME",ime);
                paket.putString("PREZIME",prezime);
                paket.putString("EMAIL",email);
                paket.putString("LOZINKA",lozinka);
                i.putExtras(paket);

            ActivityText.this.startActivity(i);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = pref.edit();
        EditText eime = findViewById(R.id.editIme);
        EditText eprezime = findViewById(R.id.editPrezime);
        EditText eemail = findViewById(R.id.editEmail);
        EditText elozinka = findViewById(R.id.editLozinka);
        String ime = eime.getText().toString();
        String prezime = eprezime.getText().toString();
        String email = eemail.getText().toString();
        String lozinka = elozinka.getText().toString();

        editor.putString("IME", ime);
        editor.putString("PREZIME", prezime);
        editor.putString("EMAIL", email);
        editor.putString("LOZINKA", lozinka);
        editor.commit();


    }
    }