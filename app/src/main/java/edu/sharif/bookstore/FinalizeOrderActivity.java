package edu.sharif.bookstore;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;

public class FinalizeOrderActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private ImageView calendarImageButton;
    private TextView dateTextView;
    private static final long oneDay = 24*60*60*1000;
    private static final long oneWeek = 7*24*60*60*1000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalize_cart);

        calendarImageButton = (ImageView) findViewById(R.id.calendarImageViewButton);

        configureImageButton(calendarImageButton);
        dateTextView = (TextView) findViewById(R.id.dateTextViewFinal);

    }

    private void configureImageButton(ImageView calendarImageButton){
        calendarImageButton.setClickable(true);
        calendarImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Date picker");
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        long todayInMillis = c.getTimeInMillis();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        if (c.getTimeInMillis() >= todayInMillis + oneDay && c.getTimeInMillis() <= todayInMillis + oneWeek) {
            String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
            System.out.println(currentDateString);
            this.dateTextView.setText(currentDateString);
        } else {
            Toast.makeText(this, "Invalid Date! select between 1 and 7 days!" , Toast.LENGTH_LONG).show();
        }
    }
}

