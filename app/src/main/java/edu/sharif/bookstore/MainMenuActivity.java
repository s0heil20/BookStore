package edu.sharif.bookstore;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainMenuActivity extends AppCompatActivity {

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
    }
}
