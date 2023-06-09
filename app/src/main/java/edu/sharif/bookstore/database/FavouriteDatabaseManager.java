package edu.sharif.bookstore.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import edu.sharif.bookstore.entity.User;

public class FavouriteDatabaseManager implements EntityDatabaseManager {

    private static FavouriteDatabaseManager favouriteDatabaseManager;
    private static final String TABLE_NAME = "favouriteDB";
    private static final String ID_FIELD = "_id";
    private static final String USER_NAME_FIELD = "user_name";
    private static final String BOOK_ID_FIELD = "book_id";

    private SQLDatabaseManager sqlDatabaseManager;

    public FavouriteDatabaseManager(SQLDatabaseManager sqlDatabaseManager) {
        this.sqlDatabaseManager = sqlDatabaseManager;
    }

    public static FavouriteDatabaseManager instanceOfFavouriteDatabaseManager(SQLDatabaseManager sqlDatabaseManager) {
        if (favouriteDatabaseManager == null) {
            favouriteDatabaseManager = new FavouriteDatabaseManager(sqlDatabaseManager);
        }
        return favouriteDatabaseManager;
    }

    @Override
    public String createTableString() {
        StringBuilder sql;
        sql = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append("(")
                .append(ID_FIELD)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(USER_NAME_FIELD)
                .append(" TEXT, ")
                .append(BOOK_ID_FIELD)
                .append(" TEXT)");

        return sql.toString();
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    public void addBookToFavourites(String bookId) {
        User loggedInUser = sqlDatabaseManager.getUserDatabaseManager().getLoggedInUser();
        String username = loggedInUser.getUsername();

        SQLiteDatabase sqLiteDatabase = sqlDatabaseManager.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME_FIELD, username);
        contentValues.put(BOOK_ID_FIELD, bookId);

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public void removeFromFavourites(String bookId){

    }

    public ArrayList<String> getFavouriteBooks(){
        return null;
    }
}
