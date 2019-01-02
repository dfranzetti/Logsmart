package it.uninsubria.pdm.logsmart;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SecondActivity extends AppCompatActivity {
    private Spinner Orario;
    private Button Prenota;
    private TextView textView;
    private EditText nameEditText;
    CalendarView CalendarView;
    TextView myDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Orario = (Spinner) findViewById(R.id.spinner);
        Prenota = (Button) findViewById(R.id.button);
        CalendarView = (CalendarView) findViewById(R.id.calendarView);
        myDate = (TextView) findViewById(R.id.textView);



        CalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView CalendarView, int i, int i1, int i2) {
                String date =  i2 + "/" + (i1+1) + "/" + i;
                myDate.setText(date);
            }
        });


        String [] orari = {"8 / 8.30","9 / 9.30","9.30 / 10","10 / 10.30","10.30 / 11","11 / 11.30","11.30 / 12","14 / 14.30","14.30 / 15","15 / 15.30","15.30 / 16", "16 / 16.30", "16.30 / 17"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,orari);
        Orario.setAdapter(adapter);
    }

    public void Click (View view) {

        Intent i = new Intent(SecondActivity.this, Logout.class);
        Toast.makeText(SecondActivity.this, "Prenotazione Effettuata", Toast.LENGTH_SHORT).show();

        startActivity(i);
        }

    }

