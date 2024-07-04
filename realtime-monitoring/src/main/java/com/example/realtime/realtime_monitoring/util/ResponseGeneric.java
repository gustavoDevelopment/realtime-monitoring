package com.example.realtime.realtime_monitoring.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseGeneric<R extends Serializable> implements Serializable{

  private String code;
  private String timestamp;
  private String message;

  private R data;

}
