package edu.sharif.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import edu.sharif.bookstore.database.UserDatabaseManager;
import edu.sharif.bookstore.entity.User;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    private static final String fileName = "login";
    private static final String username = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(username)) {
            startActivity(new Intent(this, FakeActivity.class));
        } else {
            startActivity(new Intent(this, SignUpSignInActivity.class));
        }
    }
}