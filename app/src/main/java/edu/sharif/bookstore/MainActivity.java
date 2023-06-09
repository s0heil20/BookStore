package edu.sharif.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import edu.sharif.bookstore.database.SQLDatabaseManager;
import edu.sharif.bookstore.database.UserDatabaseManager;
import edu.sharif.bookstore.entity.Feedback;
import edu.sharif.bookstore.entity.User;
import edu.sharif.bookstore.utils.BookIdListJSONConverter;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    private static final String fileName = "login";
    private static final String username = "username";
    private static final String password = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ArrayList<String> bookIds = new ArrayList<String>();
//        bookIds.add("1");
//        bookIds.add("2");
//        bookIds.add("3");
//
//        System.out.println(BookIdListJSONConverter.bookIdListToJSON(bookIds).toString());
//        System.out.println(BookIdListJSONConverter.JSONArrayToBookIdList(BookIdListJSONConverter.bookIdListToJSON(bookIds)));

        startActivity(new Intent(this, MainMenuActivity.class));



//        SQLDatabaseManager sqlDatabaseManager = SQLDatabaseManager.instanceOfDatabase(this);
//        sqlDatabaseManager.dropTables();
//        sqlDatabaseManager.getRatingDatabaseManager().addRating(
//                "o", 90
//        );
//        sqlDatabaseManager.getRatingDatabaseManager().addRating(
//                "o", 9
//        );
//        sqlDatabaseManager.getRatingDatabaseManager().addRating(
//                "o", 19
//        );
//        sqlDatabaseManager.getStockDatabaseManager().reduceStock("a", 8);
//        sqlDatabaseManager.getStockDatabaseManager().reduceStock("a", 10);


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

//        sharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE);
//        if (sharedPreferences.contains(username)) {
//            String storedUsername = sharedPreferences.getString(username, "");
//            String storedPassword = sharedPreferences.getString(password, "");
//            SQLDatabaseManager sqlDatabaseManager = SQLDatabaseManager.instanceOfDatabase(this);
//            User user = new User(storedUsername, storedPassword, "");
//            String storedNickname = sqlDatabaseManager.getUserDatabaseManager().getUserNickname(user);
//            user.setNickname(storedNickname);
//            sqlDatabaseManager.getUserDatabaseManager().setLoggedInUser(user);
//            startActivity(new Intent(this, MainMenuActivity.class));
//        } else {
//            startActivity(new Intent(this, SignUpSignInActivity.class));
//        }
    }
}