package edu.sharif.bookstore.finalizeOrder;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.util.Calendar;

import edu.sharif.bookstore.R;
import edu.sharif.bookstore.database.SQLDatabaseManager;
import edu.sharif.bookstore.exception.OrderException;
import edu.sharif.bookstore.mainMenu.MainMenuActivity;
import edu.sharif.bookstore.navigationBar.NavBarActivity;
import edu.sharif.bookstore.signUpSignIn.SignUpSignInActivity;

public class FinalizeOrderActivity extends NavBarActivity implements DatePickerDialog.OnDateSetListener {

    private ImageView calendarImageButton;
    private TextView dateTextView, totalPriceTextView;
    private Button submitButton;
    private TextInputEditText addressText;
    private SQLDatabaseManager sqlDatabaseManager;

    private static final long oneDay = 24*60*60*1000;
    private static final long oneWeek = 7*24*60*60*1000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        pageName = "Finalize Order";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_finalize_cart);
        handleParentView(R.layout.nav_finalize_cart);
        sqlDatabaseManager = SQLDatabaseManager.instanceOfDatabase(this);

        loadAllViews();

        configureTotalCostTextView(totalPriceTextView);
        configureImageButton(calendarImageButton);
        configureSubmitButton(submitButton);


    }

    private void loadAllViews(){
        calendarImageButton = (ImageView) findViewById(R.id.calendarImageViewButton);
        dateTextView = (TextView) findViewById(R.id.dateTextViewFinal);
        totalPriceTextView = (TextView) findViewById(R.id.totalCostTextViewFinal);
        submitButton = (Button) findViewById(R.id.submitButtonFinalize);
        addressText = (TextInputEditText) findViewById(R.id.addressTextInput);
    }

    private void configureSubmitButton(Button submitButton){
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totalPrice = Integer.valueOf(totalPriceTextView.getText().toString().substring(0, totalPriceTextView.getText().toString().length()-1));
                String address = addressText.getText().toString();
                String date = dateTextView.getText().toString();
                try {
                    sqlDatabaseManager.getCartDatabaseManager().finalizeCart(address, date, totalPrice);
                    Toast.makeText(getBaseContext(), "Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), MainMenuActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } catch (OrderException e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void configureTotalCostTextView(TextView totalPriceTextView){
        int totalCost = sqlDatabaseManager.getCartDatabaseManager().getTotalCost();
        totalPriceTextView.setText(totalCost+"$");

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

