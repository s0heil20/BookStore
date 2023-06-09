package edu.sharif.bookstore;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import edu.sharif.bookstore.database.SQLDatabaseManager;
import edu.sharif.bookstore.entity.Book;
import edu.sharif.bookstore.entity.Cart;
import edu.sharif.bookstore.utils.BookJsonParserUtil;
import edu.sharif.bookstore.utils.NetworkUtils;

public class ListOfCartsLoader extends AsyncTaskLoader {

    private ArrayList<Cart> carts;
    private SQLDatabaseManager sqlDatabaseManager;

    public ListOfCartsLoader(Context context, ArrayList<Cart> carts) {
        super(context);
        this.carts = carts;
        this.sqlDatabaseManager = SQLDatabaseManager.instanceOfDatabase(context);
    }

    @Override
    public ArrayList<Cart> loadInBackground() {
        for (Cart cart : this.carts) {
            for (String bookId : cart.getBookIds()) {
                String bookJson = NetworkUtils.getBookJsonStringById(bookId);
                if (bookJson != null) {
                    try {

                        Book book = BookJsonParserUtil.bookJsonIntoBookObject(bookJson);
                        // set database fields!
                        book.setAvgRating(sqlDatabaseManager.getRatingDatabaseManager().getAverageRating(book.getBookId()));
                        book.setPrice(sqlDatabaseManager.getPriceDatabaseManager().getBookPrice(book.getBookId()));
                        book.setNumbersLeft(sqlDatabaseManager.getStockDatabaseManager().getBookStock(book.getBookId()));
                        book.setNoRatings(sqlDatabaseManager.getRatingDatabaseManager().getTotalRatingNum(book.getBookId()));
//                        book.setImage(NetworkUtils.downloadBookThumbnailWithURL(book.getImageLink()));
                        cart.addBook(book);
                    } catch (JSONException e) {
                        System.out.println("Result Empty!");
                        e.printStackTrace();
                    }
                }
            }
        }
        return this.carts;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        //super.onStartLoading();
    }
}
