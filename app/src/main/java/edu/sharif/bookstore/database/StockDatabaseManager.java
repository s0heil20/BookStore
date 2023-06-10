package edu.sharif.bookstore.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class StockDatabaseManager implements EntityDatabaseManager {
    private static StockDatabaseManager stockDatabaseManager;
    private static final String TABLE_NAME = "StockDB";
    private static final String ID_FIELD = "_id";
    private static final String BOOK_ID_FIELD = "book_id";
    private static final String STOCK_FIELD = "stock";

    private static final int BASE_STOCK = 25;

    private SQLDatabaseManager sqlDatabaseManager;

    public StockDatabaseManager(SQLDatabaseManager sqlDatabaseManager) {
        this.sqlDatabaseManager = sqlDatabaseManager;
    }

    public static StockDatabaseManager instanceOfStockDatabaseManager(SQLDatabaseManager sqlDatabaseManager) {
        if (stockDatabaseManager == null) {
            stockDatabaseManager = new StockDatabaseManager(sqlDatabaseManager);
        }
        return stockDatabaseManager;
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
                .append(STOCK_FIELD)
                .append(" INTEGER)");

        return sql.toString();
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    public void reduceStock(String bookId, int value) {
        Cursor row = getBookRow(bookId);

        if (row.getCount() == 0) {
            addBookRow(bookId, BASE_STOCK - value);
        } else {
            reduceRowStock(row, bookId, value);
        }
    }

    private void addBookRow(String bookId, int firstValue) {
        SQLiteDatabase sqLiteDatabase = sqlDatabaseManager.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOK_ID_FIELD, bookId);
        contentValues.put(STOCK_FIELD, firstValue);

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    private void reduceRowStock(Cursor row, String bookId, int value) {
        SQLiteDatabase sqLiteDatabase = sqlDatabaseManager.getWritableDatabase();

        row.moveToFirst();
        int stock = row.getInt(2);

        StringBuilder sql;
        sql = new StringBuilder()
                .append("UPDATE ")
                .append(TABLE_NAME)
                .append(" SET ")
                .append(STOCK_FIELD)
                .append(" = ")
                .append(stock - value)
                .append(" WHERE ")
                .append(BOOK_ID_FIELD)
                .append(" = '")
                .append(bookId)
                .append("'");

        Log.d("salammm", "addRating: " + sql.toString());

        sqLiteDatabase.execSQL(sql.toString());
        sqLiteDatabase.close();
    }

    public int getBookStock(String bookId) {
        Cursor row = getBookRow(bookId);
        if (row.getCount() == 0) {
            addBookRow(bookId, BASE_STOCK);
            return BASE_STOCK;
        } else {
            row.moveToFirst();
            return row.getInt(2);
        }
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
