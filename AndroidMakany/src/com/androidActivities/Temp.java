package com.androidActivities;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class Temp extends Activity {

	Button btnSelectStartDate,btnSelectStartTime, btnSelectEndDate,btnSelectEndTime;
    
    static final int DATE_DIALOG_ID_Start = 0;
    static final int TIME_DIALOG_ID_Start = 1;
    static final int DATE_DIALOG_ID_End = 2;
    static final int TIME_DIALOG_ID_End = 3;
    
    // variables to save user selected date and time
    public  int yearStart,monthStart,dayStart,hourStart,minuteStart;  
    public  int yearEnd,monthEnd,dayEnd,hourEnd,minuteEnd;  
    
    // declare  the variables to Show/Set the date and time when Time and  Date Picker Dialog first appears
    private int mYearStart, mMonthStart, mDayStart,mHourStart,mMinuteStart; 
    private int mYearEnd, mMonthEnd, mDayEnd,mHourEnd,mMinuteEnd; 
    
    // constructor
    
    public Temp()
    {
                // Assign current Date and Time Values to Variables
                final Calendar c = Calendar.getInstance();
                mYearStart = mYearEnd = c.get(Calendar.YEAR);
                mMonthStart = mYearEnd = c.get(Calendar.MONTH);
                mDayStart = mYearEnd = c.get(Calendar.DAY_OF_MONTH);
                mHourStart = mHourEnd = c.get(Calendar.HOUR_OF_DAY);
                mMinuteStart = mMinuteEnd = c.get(Calendar.MINUTE);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_temp);
                
                // get the references of buttons
                btnSelectStartDate=(Button)findViewById(R.id.buttonSelectDate);
                btnSelectStartTime=(Button)findViewById(R.id.buttonSelectTime);
                
                // Set ClickListener on btnSelectStartDate 
                btnSelectStartDate.setOnClickListener(new View.OnClickListener() {
                    
                    public void onClick(View v) {
                        // Show the DatePickerDialog
                         showDialog(DATE_DIALOG_ID_Start);
                    }
                });
                
                // Set ClickListener on btnSelectStartTime
                btnSelectStartTime.setOnClickListener(new View.OnClickListener() {
                    
                    public void onClick(View v) {
                        // Show the TimePickerDialog
                         showDialog(TIME_DIALOG_ID_Start);
                    }
                });
                
    }
    
    
    // Register  DatePickerDialog listener
     private DatePickerDialog.OnDateSetListener mDateSetListener =
                            new DatePickerDialog.OnDateSetListener() {
                        // the callback received when the user "sets" the Date in the DatePickerDialog
                                public void onDateSet(DatePicker view, int yearSelected,
                                                      int monthOfYear, int dayOfMonth) {
                                   yearStart = yearSelected;
                                   monthStart = monthOfYear;
                                   dayStart = dayOfMonth;
                                   // Set the Selected Date in Select date Button
                                   btnSelectStartDate.setText("Date selected : "+dayStart+"-"+monthStart+"-"+yearStart);
                                }
                            };

       // Register  TimePickerDialog listener                 
                            private TimePickerDialog.OnTimeSetListener mTimeSetListener =
                                new TimePickerDialog.OnTimeSetListener() {
                         // the callback received when the user "sets" the TimePickerDialog in the dialog
                                    public void onTimeSet(TimePicker view, int hourOfDay, int min) {
                                        hourStart = hourOfDay;
                                        minuteStart = min;
                                        // Set the Selected Date in Select date Button
                                        btnSelectStartTime.setText("Time selected :"+hourStart+"-"+minuteStart);
                                      }
                                };


    // Method automatically gets Called when you call showDialog()  method
                            @Override
                            protected Dialog onCreateDialog(int id) {
                                switch (id) {
                                case DATE_DIALOG_ID_Start:
                         // create a new DatePickerDialog with values you want to show 
                                    return new DatePickerDialog(this,
                                                mDateSetListener,
                                                mYearStart, mMonthStart, mDayStart);
                        // create a new TimePickerDialog with values you want to show 
                                case TIME_DIALOG_ID_Start:
                                    return new TimePickerDialog(this,
                                            mTimeSetListener, mHourStart, mMinuteStart, false);
                               
                                }
                                return null;
                            }
                 }
