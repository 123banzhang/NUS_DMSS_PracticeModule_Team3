package com.client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class OpenAiApiExample {
    public static void main(String[] args) {
        String apiKey = "";
        String apiUrl = "https://api.openai.com/v1/chat/completions";
        String payload = "{"
                + "\"model\": \"gpt-3.5-turbo\","
                + "\"messages\": ["
                + "  {\"role\": \"system\", \"content\": \"You are a helpful assistant.\"},"
                + "  {\"role\": \"user\", \"content\": \"把一下中文翻译成英文: '你好，我的小助手。'\"}"
                + "]"
                + "}";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = payload.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("HTTP Response Code: " + responseCode);

            // Read and print the response body
            BufferedReader br = null;
            if (responseCode == 200) { // If successful
                br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            } else { // If failed
                br = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "utf-8"));
            }

            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            //api回答问题的完整内容
//            System.out.println("Response: " + response.toString());

            // 新代码: 解析 JSON 响应并获取特定的消息内容
            JSONObject jsonResponse = new JSONObject(response.toString());
            String assistantMessage = jsonResponse.getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");
            System.out.println("Assistant's message: " + assistantMessage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


