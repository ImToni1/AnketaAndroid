package com.example.anketa;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;

public class ActivityDateTime extends AppCompatActivity {
    EditText editDatum, editVrijeme;

    LocalDate odabraniDatum = null;
    LocalTime odabranoVrijeme = null;
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activity4_date);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editDatum = findViewById(R.id.editDatum);
        editVrijeme = findViewById(R.id.editVrijeme);
        editVrijeme.setEnabled(false);

        prefs = getSharedPreferences(getLocalClassName(), MODE_PRIVATE);
        long dl = prefs.getLong("DATUM", -1);
        if (dl != -1) {
            odabraniDatum = LocalDate.ofEpochDay(dl);
            DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
            String datum = dtf.format(odabraniDatum);
            editDatum.setText(datum);
        }
        if (odabraniDatum == null) {
            editDatum.setText("");
        }

        int vl = prefs.getInt("VRIJEME", -1);
        if (vl != -1) {
            odabranoVrijeme = LocalTime.ofSecondOfDay(vl);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("KK:mm a");
            String vrijeme = dtf.format(odabranoVrijeme);
            editVrijeme.setText(vrijeme);
        }
        if (odabranoVrijeme == null) {
            editVrijeme.setText("");
        }
        Button btnVrijeme = findViewById(R.id.btnVrijeme);
        btnVrijeme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalTime vrijeme = odabranoVrijeme;
                if (vrijeme == null) vrijeme = LocalTime.now();

                int HH = vrijeme.getHour();
                int MM = vrijeme.getMinute();

                TimePickerDialog.OnTimeSetListener vrijemeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        odabranoVrijeme = LocalTime.of(hourOfDay, minute);
                        //DateTimeFormatter dtf=DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("KK:mm a");
                        String vrijeme = dtf.format(odabranoVrijeme);
                        editVrijeme.setText(vrijeme);
                    }
                };
                TimePickerDialog tpd = new TimePickerDialog(ActivityDateTime.this, vrijemeListener, HH, MM, true);
                tpd.show();
            }
        });
        Button btnDatum = findViewById(R.id.btnDatum);
        btnDatum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(ActivityDateTime.this);
                long pocetak = Calendar.getInstance().getTimeInMillis();
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 10);
                long kraj = cal.getTimeInMillis();
                DatePicker datePicker = dialog.getDatePicker();
                datePicker.setMinDate(pocetak);
                datePicker.setMaxDate(kraj);
                if (odabraniDatum != null) {
                    datePicker.updateDate(
                            odabraniDatum.getYear(),
                            odabraniDatum.getMonthValue() - 1,
                            odabraniDatum.getDayOfMonth()


                    );
                }

                DatePickerDialog.OnDateSetListener datelistener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        odabraniDatum = LocalDate.of(year, month + 1, dayOfMonth);
                        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
                        String datum = dtf.format(odabraniDatum);
                        editDatum.setText(datum);
                    }
                };
                dialog.setOnDateSetListener(datelistener);
                dialog.show();
            }
        });


        Button Nazad = findViewById(R.id.btnNazad);
        Button Naredni = findViewById(R.id.btnNaredni);
        Nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityDateTime.this.finish();
            }
        });

        Naredni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent original = getIntent();
                Bundle primljeno = original.getExtras();

                Intent intent = new Intent(ActivityDateTime.this, ActivitySpinner.class);
                intent.putExtras(primljeno);
                intent.putExtra("DATUM", odabraniDatum.toEpochDay());
                intent.putExtra("VRIJEME", odabranoVrijeme.toSecondOfDay());
                ActivityDateTime.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor=prefs.edit();
        if (odabraniDatum!=null){
            editor.putLong("DATUM",odabraniDatum.toEpochDay());
        }
        if(odabranoVrijeme!=null){
            editor.putInt("VRIJEME",odabranoVrijeme.toSecondOfDay());
        }
        editor.commit();
    }
}




