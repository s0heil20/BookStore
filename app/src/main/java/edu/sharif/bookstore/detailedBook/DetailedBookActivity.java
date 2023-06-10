package edu.sharif.bookstore.detailedBook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.List;

import edu.sharif.bookstore.exception.OrderException;
import edu.sharif.bookstore.finalizeOrder.FinalizeOrderActivity;
import edu.sharif.bookstore.R;
import edu.sharif.bookstore.database.SQLDatabaseManager;
import edu.sharif.bookstore.entity.Book;
import edu.sharif.bookstore.entity.Feedback;
import edu.sharif.bookstore.navigationBar.NavBarActivity;

public class DetailedBookActivity extends NavBarActivity implements LoaderManager.LoaderCallbacks<Book> {

    private LinearLayout commentsListDetailed;
    private String bookId;
    private ImageView buyButton;
    private ToggleButton addOrRemoveFromFavoriteButton;
    private ImageView shareButton;
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
    private Book book;

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
        this.commentsListDetailed = (LinearLayout) findViewById(R.id.commentsListDetailed);
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
        this.buyButton = (ImageView) findViewById(R.id.buyButtonDetailed);
        this.addOrRemoveFromFavoriteButton = (ToggleButton) findViewById(R.id.addToFavoriteButtonDetailed);
        this.shareButton = (ImageView) findViewById(R.id.shareButtonDetailed);
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
                int rating = (int)ratingBar.getRating();
                Feedback feedback = new Feedback(userName, commentText, bookId, rating);
                sqlDatabaseManager.getFeedbackDatabaseManager().addFeedback(feedback);
                Toast.makeText(getBaseContext(), "Comment Submitted!" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configureShareButton(ImageView shareButton) {
        // TODO create intent to share with available apps!
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Intent.createChooser(createShareIntent(), "Share using"));
            }
        });
    }

    private void configureAddOrRemoveFromFavoriteButton(ToggleButton addOrRemoveFromFavoriteButton) {
        // TODO give book name to be added to favorites!
        boolean isBookInFavorites = sqlDatabaseManager.getFavouriteDatabaseManager().isBookInFavourites(this.bookId);
        addOrRemoveFromFavoriteButton.setChecked(isBookInFavorites);
        addOrRemoveFromFavoriteButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                    sqlDatabaseManager.getFavouriteDatabaseManager().addBookToFavourites(bookId);
                    Toast.makeText(getBaseContext(), "Added to Favorites!" , Toast.LENGTH_SHORT).show();
                } else {
                    sqlDatabaseManager.getFavouriteDatabaseManager().removeFromFavourites(bookId);
                    Toast.makeText(getBaseContext(), "Removed from Favorites!" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void configureBuyButton(ImageView buyButton) {
        // TODO proceed to next activity!
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sqlDatabaseManager.getCartDatabaseManager().addToCart(bookId);
                    Toast.makeText(getBaseContext(), "Added to Cart!" , Toast.LENGTH_SHORT).show();
                } catch (OrderException e){
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                // startActivity(new Intent(getBaseContext(), FinalizeOrderActivity.class));
            }
        });
    }

    private void configureTextViews(Book book) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(1);
        this.titleTextViewDetailed.setText(book.getTitle());
        this.priceTextViewDetailed.setText("Price: "+book.getPrice()+"$");
        this.categoryTextViewDetailed.setText("Categories: "+book.getCategory());
        String authors = "Authors: ";
        for (String author : book.getAuthors()) {
            authors = authors + author + ", ";
        }
        ((TextView) findViewById(R.id.authorsTextViewDetailed)).setText(authors);
        ((TextView) findViewById(R.id.pageCountTextViewDetailed)).setText("Page count: "+book.getPageCount()+"");
        ((TextView) findViewById(R.id.ratingTextViewDetailed)).setText("Rating: "+df.format(book.getAvgRating())+"");
        ((TextView) findViewById(R.id.noRatingTextView)).setText("Rating Count: "+book.getNoRatings()+"");
        ((TextView) findViewById(R.id.yearTextViewDetailed)).setText("Date published: "+book.getDatePublished());
        ((TextView) findViewById(R.id.leftTextViewDetailed)).setText("Numbers in stock: "+book.getNumbersLeft()+"");
        ((ImageView) findViewById(R.id.bookImageViewDetailed)).setImageDrawable(book.getImage());
        ((TextView) findViewById(R.id.descriptionTextViewDetailed)).setText("Description: "+book.getDescription());
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
            if (!feedback.getComment().equals("")) {
                this.commentsListDetailed.addView(createFeedbackView(feedback));
            }
        }
    }

    private View createFeedbackView(Feedback feedback){
        View feedbackView = getLayoutInflater().inflate(R.layout.comment_layout, null, false);
        TextView usernameTextView = (TextView) feedbackView.findViewById(R.id.userIdComment);
        TextView ratingTextView = (TextView) feedbackView.findViewById(R.id.ratingComment);
        TextView commentTextView = (TextView) feedbackView.findViewById(R.id.descriptionComment);
        usernameTextView.setText("Username: " +feedback.getUsername());
        ratingTextView.setText("Rated: "+feedback.getRating()+"");
        commentTextView.setText("Description: " + feedback.getComment());
        return feedbackView;
    }

    private Intent createShareIntent(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String shareBody = book.getTitle();
        String shareSub = book.getDescription();
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        return shareIntent;
    }


    @Override
    public Loader<Book> onCreateLoader(int id, Bundle args) {
        return new SingleBookLoader(this, args.getString("bookId"));
    }

    @Override
    public void onLoadFinished(Loader<Book> loader, Book book) {
        setContentView(R.layout.nav_detailed_book_layout);
        handleParentView(R.layout.nav_detailed_book_layout);
        loadAllViews();
        if (book != null) {
            this.book = book;
            configureTextViews(book);
        }
        configureButtons();
        loadAllFeedBacks();
    }

    @Override
    public void onLoaderReset(Loader<Book> loader) {

    }
}