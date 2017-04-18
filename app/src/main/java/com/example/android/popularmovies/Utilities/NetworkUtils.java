package com.example.android.popularmovies.Utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Ugochukwu on 4/18/2017.
 */

public class NetworkUtils {

    private static final String URL_FOR_DATA = "http://api.themoviedb.org/3/movie/popular";
    private static final String URL_FOR_DATA1 = "http://api.themoviedb.org/3/movie/top_rated";
    private static final String API_KEY = "api_key";

    //Enter your themoviedb.com api key here
    private static final String API_KEY_VALUE = "my_api_key";
    private static Uri uri;

    public static URL buildUrl(String category){
        if (category == "popular"){
            uri = Uri.parse(URL_FOR_DATA).buildUpon()
                    .appendQueryParameter(API_KEY, API_KEY_VALUE)
                    .build();
        }else if(category == "top rated"){
            uri = Uri.parse(URL_FOR_DATA1).buildUpon()
                    .appendQueryParameter(API_KEY, API_KEY_VALUE)
                    .build();
        }

        URL url = null;
        try {
            url = new URL(uri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }


}
