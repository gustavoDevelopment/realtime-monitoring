package com.example.realtime.realtime_monitoring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionSummaryDto implements Serializable {
  private double totalAmountProcessed;
  private int successfulTransactions;
  private int failedTransactions;
  private List<TransactionUserSummaryDto> transactionsPerUser;
}
