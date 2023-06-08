package edu.sharif.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import edu.sharif.bookstore.database.UserDatabaseManager;
import edu.sharif.bookstore.entity.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        startActivity(new Intent(this, SignUpSignInActivity.class));

        startActivity(new Intent(this, FinalizeOrderActivity.class));

        UserDatabaseManager userDatabaseManager = UserDatabaseManager.instanceOfDatabase(this);
        boolean res = userDatabaseManager.signUpUser(new User("a", "a", "rouzbeh"));
        boolean res2 =userDatabaseManager.signUpUser(new User("a", "a", "rouzbeh"));

        Log.d("salammm", "onCreate: "+res + res2);

//        startActivity(new Intent(this, DetailedBookActivity.class));
//        startActivity(new Intent(this, SearchActivity.class));
// startActivity(new Intent(this, MainMenuActivity.class));
//        startActivity(new Intent(this, SearchActivity.class));

//        startActivity(new Intent(this, MainMenuActivity.class));
    }
}