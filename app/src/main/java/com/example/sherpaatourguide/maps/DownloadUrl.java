package com.example.sherpaatourguide.maps;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadUrl {

    /**
     * This method will downloadd the URL from the google map places web api and return the data
     * */
    public String readUrl(String myUrl) throws IOException {
        String data = "";
        HttpURLConnection httpURLConnection = null;

        try {
            URL url = new URL(myUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            try (InputStream inputStream = httpURLConnection.getInputStream();
                 BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                StringBuffer sb = new StringBuffer();

                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                data = sb.toString();
            }

        } catch (MalformedURLException ex) {
            Log.i("DownladURL ", "readUrl" + ex.getMessage());
        } finally {
            httpURLConnection.disconnect();
        }

        return data;

    }
}

