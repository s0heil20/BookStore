package edu.sharif.bookstore;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import edu.sharif.bookstore.entity.Book;
import edu.sharif.bookstore.entity.Cart;
import edu.sharif.bookstore.utils.BookJsonParserUtil;
import edu.sharif.bookstore.utils.NetworkUtils;

public class ListOfCartsLoader extends AsyncTaskLoader {

    private ArrayList<Cart> carts;

    public ListOfCartsLoader(Context context, ArrayList<Cart> carts) {
        super(context);
        this.carts = carts;
    }

    @Override
    public ArrayList<Cart> loadInBackground() {
        for (Cart cart : this.carts) {
            for (String bookId : cart.getBookIds()) {
                String bookJson = NetworkUtils.getBookJsonStringById(bookId);
                if (bookJson != null) {
                    try {

                        Book book = BookJsonParserUtil.bookJsonIntoBookObject(bookJson);
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
