package edu.sharif.bookstore.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    private static final String BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes";
    private static final String QUERY_PARAM = "q";
    private static final String MAX_RESULT = "maxResults";
    private static final String PRINT_TYPE = "printType";
    private static final String SORTING_TYPE = "orderBy";
    public static String searchBookWithQueryAndGetJsonString(String queryString, String queryType, String sortingType, String maxResult) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;
        if (queryType != null) {
            queryString = queryString + "+" + queryType;
        }
        try {
            Uri builtUri = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULT, maxResult)
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .appendQueryParameter(SORTING_TYPE, sortingType)
                    .build();
            URL requestURL = new URL(builtUri.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if(inputStream == null){
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                return null;
            }
            bookJSONString = buffer.toString();


        } catch(Exception e){
            Log.d(LOG_TAG, e.toString());
            e.printStackTrace();
            return null;
        } finally {
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.d(LOG_TAG, e.toString());
                    e.printStackTrace();
                }
            }
            System.out.println(bookJSONString);
            return bookJSONString;
        }
    }

    public static Drawable downloadBookThumbnailWithURL(String _url){
        URL url;
        BufferedOutputStream out;
        InputStream in;
        BufferedInputStream buf;
        _url = _url.substring(0,4) + "s" + _url.substring(4);

        try{
            url = new URL(_url);
            in = url.openStream();
            buf = new BufferedInputStream(in);
            Bitmap bMap = BitmapFactory.decodeStream(buf);
            if (in != null){
                buf.close();
            }
            if (buf != null) {
                buf.close();
            }
            return new BitmapDrawable(bMap);
        } catch (Exception e) {
            Log.d(LOG_TAG, e.toString());
            e.printStackTrace();
        }
        return null;
    }

    public static String getBookJsonStringById(String bookId){
        return searchBookWithQueryAndGetJsonString("id:" + bookId, null, "relevance","1");
    }
}

