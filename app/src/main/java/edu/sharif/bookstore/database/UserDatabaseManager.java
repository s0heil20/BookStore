package edu.sharif.bookstore.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import edu.sharif.bookstore.entity.User;

public class UserDatabaseManager implements EntityDatabaseManager {

    private static UserDatabaseManager userDatabaseManager;
    private static final String TABLE_NAME = "UserDB";
    private static final String ID_FIELD = "_id";
    private static final String USER_NAME_FIELD = "user_name";
    private static final String PASSWORD_FIELD = "password";
    private static final String NICKNAME_FIELD = "nickname";

    private SQLDatabaseManager sqlDatabaseManager;

    public UserDatabaseManager(SQLDatabaseManager sqlDatabaseManager) {
        this.sqlDatabaseManager = sqlDatabaseManager;
    }

    public static UserDatabaseManager instanceOfUserDatabaseManager(SQLDatabaseManager sqlDatabaseManager) {
        if (userDatabaseManager == null) {
            userDatabaseManager = new UserDatabaseManager(sqlDatabaseManager);
        }
        return userDatabaseManager;
    }

    public String createTableString() {
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

        return sql.toString();
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    public boolean signUpUser(User user) {
        if (doesUsernameExists(user))
            return false;

        SQLiteDatabase sqLiteDatabase = sqlDatabaseManager.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME_FIELD, user.getUsername());
        contentValues.put(PASSWORD_FIELD, user.getPassword());
        contentValues.put(NICKNAME_FIELD, user.getNickname());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean doesUsernameExists(User user) {
        SQLiteDatabase sqLiteDatabase = sqlDatabaseManager.getReadableDatabase();

        StringBuilder sql;
        sql = new StringBuilder()
                .append("SELECT * FROM ")
                .append(TABLE_NAME)
                .append(" WHERE ")
                .append(USER_NAME_FIELD)
                .append(" = ? ");


        Cursor result = sqLiteDatabase.rawQuery(sql.toString(), new String[]{user.getUsername()});
        return result.getCount() > 0;

    }

    public boolean checkPassword(User user) {
        SQLiteDatabase sqLiteDatabase = sqlDatabaseManager.getReadableDatabase();

        StringBuilder sql;
        sql = new StringBuilder()
                .append("SELECT * FROM ")
                .append(TABLE_NAME)
                .append(" WHERE ")
                .append(USER_NAME_FIELD)
                .append(" = ? ");


        Cursor result = sqLiteDatabase.rawQuery(sql.toString(), new String[]{user.getUsername()});
        result.moveToFirst();
        String password = result.getString(1);
        return password.equals(user.getPassword());
    }

    public String getUserNickname(User user) {
        SQLiteDatabase sqLiteDatabase = sqlDatabaseManager.getReadableDatabase();

        StringBuilder sql;
        sql = new StringBuilder()
                .append("SELECT * FROM ")
                .append(TABLE_NAME)
                .append(" WHERE ")
                .append(USER_NAME_FIELD)
                .append(" = ? ");


        Cursor result = sqLiteDatabase.rawQuery(sql.toString(), new String[]{user.getUsername()});
        result.moveToFirst();
        String nickname = result.getString(2);
        return nickname;
    }
}
