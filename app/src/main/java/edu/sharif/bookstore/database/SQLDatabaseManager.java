package edu.sharif.bookstore.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class SQLDatabaseManager extends SQLiteOpenHelper {
    private static SQLDatabaseManager sqlDatabaseManager;
    private static final String DATABASE_NAME = "AppDB";
    private static final int DATABASE_VERSION = 1;

    private final UserDatabaseManager userDatabaseManager;
    private final FeedbackDatabaseManager feedbackDatabaseManager;

    private final FavouriteDatabaseManager favouriteDatabaseManager;

    private final CartDatabaseManager cartDatabaseManager;

    private final RatingDatabaseManager ratingDatabaseManager;

    private final StockDatabaseManager stockDatabaseManager;
    private final PriceDatabaseManager priceDatabaseManager;


    public UserDatabaseManager getUserDatabaseManager() {
        return userDatabaseManager;
    }

    public FeedbackDatabaseManager getFeedbackDatabaseManager() {
        return feedbackDatabaseManager;
    }

    public FavouriteDatabaseManager getFavouriteDatabaseManager() {
        return favouriteDatabaseManager;
    }

    public CartDatabaseManager getCartDatabaseManager() {
        return cartDatabaseManager;
    }

    public RatingDatabaseManager getRatingDatabaseManager() {
        return ratingDatabaseManager;
    }

    public StockDatabaseManager getStockDatabaseManager() {
        return stockDatabaseManager;
    }
    public PriceDatabaseManager getPriceDatabaseManager() { return priceDatabaseManager; }

    public SQLDatabaseManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        userDatabaseManager = UserDatabaseManager.instanceOfUserDatabaseManager(this);
        feedbackDatabaseManager = FeedbackDatabaseManager.instanceOfFeedbackDatabaseManager(this);
        favouriteDatabaseManager = FavouriteDatabaseManager.instanceOfFavouriteDatabaseManager(this);
        cartDatabaseManager = CartDatabaseManager.instanceOfCartDatabaseManager(this);
        ratingDatabaseManager = RatingDatabaseManager.instanceOfRatingDatabaseManager(this);
        stockDatabaseManager = StockDatabaseManager.instanceOfStockDatabaseManager(this);
        priceDatabaseManager = PriceDatabaseManager.instanceOfPriceDatabaseManager(this);

    }

    public static SQLDatabaseManager instanceOfDatabase(Context context) {
        if (sqlDatabaseManager == null) {
            sqlDatabaseManager = new SQLDatabaseManager(context);
        }
        return sqlDatabaseManager;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(userDatabaseManager.createTableString());
        sqLiteDatabase.execSQL(feedbackDatabaseManager.createTableString());
        sqLiteDatabase.execSQL(favouriteDatabaseManager.createTableString());
        sqLiteDatabase.execSQL(cartDatabaseManager.createTableString());
        sqLiteDatabase.execSQL(ratingDatabaseManager.createTableString());
        sqLiteDatabase.execSQL(stockDatabaseManager.createTableString());
        sqLiteDatabase.execSQL(priceDatabaseManager.createTableString());
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + userDatabaseManager.getTableName());
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + feedbackDatabaseManager.getTableName());
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + favouriteDatabaseManager.getTableName());
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + cartDatabaseManager.getTableName());
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ratingDatabaseManager.getTableName());
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + stockDatabaseManager.getTableName());
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + priceDatabaseManager.getTableName());
        onCreate(sqLiteDatabase);
    }


    public void dropTables() {
        SQLiteDatabase sqLiteDatabase = sqlDatabaseManager.getWritableDatabase();
        onUpgrade(sqLiteDatabase, 1, 2);
    }


}