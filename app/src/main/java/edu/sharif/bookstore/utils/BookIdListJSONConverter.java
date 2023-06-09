package edu.sharif.bookstore.utils;

import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;

public class BookIdListJSONConverter {

    public static JSONArray bookIdListToJSON(ArrayList<String> bookIds){
        JSONArray bookIdJSONArray = new JSONArray();
        for (String bookId : bookIds) {
            bookIdJSONArray.put(bookId);
        }
        return bookIdJSONArray;
    }

    public static ArrayList<String> JSONArrayToBookIdList(JSONArray jsonArray){
        ArrayList<String> bookIds = new ArrayList<String>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                bookIds.add(jsonArray.getString(i));
            } catch (Exception e){
                Log.d("PARSE_BOOK_ID_JSON_ERR", "out of bound exception!");
                e.printStackTrace();
            }
        }
        return bookIds;
    }
}
