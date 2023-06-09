package edu.sharif.bookstore;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.util.List;

import edu.sharif.bookstore.database.SQLDatabaseManager;
import edu.sharif.bookstore.entity.Book;
import edu.sharif.bookstore.entity.Feedback;

public class DetailedBookActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Book> {

    private LinearLayout mainLinearLayout;
    private String bookId;
    private Button buyButton;
    private Button addOrRemoveFromFavoriteButton;
    private Button shareButton;
    private Button submitButton;
    private RatingBar ratingBar;
    private TextInputEditText commentTextInput;
    private TextView categoryTextViewDetailed;
    private TextView titleTextViewDetailed;
    private TextView priceTextViewDetailed;
    private TextView authorsTextViewDetailed;
    private TextView pageCountTextViewDetailed;
    private TextView ratingTextViewDetailed;
    private TextView noRatingTextView;
    private TextView yearTextViewDetailed;
    private TextView leftTextViewDetailed;
    private TextView descriptionTextViewDetailed;
    private ImageView imageViewDetailed;
    private SQLDatabaseManager sqlDatabaseManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sqlDatabaseManager = SQLDatabaseManager.instanceOfDatabase(this);
        if (getIntent().getExtras().containsKey("bookId")){
            this.bookId = getIntent().getStringExtra("bookId");
        }



        // TODO load all comments on create!




        Bundle bookIdBundle = new Bundle();
        bookIdBundle.putString("bookId", this.bookId);
        getSupportLoaderManager().restartLoader(0, bookIdBundle, this);

    }

    private void loadAllViews() {
        // main layout!
        this.mainLinearLayout = (LinearLayout) findViewById(R.id.mainLinearLayoutDetailed);
        // Text Views!
        this.titleTextViewDetailed = ((TextView) findViewById(R.id.titleTextViewDetailed));
        this.priceTextViewDetailed = ((TextView) findViewById(R.id.priceTextViewDetailed));
        this.categoryTextViewDetailed = ((TextView) findViewById(R.id.categoryTextViewDetailed));
        this.authorsTextViewDetailed = ((TextView) findViewById(R.id.authorsTextViewDetailed));
        this.pageCountTextViewDetailed = ((TextView) findViewById(R.id.pageCountTextViewDetailed));
        this.ratingTextViewDetailed = ((TextView) findViewById(R.id.ratingTextViewDetailed));
        this.noRatingTextView = ((TextView) findViewById(R.id.noRatingTextView));
        this.yearTextViewDetailed = ((TextView) findViewById(R.id.yearTextViewDetailed));
        this.leftTextViewDetailed = ((TextView) findViewById(R.id.leftTextViewDetailed));
        this.descriptionTextViewDetailed = ((TextView) findViewById(R.id.descriptionTextViewDetailed));
        // Buttons!
        this.buyButton = (Button) findViewById(R.id.buyButtonDetailed);
        this.addOrRemoveFromFavoriteButton = (Button) findViewById(R.id.addToFavoriteButtonDetailed);
        this.shareButton = (Button) findViewById(R.id.shareButtonDetailed);
        this.submitButton = (Button) findViewById(R.id.submitButtonDetailed);
        // image view!
        this.imageViewDetailed = (ImageView) findViewById(R.id.bookImageViewDetailed);
        // text input!
        this.commentTextInput = (TextInputEditText) findViewById(R.id.commentTextInput);
        // rating bar!
        this.ratingBar =  findViewById(R.id.ratingBarDetailed);


    }

    private void configureSubmitButton(Button submitButton) {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentText = commentTextInput.getText().toString();
                String userName = sqlDatabaseManager.getUserDatabaseManager().getLoggedInUser().getUsername();
                int rating = (ratingBar.getNumStars());
                Feedback feedback = new Feedback(userName, commentText, bookId, rating);
                sqlDatabaseManager.getFeedbackDatabaseManager().addFeedback(feedback);
            }
        });
    }

    private void configureShareButton(Button shareButton) {
        // TODO create intent to share with available apps!
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void configureAddOrRemoveFromFavoriteButton(Button addOrRemoveFromFavoriteButton) {
        // TODO give book name to be added to favorites!
        addOrRemoveFromFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDatabaseManager.getFavouriteDatabaseManager().addBookToFavourites(bookId);
            }
        });
    }

    private void configureBuyButton(Button buyButton) {
        // TODO proceed to next activity!
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDatabaseManager.getCartDatabaseManager().addToCart(bookId);
            }
        });
    }

    private void configureTextViews(Book book) {
        this.titleTextViewDetailed.setText(book.getTitle());
        this.priceTextViewDetailed.setText(String.valueOf(book.getPrice()));
        this.categoryTextViewDetailed.setText(book.getCategory());
        String authors = "Authors: ";
        for (String author : book.getAuthors()) {
            authors = authors + author + ", ";
        }
        ((TextView) findViewById(R.id.authorsTextViewDetailed)).setText(authors);
        ((TextView) findViewById(R.id.pageCountTextViewDetailed)).setText(book.getPageCount()+"");
        ((TextView) findViewById(R.id.ratingTextViewDetailed)).setText(book.getAvgRating()+"");
        ((TextView) findViewById(R.id.noRatingTextView)).setText(book.getNoRatings()+"");
        ((TextView) findViewById(R.id.yearTextViewDetailed)).setText(book.getDatePublished());
        ((TextView) findViewById(R.id.leftTextViewDetailed)).setText(book.getNumbersLeft()+"");
        ((ImageView) findViewById(R.id.bookImageViewDetailed)).setImageDrawable(book.getImage());
        ((TextView) findViewById(R.id.descriptionTextViewDetailed)).setText(book.getDescription());
    }

    private void configureButtons(){
        configureBuyButton(buyButton);
        configureAddOrRemoveFromFavoriteButton(addOrRemoveFromFavoriteButton);
        configureShareButton(shareButton);
        configureSubmitButton(submitButton);
    }

    private void loadAllFeedBacks(){
        List<Feedback> feedbacks = sqlDatabaseManager.getFeedbackDatabaseManager().getBookFeedbacks(bookId);
        for (Feedback feedback : feedbacks) {
            this.mainLinearLayout.addView(createFeedbackView(feedback));
        }
    }

    private View createFeedbackView(Feedback feedback){
        View feedbackView = getLayoutInflater().inflate(R.layout.comment_layout, null, false);
        TextView usernameTextView = (TextView) feedbackView.findViewById(R.id.userIdComment);
        TextView ratingTextView = (TextView) feedbackView.findViewById(R.id.ratingComment);
        TextView commentTextView = (TextView) feedbackView.findViewById(R.id.descriptionComment);
        usernameTextView.setText(feedback.getUsername());
        ratingTextView.setText(feedback.getRating()+"");
        commentTextView.setText(feedback.getComment());
        return feedbackView;
    }


    @Override
    public Loader<Book> onCreateLoader(int id, Bundle args) {
        return new SingleBookLoader(this, args.getString("bookId"));
    }

    @Override
    public void onLoadFinished(Loader<Book> loader, Book book) {
        setContentView(R.layout.activity_detailed_book_layout);
        loadAllViews();
        if (book != null) {
            configureTextViews(book);
        }
        configureButtons();
        loadAllFeedBacks();
    }

    @Override
    public void onLoaderReset(Loader<Book> loader) {

    }
}