package com.example.realtime.realtime_monitoring.service;

import com.example.realtime.realtime_monitoring.model.entity.Transaction;
import com.example.realtime.realtime_monitoring.service.impl.INotificationService;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements INotificationService {

  @Override
  public void sendFailureNotification(Transaction transaction) {
    System.out.println("Notification: Transaction failed! " + transaction);
  }

  @Override
  public void sendHighValueNotification(Transaction transaction) {
    System.out.println("Notification: High-value transaction! " + transaction);
  }
}
