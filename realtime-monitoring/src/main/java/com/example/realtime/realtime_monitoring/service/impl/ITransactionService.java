package com.example.realtime.realtime_monitoring.service.impl;

import com.example.realtime.realtime_monitoring.util.ResponseGeneric;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.Serializable;

public interface ITransactionService  {

  ResponseGeneric<Serializable> doOnGetAllTransactions() throws JsonProcessingException;

  ResponseGeneric<Serializable> doOnGetTransactionsByStatus(String status) throws JsonProcessingException;

  ResponseGeneric<Serializable> doOnGetTransactionsByUserId(String userId) throws JsonProcessingException;

  ResponseGeneric<Serializable> doOnGetTransactionsPerMinute();
}
