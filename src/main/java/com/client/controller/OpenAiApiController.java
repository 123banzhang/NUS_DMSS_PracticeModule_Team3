package com.client.controller;

import com.client.entitiy.Message;
import com.client.entitiy.ChatRequest;
import com.sys.service.impl.MetahumanServiceImpl;
import com.sys.vo.MetahumanDetailVo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api")
public class OpenAiApiController {
    private final Map<String, List<Message>> userSessions = new HashMap<>();

    @Autowired
    private MetahumanServiceImpl metahumanService;  // 注入MetahumanServiceImpl

    //    private String generatePromptFromMetahumanDetail(MetahumanDetailVo detail) {
//        // 基于你的需求和Metahuman的属性，构造合适的提示
//        return String.format("你正在与%s对话，他/她是%s，简介为：%s。",
//                detail.getName(), detail.getCategory(), detail.getDescription());
//    }
    // 将MetaHuman的详细信息转换为提示
    private String convertMetahumanToPrompt(MetahumanDetailVo metahuman) {
        // 基于你的需求和Metahuman的属性，构造合适的提示
        return String.format("Currently, your name is set as %s, identified by the gender %s. Below are the specific tasks you are expected to perform, the knowledge base you should rely on, or the guidelines on how users anticipate your responses: %s. This profile was created on %s and last updated on %s.",
                metahuman.getName(), metahuman.getGender(), metahuman.getDescription(), metahuman.getCreateTime(), metahuman.getUpdateTime());
    }

    @PostMapping("/completion")
    public ResponseEntity<String> getCompletion(@RequestBody ChatRequest request) {

        long metahumanId = request.getMetahumanId();
        MetahumanDetailVo metahumanDetail = metahumanService.findMetahumanDetailVoById(metahumanId);
        // 检查metahumanDetail是否为空
        if (metahumanDetail == null) {
            // Handle the error (e.g., return an error response or throw an exception)
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx!!!");
            return ResponseEntity.badRequest().body("Error: MetaHuman not found");
        }

        // 转换Metahuman详细信息为提示
        String userSessionId = request.getSessionId();
        String userMessageContent = request.getUserMessage();

        String prompt = convertMetahumanToPrompt(metahumanDetail);
        //将提示作为第一条消息，然后将用户的消息添加到聊天列表中,之后使用这个sessionMessages列表与GPT API进行交互。
        Message initialMessage = new Message("system", prompt);

        //有上下文记忆，但是切换metahuman，就会仍然停留在上一个metahuman
//        List<Message> sessionMessages = userSessions.getOrDefault(userSessionId, new ArrayList<>());
        //没有上下文记忆
        List<Message> sessionMessages = new ArrayList<>();

        sessionMessages.add(initialMessage);

        Message userMessage = new Message("user", userMessageContent);
        sessionMessages.add(userMessage);

        userSessions.put(userSessionId, sessionMessages);


        //可以联系上下文v1.0
//        String userSessionId = request.getSessionId();
//        String userMessageContent = request.getUserMessage();
//
//        Message userMessage = new Message("user", userMessageContent);
//        List<Message> sessionMessages = userSessions.getOrDefault(userSessionId, new ArrayList<>());
//        sessionMessages.add(userMessage);
//        userSessions.put(userSessionId, sessionMessages);

        //设置接下来和openai api交互 所需要的变量
        String apiKeyPart1 = "sk-yx3aZJWYqgZ7";
        String apiKeyPart2 = "MzR4qiz6T3BlbkFJMH";
        String apiKeyPart3 = "ur8TPYBmvEdSAlammS";
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
