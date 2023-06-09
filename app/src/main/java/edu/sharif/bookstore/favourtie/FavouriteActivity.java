package edu.sharif.bookstore.favourtie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.util.List;

import edu.sharif.bookstore.R;
import edu.sharif.bookstore.entity.Book;
import edu.sharif.bookstore.navigationBar.NavBarActivity;

public class FavouriteActivity extends NavBarActivity implements LoaderManager.LoaderCallbacks<List<Book>>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_favourite);
        handleParentView(R.id.nav_favorites);
    }

    @NonNull
    @Override
    public Loader<List<Book>> onCreateLoader(int id, @Nullable Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Book>> loader, List<Book> data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Book>> loader) {

    }
}
