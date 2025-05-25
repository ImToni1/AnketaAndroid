package com.example.anketa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityCheckRadio extends AppCompatActivity {
    CheckBox chkPutovanja;
    CheckBox chkSport;
    CheckBox chkMuzika;
    RadioButton radioMladji;
    RadioButton radioSrednji;
    RadioButton radioStariji;

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_check_radio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        pref=getSharedPreferences(getLocalClassName(),MODE_PRIVATE);

        chkPutovanja=findViewById(R.id.chkPutovanja);
        chkSport=findViewById(R.id.chkSport);
        chkMuzika=findViewById(R.id.chkMuzika);

        radioMladji=findViewById(R.id.radioMladji);
        radioSrednji=findViewById(R.id.radioSrednji);
        radioStariji=findViewById(R.id.radioStariji);

        String opcije=pref.getString("OPCIJE","SREDNJI");
        if (opcije.equals("SREDNJI")) radioSrednji.setChecked(true);
        if (opcije.equals("STARIJI")) radioStariji.setChecked(true);
        if (opcije.equals("MLADJI")) radioMladji.setChecked(true);

        if (!radioMladji.isChecked() && !radioSrednji.isChecked() && !radioStariji.isChecked()){
            radioSrednji.setChecked(true);
        }

        boolean putovanja=pref.getBoolean("PUTOVANJA",false);
        boolean sport=pref.getBoolean("SPORT",false);
        boolean muzika=pref.getBoolean("MUZIKA",false);
        chkPutovanja.setChecked(putovanja);
        chkSport.setChecked(sport);
        chkMuzika.setChecked(muzika);





        Button Nazad=findViewById(R.id.btnNazad);
        Button Naredni=findViewById(R.id.btnNaredni);
        Nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCheckRadio.this.finish();
            }
        });

        Naredni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ActivityCheckRadio.this, ActivityDateTime.class);
                Bundle primljeno=getIntent().getExtras();
                i.putExtras(primljeno);
                boolean putovanja=chkPutovanja.isChecked();
                i.putExtra("PUTOVANJA",putovanja);
                boolean sport=chkSport.isChecked();
                i.putExtra("SPORT",sport);
                boolean muzika=chkSport.isChecked();
                i.putExtra("MUZIKA",muzika);

                boolean mladji=radioMladji.isChecked();
                boolean srednji=radioSrednji.isChecked();
                boolean stariji=radioStariji.isChecked();

                String opcije="MLADJI";
                if(srednji) opcije="SREDNJI";
                if(stariji) opcije="STARIJI";
                i.putExtra("OPCIJE",opcije);
                ActivityCheckRadio.this.startActivity(i);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        boolean putovanja=chkPutovanja.isChecked();
        boolean sport=chkSport.isChecked();
        boolean muzika=chkSport.isChecked();

        boolean mladji=radioMladji.isChecked();
        boolean srednji=radioSrednji.isChecked();
        boolean stariji=radioStariji.isChecked();

        SharedPreferences.Editor edit=pref.edit();
        edit.putBoolean("PUTOVANJA",putovanja);
        edit.putBoolean("SPORT",sport);
        edit.putBoolean("MUZIKA",muzika);

        String opcije="MLADJI";
        if(srednji) opcije="SREDNJI";
        if(stariji) opcije="STARIJI";
        edit.putString("OPCIJE",opcije);
        edit.commit();


    }
}