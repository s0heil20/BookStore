package edu.sharif.bookstore;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

import edu.sharif.bookstore.entity.Book;
import edu.sharif.bookstore.utils.BookJsonParserUtil;
import edu.sharif.bookstore.utils.NetworkUtils;

public class SingleBookLoader extends AsyncTaskLoader {
    private String bookId;
    public SingleBookLoader(Context context, String bookId) {
        super(context);
        this.bookId = bookId;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        //super.onStartLoading();
    }

    @Override
    public Book loadInBackground() {
        List<Book> books = BookJsonParserUtil.booksJsonArrayIntoBooksObjectArray(NetworkUtils.searchBookWithQueryAndGetJsonString(this.bookId, null, "relevance", "1"));
        if (books.size() > 0) {
            Book theBook = books.get(0);
            theBook.setImage(NetworkUtils.downloadBookThumbnailWithURL(theBook.getImageLink()));
            return theBook;
        }
        return null;
    }
}
