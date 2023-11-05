package com.client.config;

import com.client.entitiy.Message;
import com.client.service.LanguageModelService;
import com.client.service.OpenAiLanguageModelService;
import com.client.service.AnotherLanguageModelService; // 如果有多个语言模型服务

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

@Configuration
public class LanguageModelConfig {

    // 创建一个 Map 用于管理不同的用户会话
    @Bean
    public Map<String, List<Message>> userSessions() {
        return new HashMap<>();
    }

    // 创建 OpenAI 语言模型服务的 bean
//    @Bean
//    public LanguageModelService openAiLanguageModelService(Map<String, List<Message>> userSessions) {
//        return new OpenAiLanguageModelService(userSessions);
//    }

    // 如果有多个语言模型服务，可以创建其他 bean
    // @Bean
    // public LanguageModelService anotherLanguageModelService(Map<String, List<Message>> userSessions) {
    //     return new AnotherLanguageModelService(userSessions);
    // }
}
