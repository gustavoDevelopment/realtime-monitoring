package com.example.realtime.realtime_monitoring.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction implements Serializable {
  @Id
  private String transactionId;

  @Column(name = "user_id")
  private String userId;
  private double amount;

  @Column(name = "status")
  private String status;
  private String timestamp;
  private String location;

  // Getters y setters (omitidos por brevedad)

  @Override
  public String toString() {
    return "Transaction{" +
            "transactionId='" + transactionId + '\'' +
            ", userId='" + userId + '\'' +
            ", amount=" + amount +
            ", status='" + status + '\'' +
            ", timestamp='" + timestamp + '\'' +
            ", location='" + location + '\'' +
            '}';
  }
}
