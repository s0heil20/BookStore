package edu.sharif.bookstore.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import edu.sharif.bookstore.entity.User;

public class UserDatabaseManager extends SQLiteOpenHelper {

    private static UserDatabaseManager userDatabaseManager;
    private static final String DATABASE_NAME = "UserDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "UserDB";
    private static final String ID_FIELD = "_id";
    private static final String USER_NAME_FIELD = "user_name";
    private static final String PASSWORD_FIELD = "password";
    private static final String NICKNAME_FIELD = "nickname";


    public UserDatabaseManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sql;
        sql = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append("(")
                .append(ID_FIELD)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(USER_NAME_FIELD)
                .append(" TEXT NOT NULL UNIQUE, ")
                .append(PASSWORD_FIELD)
                .append(" TEXT, ")
                .append(NICKNAME_FIELD)
                .append(" TEXT)");

        sqLiteDatabase.execSQL(sql.toString());
    }

    public static UserDatabaseManager instanceOfDatabase(Context context){
        if (userDatabaseManager == null){
            userDatabaseManager = new UserDatabaseManager(context);
        }
        return userDatabaseManager;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean signUpUser(User user){
        if (doesUsernameExists(user))
            return false;

        SQLiteDatabase userDatabase = userDatabaseManager.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME_FIELD, user.getUsername());
        contentValues.put(PASSWORD_FIELD, user.getPassword());
        contentValues.put(NICKNAME_FIELD, user.getNickname());

        userDatabase.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    private boolean doesUsernameExists(User user){
        SQLiteDatabase userDatabase = userDatabaseManager.getReadableDatabase();

        StringBuilder sql;
        sql = new StringBuilder()
                .append("SELECT * FROM ")
                .append(TABLE_NAME)
                .append(" WHERE ")
                .append(USER_NAME_FIELD)
                .append(" = ? ");


        Cursor result = userDatabase.rawQuery(sql.toString(), new String[]{user.getUsername()});
        return result.getCount() > 0;

    }
}
