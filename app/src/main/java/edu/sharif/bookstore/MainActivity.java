package edu.sharif.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import edu.sharif.bookstore.database.SQLDatabaseManager;
import edu.sharif.bookstore.database.UserDatabaseManager;
import edu.sharif.bookstore.entity.Feedback;
import edu.sharif.bookstore.entity.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, FinalizeOrderActivity.class));



        SQLDatabaseManager sqlDatabaseManager = SQLDatabaseManager.instanceOfDatabase(this);
        sqlDatabaseManager.dropTables();
        sqlDatabaseManager.getRatingDatabaseManager().addRating(
                "o", 90
        );
        sqlDatabaseManager.getRatingDatabaseManager().addRating(
                "o", 9
        );
        sqlDatabaseManager.getRatingDatabaseManager().addRating(
                "o", 19
        );
//        sqlDatabaseManager.getRatingDatabaseManager().addRating(
//                "t", 8
//        );
//        boolean res = sqlDatabaseManager.getUserDatabaseManager().signUpUser(
//                new User("b", "b", "rouzbeh"));
//        boolean res2 =sqlDatabaseManager.getUserDatabaseManager().signUpUser(
//                new User("a", "a", "rouzbeh"));
//        sqlDatabaseManager.getFeedbackDatabaseManager().addFeedback(
//                new Feedback("a", "comment", "eee", 8));
//
//        Log.d("salammm", "onCreate: "+res + res2);

//        startActivity(new Intent(this, DetailedBookActivity.class));
//        startActivity(new Intent(this, SearchActivity.class));
// startActivity(new Intent(this, MainMenuActivity.class));
//        startActivity(new Intent(this, SearchActivity.class));

//        startActivity(new Intent(this, MainMenuActivity.class));
    }
}