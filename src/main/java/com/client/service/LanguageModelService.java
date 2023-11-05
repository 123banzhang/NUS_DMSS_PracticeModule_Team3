package com.client.service;

import com.client.entitiy.Message;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LanguageModelService {
//    String sendMessage(String message);
//    String sendMessage(String userMessage, String userSessionId);
    ResponseEntity<String> sendMessage(List<Message> sessionMessages);
}
