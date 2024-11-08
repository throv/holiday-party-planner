package com.ada.holiday_party_planning.util;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Utilitário para tradução de mensagens usando a API FunTranslations.
 *
 * Essa classe permite traduzir um texto para uma categoria específica utilizando a API FunTranslations.
 * O método `tranlateFun` faz uma solicitação POST para a API, enviando o texto a ser traduzido e
 * retornando o texto traduzido como resposta.
 *
 * A API FunTranslations suporta diversas categorias de tradução e pode ser usada para traduzir
 * mensagens de forma divertida, com base em diferentes estilos ou categorias, como "pirata", "yoda", etc.
 */

public class APIFunTranlation {

    /**
     * Traduz uma mensagem para a categoria especificada utilizando a API FunTranslations.
     *
     * Esse método faz uma solicitação POST para a API, enviando o texto e a categoria para tradução.
     * Se a tradução for bem-sucedida, o texto traduzido é retornado. Caso contrário, um erro é exibido.
     *
     * @param message A mensagem a ser traduzida.
     * @param category A categoria para qual a mensagem deve ser traduzida, como "pirate", "yoda", etc.
     * @return O texto traduzido se a solicitação for bem-sucedida, ou `null` em caso de erro.
     */

    public static String tranlateFun (String message, String category) {
        String endPoint = "https://api.funtranslations.com/translate/";
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
