package com.example.realtime.realtime_monitoring.component;

import com.example.realtime.realtime_monitoring.model.entity.Transaction;
import com.example.realtime.realtime_monitoring.repository.TransactionRepository;
import com.example.realtime.realtime_monitoring.service.impl.INotificationService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TransactionProcessingService {
  @Value("${transaction.success.status}")
  private String failureStatus;
  @Value("${transaction.high.value}")
  private double highTransactionValue;
  @Value("${transaction.log.pattern}")
  private String logPatternConfig;
  private Pattern logPattern;

  private final BlockingQueue<String> logQueue;
  private final TransactionRepository transactionRepository;

  private final INotificationService iNotificationService;

  public TransactionProcessingService(BlockingQueue<String> logQueue,
                                      TransactionRepository transactionRepository,
                                      INotificationService iNotificationService) {
    this.logQueue = logQueue;
    this.transactionRepository = transactionRepository;
    this.iNotificationService = iNotificationService;
  }

  @PostConstruct
  public void start() {
    logPattern = Pattern.compile(logPatternConfig);
    Thread thread = new Thread(this::processLogs);
    thread.start();
  }

  private void processLogs() {
    while (true) {
      try {
        String logEntry = logQueue.take();
        Transaction transaction = parseLogEntry(logEntry);
        if (transaction != null) {
          transactionRepository.save(transaction);
          handleNotifications(transaction);
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  private Transaction parseLogEntry(String logEntry) {
    Matcher matcher = logPattern.matcher(logEntry);
    if (matcher.matches()) {
      try {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(matcher.group(2));
        transaction.setUserId(matcher.group(3));
        transaction.setAmount(Double.parseDouble(matcher.group(4)));
        transaction.setStatus(matcher.group(5));
        transaction.setTimestamp(matcher.group(6));
        transaction.setLocation(matcher.group(7));
        return transaction;
      } catch (NumberFormatException e) {
        System.err.println("Error parsing transaction amount: " + e.getMessage());
      }
    } else {
      System.err.println("Invalid log entry format: " + logEntry);
    }
    return null;
  }

  private void handleNotifications(Transaction transaction) {
    if (failureStatus.equals(transaction.getStatus())) {
      iNotificationService.sendFailureNotification(transaction);
    }
    if (transaction.getAmount() > highTransactionValue) {
      iNotificationService.sendHighValueNotification(transaction);
    }
  }
}
