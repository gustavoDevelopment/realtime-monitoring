package com.example.realtime.realtime_monitoring.repository;



import com.example.realtime.realtime_monitoring.model.dto.TransactionUserSummaryDto;
import com.example.realtime.realtime_monitoring.model.dto.TransactionsPerMinuteDTO;
import com.example.realtime.realtime_monitoring.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

  @Query("SELECT SUM(t.amount) FROM Transaction t")
  double getTotalAmountProcessed();

  int countByStatus(String status);

  @Query("SELECT new com.example.realtime.realtime_monitoring.model.dto.TransactionUserSummaryDto(t.userId, COUNT(t)) FROM Transaction t GROUP BY t.userId")
  List<TransactionUserSummaryDto> getTransactionsPerUser();
  List<Transaction> findByStatus(String status);

  List<Transaction> findByUserId(String userId);
  @Query("SELECT COUNT(t), FORMATDATETIME(t.timestamp, 'yyyy-MM-dd HH:mm') FROM Transaction t GROUP BY FORMATDATETIME(t.timestamp, 'yyyy-MM-dd HH:mm') ORDER BY FORMATDATETIME(t.timestamp, 'yyyy-MM-dd HH:mm')")
  List<Object[]> countTransactionsPerMinute();


}
