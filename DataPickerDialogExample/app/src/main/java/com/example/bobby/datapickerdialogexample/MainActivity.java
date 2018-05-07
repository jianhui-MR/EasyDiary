package com.example.bobby.datapickerdialogexample;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView showdata;
    Button setdata;
    int year,month,day;
    Calendar calendar=Calendar.getInstance(Locale.CHINA);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        showdata=(TextView)findViewById(R.id.time_TV);
        setdata=(Button)findViewById(R.id.changeTime_button);
        Date mydata=new Date();
        calendar.setTime(mydata);
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        showdata.setText(year+"-"+(month+1)+"-"+day);
        setdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(MainActivity.this,
                        dateSetListener,year,month,day);
                datePickerDialog.show();
            }
        });
    }
    private DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker view, int y, int m, int dayOfMonth) {
            Date date=new Date(y,m,dayOfMonth);
            Date now_date=new Date();
            year = y;
            month = m;
            day = dayOfMonth;
            if (date.getTime()<=now_date.getTime())
                updateDate();
            else
            {
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Attention");
                dialog.setMessage("选择日期大于当前正确日期,是否继续改变日期");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateDate();
                    }
                });
                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.create();
                dialog.show();
            }

        }
        private void updateDate(){
            showdata.setText(year+"-"+(month+1)+"-"+day);
        }
    };

}
