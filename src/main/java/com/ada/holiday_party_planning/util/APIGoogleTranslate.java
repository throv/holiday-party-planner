package com.ada.holiday_party_planning.util;


import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.PublicKey;

/**
 * Utilitário para tradução de mensagens usando a API Google Translate.
 *
 * Esta classe permite traduzir uma mensagem de um idioma de origem para um idioma de destino
 * utilizando a API Google Translate. O método `translateMensage` envia uma solicitação POST
 * para a API, especificando a mensagem, o idioma de origem e o idioma de destino.
 *
 * A tradução retornada pela API é extraída e retornada como o texto traduzido.
 */

public class APIGoogleTranslate {

    /**
     * Traduz uma mensagem usando a API Google Translate.
     *
     * Esse método envia uma solicitação POST para a API Google Translate, especificando a mensagem
     * a ser traduzida, o idioma de origem e o idioma de destino. O texto traduzido é retornado como
     * resposta. Em caso de erro, o método imprime o código de erro da resposta.
     *
     * @param message O texto a ser traduzido.
     * @param sourceLang O código do idioma de origem (ex: "en" para inglês).
     * @param targetLang O código do idioma de destino (ex: "es" para espanhol).
     * @return O texto traduzido se a solicitação for bem-sucedida, ou `null` em caso de erro.
     */

    public static String translateMensage(String message, String sourceLang, String targetLang) {
        String apiKey = System.getenv("GOOGLE_API_KEY");
        String endPoint = "https://translation.googleapis.com/language/translate/v2?key=" + apiKey;
        System.out.println(endPoint);

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





