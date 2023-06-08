package edu.sharif.bookstore.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class CartDatabaseManager implements EntityDatabaseManager{

    private static CartDatabaseManager cartDatabaseManager;
    private static final String TABLE_NAME = "CartDB";
    private static final String ID_FIELD = "_id";
    private static final String USER_NAME_FIELD = "user_name";
    private static final String CART_ID_FIELD = "cart_id";
    private static final String BOOK_ID_FIELD = "book_id";

    private SQLDatabaseManager sqlDatabaseManager;

    public CartDatabaseManager(SQLDatabaseManager sqlDatabaseManager) {
        this.sqlDatabaseManager = sqlDatabaseManager;
    }

    public static CartDatabaseManager instanceOfCartDatabaseManager(SQLDatabaseManager sqlDatabaseManager){
        if (cartDatabaseManager == null){
            cartDatabaseManager = new CartDatabaseManager(sqlDatabaseManager);
        }
        return cartDatabaseManager;
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
                .append(CART_ID_FIELD)
                .append(" TEXT, ")
                .append(BOOK_ID_FIELD)
                .append(" TEXT)");

        return sql.toString();
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    public void addToCart(String username, String cartId, String bookId){
        SQLiteDatabase sqLiteDatabase = sqlDatabaseManager.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME_FIELD, username);
        contentValues.put(CART_ID_FIELD, cartId);
        contentValues.put(BOOK_ID_FIELD, bookId);

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }
}
