package com.client.service;

import com.client.entitiy.Message;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenAiLanguageModelService implements LanguageModelService {
//    private final Map<String, List<Message>> userSessions;
    private Map<String, List<Message>> userSessions = new HashMap<>();

    public OpenAiLanguageModelService(Map<String, List<Message>> userSessions) {
        this.userSessions = userSessions;
    }

    @Override
    public ResponseEntity<String> sendMessage(List<Message> sessionMessages) {
        // 这里可以根据需要实现使用 OpenAI 语言模型的逻辑，你可以参考之前的 OpenAiApiController 代码。
        // 在这个方法中，使用 userMessage 和 userSessionId 进行与语言模型的交互。
        // 你可以重用之前的代码来构造 payload、发送请求和解析响应。
        // 最后返回模型的响应消息。
        //设置接下来和openai api交互 所需要的变量
        String apiKeyPart1 = "sk-s4aXPRMzxudBZX";
        String apiKeyPart2 = "jzG5vgT3BlbkFJ43u";
        String apiKeyPart3 = "K199Cr6hdBYyvbyGT";
//        String apiKey = System.getenv("OPENAI_API_KEY");
        String apiKey = apiKeyPart1 + apiKeyPart2 + apiKeyPart3;
        String apiUrl = "https://api.openai.com/v1/chat/completions";

        //可以联系上下文的版本 设置payload
        // Construct the payload using all the messages in the session
        StringBuilder payloadBuilder = new StringBuilder("{ \"model\": \"gpt-3.5-turbo-16k\", \"messages\": [");
        for (Message msg : sessionMessages) {
            payloadBuilder.append("{\"role\": \"").append(msg.getRole()).append("\", \"content\": \"").append(msg.getContent()).append("\"},");
        }
        payloadBuilder.deleteCharAt(payloadBuilder.length() - 1);  // Remove trailing comma
        payloadBuilder.append("]}");
        String payload = payloadBuilder.toString();

        //与openai api交互，发送payload
        try {
//            System.out.println(apiKey);
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

//            int responseCode = connection.getResponseCode();
//            System.out.println("HTTP Response Code: " + responseCode);

            //接收gpt的回复
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                // Extracting assistant's message from the response
                JSONObject jsonResponse = new JSONObject(response.toString());
                String assistantMessage = jsonResponse.getJSONArray("choices")
                        .getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content");

                return ResponseEntity.ok(assistantMessage);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal Server Error");
        }

    }
}
