package edu.sharif.bookstore;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

public class BookLoader extends AsyncTaskLoader {
    private String queryString;
    public BookLoader(Context context, String queryString) {
        super(context);
        this.queryString = queryString;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        //super.onStartLoading();
    }

    @Override
    public String loadInBackground() {
        return NetworkUtils.getBookInfo(this.queryString);
    }
}
