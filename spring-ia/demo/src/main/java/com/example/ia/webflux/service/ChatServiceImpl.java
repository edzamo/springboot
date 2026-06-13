package com.example.ia.webflux.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

  private final ChatClient chatClient;

  @Value("${app.ai.query-template}")
  private String queryStr;

  @Override
  public String chatTemplate(String query) {
    return chatClient
      .prompt()
      .advisors(new SimpleLoggerAdvisor())
      .user(u -> u.text(queryStr).param("query", queryStr))
      .call()
      .content();
  }
}
