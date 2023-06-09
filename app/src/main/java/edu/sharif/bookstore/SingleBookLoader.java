package edu.sharif.bookstore;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import org.json.JSONException;

import java.util.List;

import edu.sharif.bookstore.database.SQLDatabaseManager;
import edu.sharif.bookstore.entity.Book;
import edu.sharif.bookstore.utils.BookJsonParserUtil;
import edu.sharif.bookstore.utils.NetworkUtils;

public class SingleBookLoader extends AsyncTaskLoader {
    private String bookId;
    private SQLDatabaseManager sqlDatabaseManager;
    public SingleBookLoader(Context context, String bookId) {
        super(context);
        this.bookId = bookId;
        this.sqlDatabaseManager = SQLDatabaseManager.instanceOfDatabase(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        //super.onStartLoading();
    }

    @Override
    public Book loadInBackground() {
        String bookJson = NetworkUtils.getBookJsonStringById(this.bookId);
        if (bookJson != null) {
            try {

                Book book = BookJsonParserUtil.bookJsonIntoBookObject(bookJson);
                book.setImage(NetworkUtils.downloadBookThumbnailWithURL(book.getImageLink()));
                // set database fields!
                book.setAvgRating(sqlDatabaseManager.getRatingDatabaseManager().getAverageRating(book.getBookId()));
                book.setPrice(sqlDatabaseManager.getPriceDatabaseManager().getBookPrice(book.getBookId()));
                book.setNumbersLeft(sqlDatabaseManager.getStockDatabaseManager().getBookStock(book.getBookId()));
                book.setNoRatings(sqlDatabaseManager.getRatingDatabaseManager().getTotalRatingNum(book.getBookId()));
                return book;
            } catch (JSONException e) {
                System.out.println("Result Empty!");
                e.printStackTrace();
            }
        }
        return null;
    }
}
