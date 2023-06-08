package edu.sharif.bookstore.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import edu.sharif.bookstore.entity.User;

public class SQLDatabaseManager extends SQLiteOpenHelper{
    private static SQLDatabaseManager sqlDatabaseManager;
    private static final String DATABASE_NAME = "AppDB";
    private static final int DATABASE_VERSION = 1;

    private final UserDatabaseManager userDatabaseManager;
    private final FeedbackDatabaseManager feedbackDatabaseManager;


    public UserDatabaseManager getUserDatabaseManager() {
        return userDatabaseManager;
    }

    public FeedbackDatabaseManager getFeedbackDatabaseManager() {
        return feedbackDatabaseManager;
    }

    public SQLDatabaseManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        userDatabaseManager = UserDatabaseManager.instanceOfUserDatabaseManager(this);
        feedbackDatabaseManager = FeedbackDatabaseManager.instanceOfFeedbackDatabaseManager(this);
    }

    public static SQLDatabaseManager instanceOfDatabase(Context context){
        if (sqlDatabaseManager == null){
            sqlDatabaseManager = new SQLDatabaseManager(context);
        }
        return sqlDatabaseManager;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("salammm", "onCreate: "+ userDatabaseManager.createTableString());
        Log.d("salammm", "onCreate: "+ feedbackDatabaseManager.createTableString());
        sqLiteDatabase.execSQL(userDatabaseManager.createTableString());
        sqLiteDatabase.execSQL(feedbackDatabaseManager.createTableString());
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + userDatabaseManager.getTableName());
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + feedbackDatabaseManager.getTableName());
        onCreate(sqLiteDatabase);
    }


    public void dropTables(){
        SQLiteDatabase sqLiteDatabase = sqlDatabaseManager.getWritableDatabase();
        onUpgrade(sqLiteDatabase, 1, 2);
    }


}