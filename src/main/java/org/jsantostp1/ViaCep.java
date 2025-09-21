package org.jsantostp1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViaCep {

    public static String consultaCep(String cep) {
        String urlString = "https://viacep.com.br/ws/" + cep + "/json/";
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                }
            } else {
                throw new RuntimeException("Erro na requisição: " + conn.getResponseCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static boolean validateCep(String cep) {
        return cep != null && cep.matches("\\d{8}");
    }

    public static void main(String[] args) {
        String cep = "20271220";
        String resposta = consultaCep(cep);
        System.out.println("Resposta da API: " + resposta);
    }
}

