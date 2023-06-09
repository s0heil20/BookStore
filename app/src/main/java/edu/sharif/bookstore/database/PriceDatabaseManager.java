package edu.sharif.bookstore.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Random;

public class PriceDatabaseManager implements EntityDatabaseManager {

    private static PriceDatabaseManager priceDatabaseManager;
    private static final String TABLE_NAME = "PriceDB";
    private static final String ID_FIELD = "_id";
    private static final String BOOK_ID_FIELD = "book_id";
    private static final String PRICE_FIELD = "price";

    private SQLDatabaseManager sqlDatabaseManager;

    public PriceDatabaseManager(SQLDatabaseManager sqlDatabaseManager) {
        this.sqlDatabaseManager = sqlDatabaseManager;
    }

    public static PriceDatabaseManager instanceOfPriceDatabaseManager(SQLDatabaseManager sqlDatabaseManager) {
        if (priceDatabaseManager == null) {
            priceDatabaseManager = new PriceDatabaseManager(sqlDatabaseManager);
        }
        return priceDatabaseManager;
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
                .append(BOOK_ID_FIELD)
                .append(" TEXT, ")
                .append(PRICE_FIELD)
                .append(" INTEGER)");

        return sql.toString();
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }


    public int getBookPrice(String bookId) {
        SQLiteDatabase sqLiteDatabase = sqlDatabaseManager.getWritableDatabase();

        Cursor row = getBookRow(bookId);
        int price;

        if (row.getCount() == 0) {
            price = addRandomPriceForBook(sqLiteDatabase, bookId);
        } else {
            row.moveToFirst();
            price = row.getInt(2);
        }
        sqLiteDatabase.close();
        return price;
    }


    private int addRandomPriceForBook(SQLiteDatabase sqLiteDatabase, String bookId) {
        int generatedPrice = new Random().nextInt(51) + 10;
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOK_ID_FIELD, bookId);
        contentValues.put(PRICE_FIELD, generatedPrice);

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return generatedPrice;
    }

    private Cursor getBookRow(String bookId) {
        SQLiteDatabase sqLiteDatabase = sqlDatabaseManager.getReadableDatabase();

        StringBuilder sql;
        sql = new StringBuilder()
                .append("SELECT * FROM ")
                .append(TABLE_NAME)
                .append(" WHERE ")
                .append(BOOK_ID_FIELD)
                .append(" = ? ");


        Cursor result = sqLiteDatabase.rawQuery(sql.toString(), new String[]{bookId});
        return result;
    }
}
