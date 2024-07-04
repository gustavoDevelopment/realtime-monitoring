package com.example.realtime.realtime_monitoring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class AppConfig {

  @Bean
  public BlockingQueue<String> logQueue() {
    return new LinkedBlockingQueue<>();
  }
}
