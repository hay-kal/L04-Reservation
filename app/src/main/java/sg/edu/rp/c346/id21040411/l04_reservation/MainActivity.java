package sg.edu.rp.c346.id21040411.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText Name;
    EditText Contact;
    EditText Pax;
    CheckBox Smoke;
    DatePicker dp;
    TimePicker tp;
    Button Reserve;
    Button Reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.etName);
        Contact = findViewById(R.id.etContact);
        Pax = findViewById(R.id.etPax);
        Smoke = findViewById(R.id.cbSmoke);
        dp = findViewById(R.id.dpReserve);
        tp = findViewById(R.id.tpReserve);
        Reserve = findViewById(R.id.btnReserve);
        Reset = findViewById(R.id.btnReset);
        tp.setIs24HourView(true);

        // Reserve Button
        Reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ReserveName = Name.getText().toString();
                String ReservePax = Pax.getText().toString();
                String ReserveContact = Contact.getText().toString();
                int hour = tp.getCurrentHour();
                int min = tp.getCurrentMinute();
                int year = dp.getYear();
                int month = dp.getMonth() + 1;
                int day = dp.getDayOfMonth();
                String msg = "";
                String smokeCheck = "";

                if(Smoke.isChecked()) {
                    smokeCheck = "Smoking Area";
                } else {
                    smokeCheck = "Non-Smoking Area";
                }

                // Check if all fields are filled
                if (Name.getText().toString().trim().length() != 0 && Pax.getText().toString().trim().length() != 0 && Contact.getText().toString().trim().length() != 0 ) {
                    msg = smokeCheck +" Table for " + ReservePax + " Pax on " + day + "/" + month + "/"  + year + " at " +  String.format("%01d",hour) + ":" + String.format("%01d", min)
                            + " hours. Reserved under the name and contact: " + ReserveName + " " + ReserveContact;
                } else {
                    msg = "Error kindly fill up all fields.";
                }



                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
            });

        // Reset Button
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name.setText("");
                Contact.setText("");
                Pax.setText("");
                Smoke.setChecked(false);
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
                dp.updateDate(2020, 5, 1);
            }
        });

        // Scenario 1
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker v, int hourOfDay, int minute) {
                String msg = "Restaurant is only open from 8 AM to 8:59 PM";
                if (hourOfDay < 8) {
                    tp.setCurrentHour(8);

                } else if (hourOfDay > 20) {
                    tp.setCurrentHour(20);
                    tp.setCurrentMinute(59);

                }
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });


            }

    }

