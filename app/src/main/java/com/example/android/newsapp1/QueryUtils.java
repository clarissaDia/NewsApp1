package com.example.android.newsapp1;

import android.text.TextUtils;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.example.android.newsapp1.MainActivity.LOG_TAG;

public final class QueryUtils {

private QueryUtils () {
}
    public static List<News> fetchNewsData (String requestUrl){
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        }catch (IOException e){
            Log.e (LOG_TAG, "Problem making HTTP request", e);
        }
        List<News> newsArray = extractFeatureFromJson(jsonResponse);
        return newsArray;
    }

    public static List<News> extractFeatureFromJson(String newsJSON) {
        if (TextUtils.isEmpty(newsJSON)){
            return null;
        }
        List<News> news = new ArrayList<News>();

        try {
            JSONObject baseJsonResponse = new JSONObject(newsJSON);
            JSONObject responseObject = baseJsonResponse.getJSONObject("response");
            JSONArray newsArray = responseObject.getJSONArray("results");

            for (int i = 0; i <newsArray.length(); i++) {
                JSONObject currentNews = newsArray.getJSONObject(i);
                String section = currentNews.getString("sectionId");
                String date = currentNews.getString("webPublicationDate");
                String title = currentNews.getString("webTitle");
                String url = currentNews.getString("webUrl");

                News books = new News (section, date, title,
                        url);
                news.add(books);
        }
        }catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the news JSON results", e);
        }
        return news;
}
    private static URL createUrl (String stringUrl){
        URL url = null;
        try {
            url = new URL(stringUrl);
        }catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the url", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse="";
        if (url==null){
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode()==200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream (inputStream);
            }else {
                Log.e (LOG_TAG,"Error response code" + urlConnection.getResponseCode());
            }

        }catch (IOException e){
            Log.e(LOG_TAG,"Problem retrieving the news JSON results", e);
        }finally {
            if(urlConnection!= null){
                urlConnection.disconnect();
            }
            if (inputStream !=null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream (InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream !=null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null){
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}