package edu.sharif.bookstore;

import android.net.Uri;
import android.util.Log;



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
    static String getBookInfo(String queryString) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;

        try {
            Uri builtUri = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULT, "10")
                    .appendQueryParameter(PRINT_TYPE, "books")
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
}
