package com.client.controller;

import com.client.entitiy.MessageRequest;
import com.client.entitiy.Message;
import com.client.entitiy.ChatRequest;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.*;
import java.util.*;



@RestController
@CrossOrigin
public class OpenAiApiController {
    private final Map<String, List<Message>> userSessions = new HashMap<>();

    @PostMapping("/api/completion")
//    public ResponseEntity<String> getCompletion(@RequestBody String userMessage) {
//    public ResponseEntity<String> getCompletion(@RequestBody MessageRequest request) {
    public ResponseEntity<String> getCompletion(@RequestBody ChatRequest request) {
        //可以联系上下文的版本
        String userSessionId = request.getSessionId();
        String userMessageContent = request.getUserMessage();

        Message userMessage = new Message("user", userMessageContent);
        List<Message> sessionMessages = userSessions.getOrDefault(userSessionId, new ArrayList<>());
        sessionMessages.add(userMessage);
        userSessions.put(userSessionId, sessionMessages);

//        String userMessage = request.getUserMessage();
        String apiKeyPart1 = "sk-yx3aZJWYqgZ7";
        String apiKeyPart2 = "MzR4qiz6T3BlbkFJMH";
        String apiKeyPart3 = "ur8TPYBmvEdSAlammS";
//        String apiKey = System.getenv("OPENAI_API_KEY");
        String apiKey = apiKeyPart1 + apiKeyPart2 + apiKeyPart3;
        String apiUrl = "https://api.openai.com/v1/chat/completions";
        //可以联系上下文的版本
        // Construct the payload using all the messages in the session
        StringBuilder payloadBuilder = new StringBuilder("{ \"model\": \"gpt-3.5-turbo\", \"messages\": [");
        for (Message msg : sessionMessages) {
            payloadBuilder.append("{\"role\": \"").append(msg.getRole()).append("\", \"content\": \"").append(msg.getContent()).append("\"},");
        }
        payloadBuilder.deleteCharAt(payloadBuilder.length() - 1);  // Remove trailing comma
        payloadBuilder.append("]}");
        String payload = payloadBuilder.toString();
        //不可以联系上下文的版本
//        String payload = "{"
//                + "\"model\": \"gpt-3.5-turbo\","
//                + "\"messages\": ["
//                + "  {\"role\": \"system\", \"content\": \"You are a helpful assistant.\"},"
//                + "  {\"role\": \"user\", \"content\": \"" + userMessage + "\"}"
//                + "]"
//                + "}";

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

            int responseCode = connection.getResponseCode();

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