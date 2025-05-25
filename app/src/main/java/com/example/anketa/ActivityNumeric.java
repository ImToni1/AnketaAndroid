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

public class ActivityNumeric extends AppCompatActivity {
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_numeric);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText editGodiste=findViewById(R.id.editGodiste);
        EditText editVisina=findViewById(R.id.editVisina);
        pref=getSharedPreferences(getLocalClassName(),MODE_PRIVATE);
        long godiste=pref.getLong("GODISTE",0);
        float visina=pref.getFloat("VISINA",0.0f);

        editGodiste.setText(Long.toString(godiste));
        editVisina.setText(Float.toString(visina));

        Button Nazad=findViewById(R.id.btnNazad);
        Button Naredni=findViewById(R.id.btnNaredni);
        Nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityNumeric.this.finish();
            }
        });

        Naredni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ActivityNumeric.this,ActivityCheckRadio.class);
                Bundle primljeno=getIntent().getExtras();

                i.putExtras(primljeno);
                EditText editGodiste=findViewById(R.id.editGodiste);
                EditText editVisina=findViewById(R.id.editVisina);
                String sGodiste = editGodiste.getText().toString();
                String sVisina = editVisina.getText().toString();

                long godiste=Long.parseLong(sGodiste);
                float visina=Float.parseFloat(sVisina);

                i.putExtra("GODISTE",godiste);
                i.putExtra("VISINA",visina);

                ActivityNumeric.this.startActivity(i);

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        EditText editGodiste=findViewById(R.id.editGodiste);
        EditText editVisina=findViewById(R.id.editVisina);

        String sGodiste = editGodiste.getText().toString();
        String sVisina = editVisina.getText().toString();

        long godiste=Long.parseLong(sGodiste);
        float visina=Float.parseFloat(sVisina);

        SharedPreferences.Editor edit=pref.edit();
        edit.putLong("GODISTE",godiste);
        edit.putFloat("VISINA",visina);
        edit.commit();
    }
}