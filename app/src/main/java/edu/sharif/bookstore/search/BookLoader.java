package edu.sharif.bookstore.search;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

import edu.sharif.bookstore.database.SQLDatabaseManager;
import edu.sharif.bookstore.entity.Book;
import edu.sharif.bookstore.utils.BookJsonParserUtil;
import edu.sharif.bookstore.utils.NetworkUtils;

public class BookLoader extends AsyncTaskLoader {
    private String queryString;
    private String queryType;
    private String sortingType;
    private String maxResult;
    private SQLDatabaseManager sqlDatabaseManager;
    public BookLoader(Context context, String queryString, String queryType) {
        super(context);
        this.queryString = queryString;
        this.queryType = queryType;
        this.sortingType = "relevance";
        this.maxResult = "10";
        this.sqlDatabaseManager = SQLDatabaseManager.instanceOfDatabase(context);
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
            // set database fields!
            book.setAvgRating(sqlDatabaseManager.getRatingDatabaseManager().getAverageRating(book.getBookId()));
            book.setPrice(sqlDatabaseManager.getPriceDatabaseManager().getBookPrice(book.getBookId()));
            book.setNumbersLeft(sqlDatabaseManager.getStockDatabaseManager().getBookStock(book.getBookId()));
            book.setNoRatings(sqlDatabaseManager.getRatingDatabaseManager().getTotalRatingNum(book.getBookId()));
        }
        return books;
    }
}
