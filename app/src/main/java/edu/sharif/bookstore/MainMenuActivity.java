package edu.sharif.bookstore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainMenuActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_layout);

        RecyclerView recyclerView = findViewById(R.id.bookListRecyclerView);

        List<MainMenuItem> items = new ArrayList<MainMenuItem>();
        items.add(new MainMenuItem(0, "SALAM", "5/11","10$"));
        items.add(new MainMenuItem(0, "SALAM", "5/11","10$"));
        items.add(new MainMenuItem(0, "SALAM", "5/11","10$"));
        items.add(new MainMenuItem(0, "SALAM", "5/11","10$"));
        items.add(new MainMenuItem(0, "SALAM", "5/11","10$"));
        items.add(new MainMenuItem(0, "SALAM", "5/11","10$"));
        items.add(new MainMenuItem(0, "SALAM", "5/11","10$"));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MainMenuAdapter(getApplicationContext(), items));

        String queryString = "java";
        Bundle queryBundle = new Bundle();
        queryBundle.putString("queryString", queryString);
        getSupportLoaderManager().restartLoader(0, queryBundle, this);

    }


    @Override
    public Loader<String> onCreateLoader(int id,Bundle args) {
        return new BookLoader(this, args.getString("queryString"));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject book = itemsArray.getJSONObject(i);
                String title = null;
                String authors = null;
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");
                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (title != null && authors != null) {
                    System.out.println(title);
                    System.out.println(authors);
                    return;
                }
                System.out.println("NO RESULT!");
            }
        } catch (Exception e) {
            System.out.println("NO RESULT!");
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
