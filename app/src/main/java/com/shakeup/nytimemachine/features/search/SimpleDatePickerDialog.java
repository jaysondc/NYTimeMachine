package com.shakeup.nytimemachine.features.search;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Jayson on 9/24/2017.
 * <p>
 * Simple date picker for setting the date filter
 */

public class SimpleDatePickerDialog
        extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private DatePickerCallback callback;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void setCallback(DatePickerCallback callback) {
        this.callback = callback;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        Date date = new GregorianCalendar(year, month, day).getTime();
        callback.onDatePicked(date.getTime());
    }
}
