package edu.sharif.bookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import edu.sharif.bookstore.mainMenu.MainMenuActivity;
import edu.sharif.bookstore.database.SQLDatabaseManager;
import edu.sharif.bookstore.entity.User;
import edu.sharif.bookstore.navigationBar.NavBarActivity;
import edu.sharif.bookstore.signUpSignIn.SignUpSignInActivity;

public class MainActivity extends AppCompatActivity {

    private static final String fileName = "login";
    private static final String username = "username";
    private static final String password = "password";

    private int NightMode;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("SharedPrefs", MODE_PRIVATE);
        NightMode = sharedPreferences.getInt("NightModeInt", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        AppCompatDelegate.setDefaultNightMode(NightMode);
        getDelegate().applyDayNight();

        sharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(username)) {
            String storedUsername = sharedPreferences.getString(username, "");
            String storedPassword = sharedPreferences.getString(password, "");
            SQLDatabaseManager sqlDatabaseManager = SQLDatabaseManager.instanceOfDatabase(this);
            User user = new User(storedUsername, storedPassword, "");
            String storedNickname = sqlDatabaseManager.getUserDatabaseManager().getUserNickname(user);
            user.setNickname(storedNickname);
            sqlDatabaseManager.getUserDatabaseManager().setLoggedInUser(user);

            Intent intent = new Intent(this, MainMenuActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, SignUpSignInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

}