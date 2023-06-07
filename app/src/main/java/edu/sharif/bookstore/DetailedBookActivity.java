package edu.sharif.bookstore;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class DetailedBookActivity extends AppCompatActivity {

    private Button buyButton;
    private Button addOrRemoveFromFavoriteButton;
    private Button shareButton;
    private Button submitButton;
    private RatingBar ratingBar;
    private TextInputEditText commentTextInput;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_book_layout);

        // TODO load all comments on create!

        buyButton = findViewById(R.id.buyButtonDetailed);
        addOrRemoveFromFavoriteButton = findViewById(R.id.addToFavoriteButtonDetailed);
        shareButton = findViewById(R.id.shareButtonDetailed);
        submitButton = findViewById(R.id.submitButtonDetailed);
        ratingBar = findViewById(R.id.ratingBarDetailed);
        commentTextInput = findViewById(R.id.commentTextInput);

        configureButButton(buyButton);
        configureAddOrRemoveFromFavoriteButton(addOrRemoveFromFavoriteButton);
        configureShareButton(shareButton);
        configureSubmitButton(submitButton);

    }

    private void configureSubmitButton(Button submitButton) {

    }

    private void configureShareButton(Button shareButton) {
        // TODO create intent to share with available apps!
    }

    private void configureAddOrRemoveFromFavoriteButton(Button addOrRemoveFromFavoriteButton) {
        // TODO give book name to be added to favorites!
    }

    private void configureButButton(Button buyButton) {
        // TODO proceed to next activity!
    }

}