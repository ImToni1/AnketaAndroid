package com.example.anketa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ActivitySpinner extends AppCompatActivity {
    Spinner spinner;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_spinner);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        spinner=findViewById(R.id.spinner);
        List<String> popisDrzava=new ArrayList<String>();
        popisDrzava.add(getString(R.string.Croatia));
        popisDrzava.add(getString(R.string.Bosnia));

        ArrayAdapter<String> adapterZemlje=new ArrayAdapter<>(
                ActivitySpinner.this,
                android.R.layout.simple_spinner_item,
                popisDrzava
        );
        spinner.setAdapter(adapterZemlje);
        pref=getSharedPreferences(getLocalClassName(),MODE_PRIVATE);
        String odabranaZemlja=pref.getString("ZEMLJA",null);
        if (odabranaZemlja!=null){
            int pos=adapterZemlje.getPosition(odabranaZemlja);
            spinner.setSelection(pos);
        }

        Button Nazad=findViewById(R.id.btnNazad);
        Button Naredni=findViewById(R.id.btnNaredni);
        Nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Naredni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent original=getIntent();
                Bundle primljeno=original.getExtras();

                Intent intent=new Intent(ActivitySpinner.this,ActivityFinish.class);
                intent.putExtras(primljeno);
                String odabranaZemlja=spinner.getSelectedItem().toString();
                intent.putExtra("ZEMLJA",odabranaZemlja);
                ActivitySpinner.this.startActivity(intent);
            }
        });
    }
}