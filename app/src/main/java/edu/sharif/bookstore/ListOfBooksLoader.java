package edu.sharif.bookstore;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import edu.sharif.bookstore.entity.Book;
import edu.sharif.bookstore.utils.BookJsonParserUtil;
import edu.sharif.bookstore.utils.NetworkUtils;

public class ListOfBooksLoader extends AsyncTaskLoader {

    private List<String> bookIds;

    public ListOfBooksLoader(Context context, List<String> bookIds) {
        super(context);
        this.bookIds = bookIds;
    }

    @Override
    public List<Book> loadInBackground() {

        ArrayList<Book> books = new ArrayList<Book>();
        for (String bookId : this.bookIds) {
            String bookJson = NetworkUtils.getBookJsonStringById(bookId);
            if (bookJson != null) {
                try {

                    Book book = BookJsonParserUtil.bookJsonIntoBookObject(bookJson);
                    book.setImage(NetworkUtils.downloadBookThumbnailWithURL(book.getImageLink()));
                    books.add(book);
                } catch (JSONException e) {
                    System.out.println("Result Empty!");
                    e.printStackTrace();
                }
            }
        }
        return books;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        //super.onStartLoading();
    }
}
