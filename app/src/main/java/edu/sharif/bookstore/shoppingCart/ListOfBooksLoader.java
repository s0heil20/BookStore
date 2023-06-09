package edu.sharif.bookstore.shoppingCart;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import edu.sharif.bookstore.database.SQLDatabaseManager;
import edu.sharif.bookstore.entity.Book;
import edu.sharif.bookstore.utils.BookJsonParserUtil;
import edu.sharif.bookstore.utils.NetworkUtils;

public class ListOfBooksLoader extends AsyncTaskLoader {
    private List<String> bookIds;
    private SQLDatabaseManager sqlDatabaseManager;
    public ListOfBooksLoader(Context context, List<String> bookIds) {
        super(context);
        this.bookIds = bookIds;
        this.sqlDatabaseManager = SQLDatabaseManager.instanceOfDatabase(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Book> loadInBackground() {
        ArrayList<Book> books = new ArrayList<Book>();
        for (String bookId : this.bookIds) {
            String bookJson = NetworkUtils.getBookJsonStringById(bookId);
            if (bookJson != null) {
                try {

                    Book book = BookJsonParserUtil.bookJsonIntoBookObject(bookJson);
                    book.setImage(NetworkUtils.downloadBookThumbnailWithURL(book.getImageLink()));
                    // set database fields!
                    book.setAvgRating(sqlDatabaseManager.getRatingDatabaseManager().getAverageRating(book.getBookId()));
                    book.setPrice(sqlDatabaseManager.getPriceDatabaseManager().getBookPrice(book.getBookId()));
                    book.setNumbersLeft(sqlDatabaseManager.getStockDatabaseManager().getBookStock(book.getBookId()));
                    book.setNoRatings(sqlDatabaseManager.getRatingDatabaseManager().getTotalRatingNum(book.getBookId()));
                    books.add(book);
                } catch (JSONException e) {
                    System.out.println("Result Empty!");
                    e.printStackTrace();
                }
            }
        }
        return books;
    }
}
