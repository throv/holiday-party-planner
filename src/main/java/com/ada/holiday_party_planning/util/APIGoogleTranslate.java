package com.ada.holiday_party_planning.util;


import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIGoogleTranslate {

    private String apiKey = System.getenv("GOOGLE_API_KEY");
    private String endPoint = "https://translation.googleapis.com/language/translate/v2?key=" + apiKey;

    public String translateMensage(String message, String sourceLang, String targetLang) {

        try {
            URL url = new URL(endPoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);

            String jsonInputString = String.format(
                    "{\"q\":\"%s\", \"source\":\"%s\", \"target\":\"%s\", \"format\":\"text\"}",
                    message, sourceLang, targetLang
            );

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                StringBuilder response = new StringBuilder();
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                }

                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONArray translations = jsonResponse.getJSONObject("data").getJSONArray("translations");
                String translatedText = translations.getJSONObject(0).getString("translatedText");

                return translatedText;

            } else {
                System.out.println("ERRO: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}





