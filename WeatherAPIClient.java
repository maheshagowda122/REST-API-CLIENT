package com.tap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 * WeatherAPIClient
 * 
 * A simple Java application to consume a public REST API
 * (OpenWeatherMap) and display current weather information.
 * 
 * Part of CODTECH Internship Deliverables: REST API CLIENT
 */


public class WeatherAPIClient {

    private static final String API_KEY = "053d4615165c268d8b2e003ce4f6be3a";
    private static final String CITY = "Mumbai";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public static void main(String[] args) {
        try {
            // Construct the full URL with parameters
            String urlString = BASE_URL + "?q=" + CITY + "&appid=" + API_KEY;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse JSON and print weather info
                JSONObject jsonResponse = new JSONObject(response.toString());
                System.out.println("Weather in " + CITY + ": " + jsonResponse.getJSONArray("weather").getJSONObject(0).getString("description"));
            } else {
                System.out.println("API request failed. Response code: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}