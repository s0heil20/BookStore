package edu.sharif.bookstore.favourtie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.sharif.bookstore.R;
import edu.sharif.bookstore.bookCard.BookCardAdapter;
import edu.sharif.bookstore.bookCard.BookCardItem;
import edu.sharif.bookstore.bookCard.SelectBookCardListener;
import edu.sharif.bookstore.database.SQLDatabaseManager;
import edu.sharif.bookstore.detailedBook.DetailedBookActivity;
import edu.sharif.bookstore.entity.Book;
import edu.sharif.bookstore.navigationBar.NavBarActivity;
import edu.sharif.bookstore.shoppingCart.ListOfBooksLoader;

public class FavouriteActivity extends NavBarActivity implements LoaderManager.LoaderCallbacks<List<Book>>, SelectBookCardListener {

    RecyclerView recyclerView;
    BookCardAdapter adapter;

    TextView emptyResultMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pageName = "Favorites";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_favourite);
        handleParentView(R.layout.nav_favourite);

        recyclerView = findViewById(R.id.favouriteRecyclerView);

        emptyResultMessage = findViewById(R.id.favouriteEmptyResultMessage);
    }

    @Override
    protected void onStart() {
        super.onStart();

        getSupportLoaderManager().restartLoader(0, null, this);
    }

    private void addBookItemsToRecyclerView(List<Book> books) {
        if (books.size() == 0){
            emptyResultMessage.setVisibility(View.VISIBLE);
        }else {
            emptyResultMessage.setVisibility(View.INVISIBLE);
        }
        SQLDatabaseManager sqlDatabaseManager = SQLDatabaseManager.instanceOfDatabase(this);
        ArrayList<String> favouriteBooks = sqlDatabaseManager.getFavouriteDatabaseManager().getFavouriteBooks();

        List<BookCardItem> items = new ArrayList<BookCardItem>();
        for (Book book : books) {
            items.add(new BookCardItem(book.getAuthors(), book.getTitle(), book.getPublisher(),
                    String.valueOf(book.getPrice()), book.getBookId(), book.getImage(), book.getAvgRating(),  favouriteBooks.contains(book.getBookId())));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BookCardAdapter(this, items, false, this);
        recyclerView.setAdapter(adapter);
    }

    @NonNull
    @Override
    public Loader<List<Book>> onCreateLoader(int id, @Nullable Bundle args) {
        SQLDatabaseManager sqlDatabaseManager = SQLDatabaseManager.instanceOfDatabase(this);
        ArrayList<String> bookIds = sqlDatabaseManager.getFavouriteDatabaseManager().getFavouriteBooks();
        return new ListOfBooksLoader(this, bookIds);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Book>> loader, List<Book> books) {
        addBookItemsToRecyclerView(books);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Book>> loader) {

    }

    @Override
    public void onItemClicked(BookCardItem bookCardItem) {
        startActivity(new Intent(this, DetailedBookActivity.class).putExtra("bookId", bookCardItem.getBookId()));
    }

    @Override
    public void onDeleteItemClicked(BookCardItem bookCardItem) {

    }
}
