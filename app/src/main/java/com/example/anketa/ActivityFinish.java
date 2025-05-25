package com.example.anketa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class ActivityFinish extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_finish);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button Nazad = findViewById(R.id.btnNazad);
        Button Zavrsi = findViewById(R.id.btnZavrsi);
        Nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Zavrsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
        Intent originalniIntent=getIntent();
        Bundle primljeno=originalniIntent.getExtras();

        TextView txtIme=findViewById(R.id.txtIme);
        String ime=primljeno.getString("IME");
        txtIme.setText(ime);

        TextView txtPrezime=findViewById(R.id.txtPrezime);
        String prezime=primljeno.getString("PREZIME");
        txtPrezime.setText(prezime);

        TextView txtEmail=findViewById(R.id.txtEmail);
        String email=primljeno.getString("EMAIL");
        txtEmail.setText(email);

        TextView txtLozinka=findViewById(R.id.txtLozinka);
        String lozinka=primljeno.getString("LOZINKA");
        txtLozinka.setText(lozinka);

        TextView txtGodiste=findViewById(R.id.txtGodiste);
        long godiste=primljeno.getLong("GODISTE",-1);
        if (godiste==-1){
            txtGodiste.setText("nije upisano");
        }else{
            txtGodiste.setText(Long.toString(godiste));
        }

        TextView txtVisina=findViewById(R.id.txtVisina);
        float visina=primljeno.getFloat("VISINA",-1);
        if (visina==-1){
            txtVisina.setText("nije upisano");
        }else{
            txtVisina.setText(Float.toString(visina));
        }

        TextView txtAktivnosti=findViewById(R.id.txtAktivnosti);
        boolean putovanja= primljeno.getBoolean("PUTOVANJA");
        boolean sport=primljeno.getBoolean("SPORT");
        boolean muzika= primljeno.getBoolean("MUZIKA");

        String aktivnosti="";
        if (putovanja){
            aktivnosti=aktivnosti+"PUTOVANJA ";
        }
        if (sport){
            aktivnosti=aktivnosti+"SPORT ";
        }
        if (muzika){
            aktivnosti=aktivnosti+"MUZIKA ";
        }
        txtAktivnosti.setText(aktivnosti);

        TextView txtOpcije=findViewById(R.id.txtOpcije);
        String opcije=primljeno.getString("OPCIJE",null);
        if (opcije!=null){
            txtOpcije.setText(opcije);
        }else{
            txtOpcije.setText("nije odabrano");
        }


        TextView txtDatum=findViewById(R.id.txtDatum);
        long datum=primljeno.getLong("DATUM");
        LocalDate ld=LocalDate.ofEpochDay(datum);
        DateTimeFormatter dtf=DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        String dt=dtf.format(ld);
        txtDatum.setText(dt);

        TextView txtVrijeme=findViewById(R.id.txtVrijeme);
        int vrijeme=primljeno.getInt("VRIJEME");
        LocalTime lt=LocalTime.ofSecondOfDay(vrijeme);
        DateTimeFormatter dtf2=DateTimeFormatter.ofPattern("KK:mm a");
        String lv=dtf2.format(lt);
        txtVrijeme.setText(lv);

        TextView txtZemlja=findViewById(R.id.txtZemlja);
        String zemlja=primljeno.getString("ZEMLJA");
        txtZemlja.setText(zemlja);
    }
}
