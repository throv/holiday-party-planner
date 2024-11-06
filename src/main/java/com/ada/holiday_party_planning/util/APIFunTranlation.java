package com.ada.holiday_party_planning.util;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIFunTranlation {
    private String endPoint = "https://api.funtranslations.com/translate/";

    public String tranlateFun (String message, String category) {
        endPoint  += category + ".json";

        try {
            URL url = new URL(endPoint);
            System.out.println(endPoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);

            String jsonInputString = String.format("{\"text\":\"%s\"}", message);

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
                JSONObject contents = jsonResponse.getJSONObject("contents");
                String translatedText = contents.getString("translated");

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
