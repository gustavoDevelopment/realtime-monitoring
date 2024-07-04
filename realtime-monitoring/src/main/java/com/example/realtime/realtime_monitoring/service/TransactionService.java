package com.example.realtime.realtime_monitoring.service;

import com.example.realtime.realtime_monitoring.helper.ResponseGenerator;
import com.example.realtime.realtime_monitoring.model.dto.TransactionsPerMinuteDTO;
import com.example.realtime.realtime_monitoring.model.entity.Transaction;
import com.example.realtime.realtime_monitoring.repository.TransactionRepository;
import com.example.realtime.realtime_monitoring.service.impl.ITransactionService;
import com.example.realtime.realtime_monitoring.util.ConstantCode;
import com.example.realtime.realtime_monitoring.util.ConstantMessage;
import com.example.realtime.realtime_monitoring.util.ResponseGeneric;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService  implements ITransactionService {
  @Autowired
  private TransactionRepository transactionRepository;

  @Override
  public ResponseGeneric<Serializable> doOnGetAllTransactions() throws JsonProcessingException {
    List<Transaction> transactions=  transactionRepository.findAll();;
    if(!transactions.isEmpty())
      return ResponseGenerator.doOnGenerateResponse(ConstantCode.SUCCESS, ConstantMessage.SUCCESS,transactions);
    return ResponseGenerator.doOnGenerateResponse(ConstantCode.SUCCESS, ConstantMessage.NOT_FOUND_DATA);
  }


  @Override
  public ResponseGeneric<Serializable> doOnGetTransactionsByStatus(String status) throws JsonProcessingException {
    List<Transaction> transactions= transactionRepository.findByStatus(status);
    if(transactions!=null && !transactions.isEmpty())
      return ResponseGenerator.doOnGenerateResponse(ConstantCode.SUCCESS, ConstantMessage.SUCCESS,transactions);
    return ResponseGenerator.doOnGenerateResponse(ConstantCode.VALIDATE_ERROR, ConstantMessage.NOT_FOUND_DATA);
  }

  @Override
  public ResponseGeneric<Serializable> doOnGetTransactionsByUserId(String userId) throws JsonProcessingException {
    List<Transaction> transactions= transactionRepository.findByUserId(userId);
    if(transactions!=null && !transactions.isEmpty())
      return ResponseGenerator.doOnGenerateResponse(ConstantCode.SUCCESS, ConstantMessage.SUCCESS, transactions);
    return ResponseGenerator.doOnGenerateResponse(ConstantCode.VALIDATE_ERROR, ConstantMessage.NOT_FOUND_DATA);
  }
  @Override
  public ResponseGeneric<Serializable> doOnGetTransactionsPerMinute() {
    List<Object[]> results = transactionRepository.countTransactionsPerMinute();
    List<TransactionsPerMinuteDTO> transactionsPerMinuteDTO = results.stream()
            .map(result -> new TransactionsPerMinuteDTO(
                    ((Number) result[0]).longValue(), // Assuming count is returned as a Number
                    (String) result[1]                // Assuming minute is returned as a String
            ))
            .collect(Collectors.toList());
    if(!transactionsPerMinuteDTO.isEmpty())
      return ResponseGenerator.doOnGenerateResponse(ConstantCode.SUCCESS, ConstantMessage.SUCCESS, transactionsPerMinuteDTO);
    return ResponseGenerator.doOnGenerateResponse(ConstantCode.VALIDATE_ERROR, ConstantMessage.NOT_FOUND_DATA);
  }

}
