package edu.sharif.bookstore.database;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import edu.sharif.bookstore.entity.Feedback;

public class FeedbackDatabaseManager implements EntityDatabaseManager{
    private static FeedbackDatabaseManager feedbackDatabaseManager;
    private static final String TABLE_NAME = "FeedbackDB";
    private static final String ID_FIELD = "_id";
    private static final String USER_NAME_FIELD = "user_name";
    private static final String BOOK_ID_FIELD = "book_id";
    private static final String COMMENT_FIELD = "nickname";
    private static final String RATING_FIELD = "rating";

    private SQLDatabaseManager sqlDatabaseManager;

    public FeedbackDatabaseManager(SQLDatabaseManager sqlDatabaseManager) {
        this.sqlDatabaseManager = sqlDatabaseManager;
    }

    public static FeedbackDatabaseManager instanceOfFeedbackDatabaseManager(SQLDatabaseManager sqlDatabaseManager){
        if (feedbackDatabaseManager == null){
            feedbackDatabaseManager = new FeedbackDatabaseManager(sqlDatabaseManager);
        }
        return feedbackDatabaseManager;
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
                .append(" TEXT, ")
                .append(COMMENT_FIELD)
                .append(" TEXT, ")
                .append(RATING_FIELD)
                .append(" TEXT)");

        return sql.toString();
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    public void addFeedback(Feedback feedback){
        SQLiteDatabase sqLiteDatabase = sqlDatabaseManager.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME_FIELD, feedback.getUsername());
        contentValues.put(COMMENT_FIELD, feedback.getComment());
        contentValues.put(BOOK_ID_FIELD, feedback.getBookId());
        contentValues.put(RATING_FIELD, feedback.getRating());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

}
