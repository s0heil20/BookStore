package edu.sharif.bookstore.shoppingCart;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.sharif.bookstore.bookCard.BookCardAdapter;
import edu.sharif.bookstore.bookCard.BookCardItem;
import edu.sharif.bookstore.R;
import edu.sharif.bookstore.bookCard.SelectBookCardListener;
import edu.sharif.bookstore.database.SQLDatabaseManager;
import edu.sharif.bookstore.detailedBook.DetailedBookActivity;
import edu.sharif.bookstore.entity.Book;
import edu.sharif.bookstore.finalizeOrder.FinalizeOrderActivity;
import edu.sharif.bookstore.navigationBar.NavBarActivity;
import edu.sharif.bookstore.search.BookLoader;

public class ShoppingCartActivity extends NavBarActivity implements LoaderManager.LoaderCallbacks<List<Book>>, SelectBookCardListener {
    RecyclerView recyclerView;
    BookCardAdapter adapter;

    Button purchaseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_shopping_cart);
        handleParentView(R.layout.nav_shopping_cart);

        recyclerView = findViewById(R.id.shoppingCartRecyclerView);

        purchaseButton = findViewById(R.id.purchaseButton);

        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShoppingCartActivity.this, FinalizeOrderActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getSupportLoaderManager().restartLoader(0, null, this);
    }

    private void addBookItemsToRecyclerView(List<Book> books) {
        List<BookCardItem> items = new ArrayList<BookCardItem>();
        for (Book book : books) {
            items.add(new BookCardItem(book.getTitle(), book.getPublisher(), book.getAuthors().get(0),
                    String.valueOf(book.getPrice()), book.getBookId(), book.getImage()));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BookCardAdapter(this, items, true, this);
        recyclerView.setAdapter(adapter);
    }

    @NonNull
    @Override
    public Loader<List<Book>> onCreateLoader(int id, @Nullable Bundle args) {
        SQLDatabaseManager sqlDatabaseManager = SQLDatabaseManager.instanceOfDatabase(this);
        ArrayList<String> bookIds = sqlDatabaseManager.getCartDatabaseManager().getBookIds();
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
        SQLDatabaseManager sqlDatabaseManager = SQLDatabaseManager.instanceOfDatabase(this);
        sqlDatabaseManager.getCartDatabaseManager().removeFromCart(bookCardItem.getBookId());
        getSupportLoaderManager().restartLoader(0, null, this);
    }
}
