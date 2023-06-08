package edu.sharif.bookstore.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import edu.sharif.bookstore.entity.Book;

public class BookJsonParserUtil {
    private static final String LOG_TAG = BookJsonParserUtil.class.getSimpleName();

    public static ArrayList<Book> booksJsonArrayIntoBooksObjectArray(String booksJson) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(booksJson);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject bookJSON = itemsArray.getJSONObject(i);
                try {
                    Book bookObject = bookJsonIntoBookObject(bookJSON.toString());
                    books.add(bookObject);
                } catch (JSONException e){
                    Log.d(LOG_TAG, "book missing fields!");
                    e.printStackTrace();
                }
            }
            return books;
        } catch (Exception e) {
            Log.d(LOG_TAG, "Search Result Empty! (Or json parse error ! :| )");
            e.printStackTrace();
            return books;
        }
    }

    public static Book bookJsonIntoBookObject(String bookJson) throws JSONException {
        JSONObject bookObject = new JSONObject(bookJson);
        String bookId = bookObject.getString("id");
        JSONObject volumeInfo = bookObject.getJSONObject("volumeInfo");
        ArrayList<String> authors = new ArrayList<String>();
        String publisher = volumeInfo.getString("publisher");
        String title = volumeInfo.getString("title");
        JSONArray authorsJSON = volumeInfo.getJSONArray("authors");
        for (int i = 0; i < authorsJSON.length(); i++) {
            authors.add(authorsJSON.getString(i));
        }
        int pageCount = volumeInfo.getInt("pageCount");
        JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
        String description = volumeInfo.getString("description");
        String category = volumeInfo.getJSONArray("categories").getString(0);
        String imageLink = imageLinks.getString("thumbnail");
        String datePublished = volumeInfo.getString("publishedDate");
        return new Book(bookId, title, authors, publisher, datePublished, description, category, imageLink, pageCount);
    }
}
