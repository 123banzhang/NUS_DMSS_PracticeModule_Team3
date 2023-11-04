package com.client.service;

import com.client.entitiy.Message;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class AnotherLanguageModelService implements LanguageModelService {
    @Override
    public ResponseEntity<String> sendMessage(List<Message> sessionMessages) {
        return null;
    }
}
