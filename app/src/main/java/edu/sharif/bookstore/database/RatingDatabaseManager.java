package edu.sharif.bookstore.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RatingDatabaseManager implements EntityDatabaseManager {

    private static RatingDatabaseManager ratingDatabaseManager;
    private static final String TABLE_NAME = "RatingDB";
    private static final String ID_FIELD = "_id";
    private static final String BOOK_ID_FIELD = "book_id";
    private static final String SCORE_NUM_FIELD = "score_num";
    private static final String SCORE_SUM_FIELD = "score_sum";


    private SQLDatabaseManager sqlDatabaseManager;

    public RatingDatabaseManager(SQLDatabaseManager sqlDatabaseManager) {
        this.sqlDatabaseManager = sqlDatabaseManager;
    }

    public static RatingDatabaseManager instanceOfRatingDatabaseManager(SQLDatabaseManager sqlDatabaseManager) {
        if (ratingDatabaseManager == null) {
            ratingDatabaseManager = new RatingDatabaseManager(sqlDatabaseManager);
        }
        return ratingDatabaseManager;
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
                .append(SCORE_NUM_FIELD)
                .append(" INTEGER, ")
                .append(SCORE_SUM_FIELD)
                .append(" INTEGER)");

        return sql.toString();
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    public void addRating(String bookId, int rating) {
        SQLiteDatabase sqLiteDatabase = sqlDatabaseManager.getWritableDatabase();

        Cursor row = getBookRow(bookId);


        if (row.getCount() == 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(BOOK_ID_FIELD, bookId);
            contentValues.put(SCORE_SUM_FIELD, rating);
            contentValues.put(SCORE_NUM_FIELD, 1);

            sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        } else {
            row.moveToFirst();
            int scoreNum = row.getInt(2);
            int scoreSum = row.getInt(3);

            StringBuilder sql;
            sql = new StringBuilder()
                    .append("UPDATE ")
                    .append(TABLE_NAME)
                    .append(" SET ")
                    .append(SCORE_SUM_FIELD)
                    .append(" = ")
                    .append(scoreSum + rating)
                    .append(", ")
                    .append(SCORE_NUM_FIELD)
                    .append(" = ")
                    .append(scoreNum + 1)
                    .append(" WHERE ")
                    .append(BOOK_ID_FIELD)
                    .append(" = '")
                    .append(bookId)
                    .append("'");

            Log.d("salammm", "addRating: " + sql.toString());

            sqLiteDatabase.execSQL(sql.toString());

//            ContentValues contentValues = new ContentValues();
//            contentValues.put(SCORE_SUM_FIELD, scoreSum + rating);
//            contentValues.put(SCORE_NUM_FIELD, scoreNum + 1);
//
//            sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
//
//            sqLiteDatabase.update(TABLE_NAME, contentValues, BOOK_ID_FIELD + " = ?", new String[]{bookId});

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

    public float getAverageRating(String bookId) {
        SQLiteDatabase sqLiteDatabase = sqlDatabaseManager.getWritableDatabase();

        Cursor row = getBookRow(bookId);
        row.moveToFirst();

        if (row.getCount() == 0) {
            return 0;
        } else {
            return (float)row.getInt(3) / (float)row.getInt(2);
        }

    }

    public int getTotalRatingNum(String bookId) {
        SQLiteDatabase sqLiteDatabase = sqlDatabaseManager.getWritableDatabase();

        Cursor row = getBookRow(bookId);
        row.moveToFirst();
        if (row.getCount() == 0) {
            return 0;
        } else {
            return row.getInt(2);
        }
    }


}
