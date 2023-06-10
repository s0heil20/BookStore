package edu.sharif.bookstore.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Objects;

import edu.sharif.bookstore.entity.Cart;
import edu.sharif.bookstore.entity.User;
import edu.sharif.bookstore.exception.OrderException;
import edu.sharif.bookstore.exception.OrderExceptionType;
import edu.sharif.bookstore.utils.BookIdListJSONConverter;

public class CartDatabaseManager implements EntityDatabaseManager {

    private static CartDatabaseManager cartDatabaseManager;
    private static final String TABLE_NAME = "CartDB";
    private static final String CART_ID_FIELD = "cart_id";
    private static final String USER_NAME_FIELD = "user_name";
    private static final String BOOK_IDS_FIELD = "book_ids";
    private static final String ADDRESS_FIELD = "address";
    private static final String DATE_FIELD = "date";
    private static final String TOTAL_PRICE_FIELD = "total_price";


    private SQLDatabaseManager sqlDatabaseManager;

    private ArrayList<String> bookIds;

    public CartDatabaseManager(SQLDatabaseManager sqlDatabaseManager) {
        this.sqlDatabaseManager = sqlDatabaseManager;
        bookIds = new ArrayList<>();
    }

    public static CartDatabaseManager instanceOfCartDatabaseManager(SQLDatabaseManager sqlDatabaseManager) {
        if (cartDatabaseManager == null) {
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
                .append(CART_ID_FIELD)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(USER_NAME_FIELD)
                .append(" TEXT, ")
                .append(BOOK_IDS_FIELD)
                .append(" TEXT, ")
                .append(ADDRESS_FIELD)
                .append(" TEXT, ")
                .append(DATE_FIELD)
                .append(" TEXT, ")
                .append(TOTAL_PRICE_FIELD)
                .append(" INT)");

        return sql.toString();
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    public ArrayList<String> getBookIds() {
        return bookIds;
    }

    public void addToCart(String bookId) throws OrderException{
        int bookStock = sqlDatabaseManager.getStockDatabaseManager().getBookStock(bookId);
        if (bookStock == 0){
            throw new OrderException("Book " + bookId + "is out of stock", OrderExceptionType.BOOK_OUT_OF_STOCK);
        }
        bookIds.add(bookId);
    }

    public void removeFromCart(String bookId) {
        bookIds.remove(bookId);
    }

    public void finalizeCart(String address, String date, int totalPrice) throws OrderException {

        if (Objects.equals(date, "Choose Date")){
            throw new OrderException("You must choose date", OrderExceptionType.DATE_EMPTY);
        }
        else if (address.equals("")){
            throw new OrderException("You must enter your address", OrderExceptionType.ADDRESS_EMPTY);
        }

        User loggedInUser = sqlDatabaseManager.getUserDatabaseManager().getLoggedInUser();

        SQLiteDatabase sqLiteDatabase = sqlDatabaseManager.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME_FIELD, loggedInUser.getUsername());
        contentValues.put(BOOK_IDS_FIELD, BookIdListJSONConverter.bookIdListToJSON(bookIds).toString());
        contentValues.put(ADDRESS_FIELD, address);
        contentValues.put(DATE_FIELD, date);
        contentValues.put(TOTAL_PRICE_FIELD, totalPrice);

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        reduceBookStock();

    }

    private void reduceBookStock() {
        for (String bookId : bookIds) {
            sqlDatabaseManager.getStockDatabaseManager().reduceStock(bookId, 1);
        }
    }


    public ArrayList<Cart> getUsersCarts() {
        User loggedInUser = sqlDatabaseManager.getUserDatabaseManager().getLoggedInUser();

        SQLiteDatabase sqLiteDatabase = sqlDatabaseManager.getReadableDatabase();

        StringBuilder sql;
        sql = new StringBuilder()
                .append("SELECT * FROM ")
                .append(TABLE_NAME)
                .append(" WHERE ")
                .append(USER_NAME_FIELD)
                .append(" = ? ");


        Cursor result = sqLiteDatabase.rawQuery(sql.toString(), new String[]{loggedInUser.getUsername()});
        ArrayList<Cart> cartList = new ArrayList<>();

        while (result.moveToNext()) {
            String cartId = result.getString(0);
            String bookIdsString = result.getString(2);
            String address = result.getString(3);
            String date = result.getString(4);
            int totalPrice = result.getInt(5);


            ArrayList<String> cartBookIds = null;
            try {
                cartBookIds = BookIdListJSONConverter.JSONArrayToBookIdList(new JSONArray(bookIdsString));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }


            Cart newCart = new Cart(cartId, address, date, totalPrice, cartBookIds);
            cartList.add(newCart);


        }
        return cartList;

    }

    public int getTotalCost() {
        PriceDatabaseManager priceDatabaseManager = sqlDatabaseManager.getPriceDatabaseManager();
        int totalPrice = 0;
        for (String bookId : bookIds) {
            totalPrice += priceDatabaseManager.getBookPrice(bookId);
        }
        return totalPrice;
    }


}
