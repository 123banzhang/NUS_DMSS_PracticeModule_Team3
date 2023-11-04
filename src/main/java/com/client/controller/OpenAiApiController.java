package com.client.controller;

import com.client.entitiy.Message;
import com.client.entitiy.ChatRequest;
import com.client.service.AnotherLanguageModelService;
import com.client.service.LanguageModelService;
import com.client.service.OpenAiLanguageModelService;
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

    // 将MetaHuman的详细信息转换为提示
    private String convertMetahumanToPrompt(MetahumanDetailVo metahuman) {
        // 基于你的需求和Metahuman的属性，构造合适的提示
        return String.format("Currently, your name is set as %s, identified by the gender %s. Below are the specific tasks you are expected to perform, the knowledge base you should rely on, or the guidelines on how users anticipate your responses: %s. This profile was created on %s and last updated on %s.",
                metahuman.getName(), metahuman.getGender(), removeNewlinesFromPrompt(metahuman.getDescription()), metahuman.getCreateTime(), metahuman.getUpdateTime());
    }

    private String removeNewlinesFromPrompt(String prompt) {
        // 将 prompt 中的换行符替换为空格
        return prompt.replace("\n", " ");
    }

    @Autowired
    private LanguageModelService languageModelService;

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

        //在运行时，根据配置的 language.model.type 值，动态选择要使用的语言模型服务类。这可以通过条件语句或工厂模式来实现。
//        String languageModelType = request.getChatModel();

        String languageModelType = "openai";
        LanguageModelService selectedLanguageModel;
        switch (languageModelType) {
            case "openai":
                selectedLanguageModel = new OpenAiLanguageModelService(userSessions);
                break;
            case "another":
                selectedLanguageModel = new AnotherLanguageModelService();
                break;
            default:
                // 默认情况下选择 OpenAI 语言模型
                selectedLanguageModel = new OpenAiLanguageModelService(userSessions);
                break;
        }

//        String languageModelType = "openai";
//        if ("openai".equals(languageModelType)) {
//            languageModelService = new OpenAiLanguageModelService(userSessions);
//        } else if ("another".equals(languageModelType)) {
//            languageModelService = new AnotherLanguageModelService();
//        }

        // 使用 LanguageModelService 与语言模型进行交互
        ResponseEntity<String> modelResponse = languageModelService.sendMessage(sessionMessages);
        // 返回模型的响应
        return modelResponse;
    }
}