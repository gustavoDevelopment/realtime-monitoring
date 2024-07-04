package com.example.realtime.realtime_monitoring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionUserSummaryDto implements Serializable {
  private String userId;
  private Long transactionCount;
}
