package com.example.realtime.realtime_monitoring.service.impl;

import com.example.realtime.realtime_monitoring.model.entity.Transaction;

public interface INotificationService {
  void sendFailureNotification(Transaction transaction);

  void sendHighValueNotification(Transaction transaction);
}
