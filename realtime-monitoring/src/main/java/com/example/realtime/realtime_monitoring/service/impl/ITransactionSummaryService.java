package com.example.realtime.realtime_monitoring.service.impl;

import com.example.realtime.realtime_monitoring.util.ResponseGeneric;

import java.io.Serializable;

public interface ITransactionSummaryService {
  ResponseGeneric<Serializable> getTransactionSummary();
}
