package com.example.realtime.realtime_monitoring.service;

import com.example.realtime.realtime_monitoring.helper.ResponseGenerator;
import com.example.realtime.realtime_monitoring.model.dto.TransactionSummaryDto;
import com.example.realtime.realtime_monitoring.model.dto.TransactionUserSummaryDto;
import com.example.realtime.realtime_monitoring.repository.TransactionRepository;
import com.example.realtime.realtime_monitoring.service.impl.ITransactionSummaryService;
import com.example.realtime.realtime_monitoring.util.ConstantCode;
import com.example.realtime.realtime_monitoring.util.ConstantMessage;
import com.example.realtime.realtime_monitoring.util.ResponseGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class TransactionSummaryService implements ITransactionSummaryService {

  @Autowired
  private TransactionRepository transactionRepository;

  @Value("${transaction.success.status}")
  private String successStatus;

  @Value("${transaction.success.status}")
  private String failureStatus;

  @Override
  public ResponseGeneric<Serializable> getTransactionSummary() {
    TransactionSummaryDto summary = new TransactionSummaryDto();

    double totalAmountProcessed = transactionRepository.getTotalAmountProcessed();
    summary.setTotalAmountProcessed(totalAmountProcessed);

    int successfulCount = transactionRepository.countByStatus(successStatus);
    int failedCount = transactionRepository.countByStatus(failureStatus);
    summary.setSuccessfulTransactions(successfulCount);
    summary.setFailedTransactions(failedCount);

    List<TransactionUserSummaryDto> transactionsPerUser = transactionRepository.getTransactionsPerUser();
    summary.setTransactionsPerUser(transactionsPerUser);


    return ResponseGenerator.doOnGenerateResponse(ConstantCode.SUCCESS, ConstantMessage.SUCCESS, summary);
  }
}
