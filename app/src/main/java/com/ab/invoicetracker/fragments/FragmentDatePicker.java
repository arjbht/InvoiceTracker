package com.ab.invoicetracker.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Objects;

public class FragmentDatePicker extends DialogFragment
                            implements DatePickerDialog.OnDateSetListener {

    private onDatePickerListener mDatePickerListener=null;

    public onDatePickerListener getmDatePickerListener() {
        return mDatePickerListener;
    }

    void setmDatePickerListener(onDatePickerListener mDatePickerListener) {
        this.mDatePickerListener = mDatePickerListener;
    }

    @Override
    public void onSaveInstanceState(Bundle oldInstanceState)
    {
        super.onSaveInstanceState(oldInstanceState);
        oldInstanceState.clear();
    }

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c;
        int year = 0;
        int month = 0;
        int day = 0;


        c = Calendar.getInstance();

         year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

    Calendar mCMax  = Calendar.getInstance();
        mCMax.add(Calendar.DATE,0);


        DatePickerDialog dialogFrag = new DatePickerDialog(Objects.requireNonNull(getActivity()), this, year, month, day);
        dialogFrag.getDatePicker().setMaxDate(mCMax.getTime().getTime());
        // Create a new instance of DatePickerDialog and return it
        return dialogFrag ;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {

        if(mDatePickerListener!=null){
            mDatePickerListener.onDateSet(view,year,month,day);
        }
    }
    public interface onDatePickerListener {
        void onDateSet(DatePicker view, int year, int month, int day);
    }
}