package com.example.realtime.realtime_monitoring.controller;


import com.example.realtime.realtime_monitoring.service.impl.ITransactionService;
import com.example.realtime.realtime_monitoring.service.impl.ITransactionSummaryService;
import com.example.realtime.realtime_monitoring.util.ResponseGeneric;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
public class TransactionController {
  private final ITransactionSummaryService itransactionSummaryService;

  private final ITransactionService iTransactionService;

  public TransactionController(ITransactionSummaryService itransactionSummaryService, ITransactionService iTransactionService) {
    this.itransactionSummaryService = itransactionSummaryService;
    this.iTransactionService = iTransactionService;
  }

  @GetMapping("/transactions")
  public ResponseGeneric<Serializable> getTransactions() throws JsonProcessingException {
    return iTransactionService.doOnGetAllTransactions();
  }
  @GetMapping("/summary")
  public ResponseGeneric<Serializable> getTransactionSummary() {
    return itransactionSummaryService.getTransactionSummary();
  }

  @GetMapping("/status/{status}")
  public ResponseGeneric<Serializable> getTransactionsByStatus(@PathVariable String status) throws JsonProcessingException {
    return iTransactionService.doOnGetTransactionsByStatus(status);
  }

  @GetMapping("/user/{userId}")
  public ResponseGeneric<Serializable> getTransactionsByUserId(@PathVariable String userId) throws JsonProcessingException {
    return iTransactionService.doOnGetTransactionsByUserId(userId);
  }
  @GetMapping("/trends")
  public ResponseGeneric<Serializable> getTransactionsPerMinute() {
    return iTransactionService.doOnGetTransactionsPerMinute();
  }

}
