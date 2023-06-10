package edu.sharif.bookstore.utils;

import android.text.Html;
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
            System.out.println("Parsing " +itemsArray.length()+" Json objects!");
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
        //------------------
        String bookId;
        if (bookObject.has("selfLink")) {
            bookId = bookObject.getString("selfLink");
        } else { throw new JSONException("Book missing id link!"); }

        JSONObject volumeInfo;
        if (bookObject.has("volumeInfo")){
             volumeInfo = bookObject.getJSONObject("volumeInfo");
             ArrayList<String> authors = new ArrayList<String>();
             if (volumeInfo.has("authors")) {
                 JSONArray authorsJSON = volumeInfo.getJSONArray("authors");
                 for (int i = 0; i < authorsJSON.length(); i++) {
                     authors.add(authorsJSON.getString(i));
                 }
             }
             String publisher = "DEFAULT PUBLISHER";
             if (volumeInfo.has("publisher")){
                 publisher = volumeInfo.getString("publisher");
             }
             String title = "DEFAULT TITLE";
             if (volumeInfo.has("title")){
                 title = volumeInfo.getString("title");
             }
             int pageCount = 0;
             if (volumeInfo.has("pageCount")){
                 pageCount = volumeInfo.getInt("pageCount");
             }
             JSONObject imageLinks;
             String imageLink;
             if (volumeInfo.has("imageLinks")){
                 imageLinks = volumeInfo.getJSONObject("imageLinks");
                 imageLink = imageLinks.getString("thumbnail");
             } else { throw new JSONException("Book missing image!"); }
             String description = "DEFAULT DESCRIPTION!";
             if (volumeInfo.has("description")){
                 description = volumeInfo.getString("description");
                 description = Html.fromHtml(description).toString();
                 //description.replaceAll("<.*?>" , " ");
                 //description.replaceAll("&.*?;", "");
             }
             String category = "DEFAULT CATEGORY";
             if (volumeInfo.has("categories")) {
                 category = volumeInfo.getJSONArray("categories").getString(0);
             }
             String datePublished = "0000-00-00";
             if (volumeInfo.has("publishedDate")){
                 datePublished = volumeInfo.getString("publishedDate");
             }
            System.out.println("Parsing successful!");
            return new Book(bookId, title, authors, publisher, datePublished, description, category, imageLink, pageCount);
        } else { throw new JSONException("Book missing volume info!"); }
    }
}
