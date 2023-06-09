package edu.sharif.bookstore.cartHistory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import edu.sharif.bookstore.R;
import edu.sharif.bookstore.database.SQLDatabaseManager;
import edu.sharif.bookstore.entity.Cart;
import edu.sharif.bookstore.navigationBar.NavBarActivity;

public class CartHistoryActivity extends NavBarActivity implements LoaderManager.LoaderCallbacks<ArrayList<Cart>> {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pageName = "Cart History";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_cart_history);
        handleParentView(R.layout.nav_cart_history);

        recyclerView = findViewById(R.id.cartListRecyclerView);

        getSupportLoaderManager().restartLoader(0, null, this);
    }

    public void addCartsToRecyclerView(ArrayList<Cart> carts) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CartHistoryAdapter(getApplicationContext(), carts));
    }

    @NonNull
    @Override
    public Loader<ArrayList<Cart>> onCreateLoader(int id, @Nullable Bundle args) {
        SQLDatabaseManager sqlDatabaseManager = SQLDatabaseManager.instanceOfDatabase(this);
        return new ListOfCartsLoader(this, sqlDatabaseManager.getCartDatabaseManager().getUsersCarts());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Cart>> loader, ArrayList<Cart> data) {
        addCartsToRecyclerView(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Cart>> loader) {

    }
}