package sg.edu.rp.c346.id21007436.l04reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etPhone;
    EditText etPax;
    DatePicker dp;
    TimePicker tp;
    RadioGroup rgArea;
    RadioButton rbSmoke;
    RadioButton rbNonSmoke;
    Button btnReset;
    Button btnReserve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etPhone = findViewById(R.id.editTextPhone);
        etPax = findViewById(R.id.editTextPax);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        rgArea = findViewById(R.id.radioGroupArea);
        rbSmoke = findViewById(R.id.radioButtonSmoke);
        btnReset = findViewById(R.id.buttonReset);
        btnReserve = findViewById(R.id.buttonReserve);

        dp.updateDate(2023,6,1);
        tp.setCurrentHour(19);
        tp.setCurrentMinute(30);

        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = "";
                int month = 0;
                String time = "";
                String area = "";

                if (etName.getText().length() == 0){
                    Toast.makeText(MainActivity.this, "Please enter your name.",
                            Toast.LENGTH_SHORT).show();
                } else if (etPhone.getText().length() == 0) {
                    Toast.makeText(MainActivity.this, "Please enter your mobile number.",
                            Toast.LENGTH_SHORT).show();
                } else if (etPax.getText().length() == 0){
                    Toast.makeText(MainActivity.this, "Please enter number of pax.",
                            Toast.LENGTH_SHORT).show();
                } else{

                    month = dp.getMonth() + 1;
                    date = "Date: " + dp.getDayOfMonth() + "/" + month + "/" + dp.getYear();
                    time = tp.getCurrentHour() + ":" + tp.getCurrentMinute();
                    if (rbSmoke.isChecked()){
                        area = "Smoking area";
                    }else {
                        area = "Non-smoking area";
                    }
                    String text = "Table reserved\nName:"+etName.getText()+"\nMobile no.:" + etPhone.getText() +"\nPax:" + etPax.getText() + "\nDate:" + date + "\nTime:" + time +"\nin " + area;
               
                }
            }
        });



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dp.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    int yearNow = Calendar.getInstance().get(Calendar.YEAR);
                    int monthNow = Calendar.getInstance().get(Calendar.MONTH);
                    int dayNow = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

                    if (dp.getYear() < yearNow){
                        dp.updateDate(yearNow,monthNow,dayNow);
                        Toast.makeText(MainActivity.this, "Date is not available.",
                                Toast.LENGTH_SHORT).show();
                    }else if (dp.getMonth() < monthNow ){
                        dp.updateDate(yearNow,monthNow,dayNow);
                        Toast.makeText(MainActivity.this, "Date is not available.",
                                Toast.LENGTH_SHORT).show();
                    }else if (dp.getDayOfMonth() < dayNow){
                        dp.updateDate(yearNow,monthNow,dayNow);
                        Toast.makeText(MainActivity.this, "Date is not available.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                if (tp.getCurrentHour() > 20){
                    tp.setCurrentHour(20);
                    Toast.makeText(MainActivity.this, "Time only available between 8:00am to 8:59pm.",
                            Toast.LENGTH_SHORT).show();
                }else if (tp.getCurrentHour() < 8){
                    tp.setCurrentHour(8);
                    Toast.makeText(MainActivity.this, "Time only available between 8:00am to 8:59pm.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dp.updateDate(2023,6,1);
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
                etName.setText("");
                etPhone.setText("");
                etPax.setText("");
                Toast.makeText(MainActivity.this, "Details has been reset.",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}