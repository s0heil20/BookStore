package edu.sharif.bookstore;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

import edu.sharif.bookstore.entity.Book;
import edu.sharif.bookstore.utils.BookJsonParserUtil;
import edu.sharif.bookstore.utils.NetworkUtils;

public class BookLoader extends AsyncTaskLoader {
    private String queryString;
    private String queryType;
    private String sortingType;
    private String maxResult;
    public BookLoader(Context context, String queryString, String queryType) {
        super(context);
        this.queryString = queryString;
        this.queryType = queryType;
        this.sortingType = "relevance";
        this.maxResult = "25";
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        //super.onStartLoading();
    }

    @Override
    public List<Book> loadInBackground() {

        List<Book> books = BookJsonParserUtil.booksJsonArrayIntoBooksObjectArray(NetworkUtils.searchBookWithQueryAndGetJsonString(this.queryString, this.queryType, sortingType, maxResult));
        for (Book book : books) {
            book.setImage(NetworkUtils.downloadBookThumbnailWithURL(book.getImageLink()));
        }
        return books;
    }
}
