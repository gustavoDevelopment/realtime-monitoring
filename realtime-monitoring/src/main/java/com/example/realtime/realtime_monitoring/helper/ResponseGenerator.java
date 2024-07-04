package com.example.realtime.realtime_monitoring.helper;

import com.example.realtime.realtime_monitoring.util.ConstantCode;
import com.example.realtime.realtime_monitoring.util.ConstantMessage;
import com.example.realtime.realtime_monitoring.util.Constants;
import com.example.realtime.realtime_monitoring.util.ResponseGeneric;
import lombok.experimental.UtilityClass;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@UtilityClass
public class ResponseGenerator {
  public   <R extends Serializable> ResponseGeneric<Serializable> doOnGenerateResponse(String code, String message) {
    try {
      return ResponseGeneric.<Serializable>builder().code(code).message(message).timestamp(Constants.rspFormat.format(new Date())).build();
    } catch (Exception ex) {
      return ResponseGeneric.<Serializable>builder().code(ConstantCode.CRITICAL_ERROR).message(ConstantMessage.CRITICAL_ERROR).timestamp(Constants.rspFormat.format(new Date())).build();
    }
  }

  public   <R extends Serializable> ResponseGeneric<Serializable> doOnGenerateResponse(String code, String message, List<R> data) {
    try {
      return ResponseGeneric.<Serializable>builder().code(code).message(message).timestamp(Constants.rspFormat.format(new Date())).data((Serializable) data).build();
    } catch (Exception ex) {
      return ResponseGeneric.<Serializable>builder().code(ConstantCode.CRITICAL_ERROR).message(ConstantMessage.CRITICAL_ERROR).timestamp(Constants.rspFormat.format(new Date())).build();
    }
  }
  public   <R extends Serializable> ResponseGeneric<Serializable> doOnGenerateResponse(String code, String message, R data) {
    try {
      return ResponseGeneric.<Serializable>builder().code(code).message(message).timestamp(Constants.rspFormat.format(new Date())).data(data).build();
    } catch (Exception ex) {
      return ResponseGeneric.<Serializable>builder().code(ConstantCode.CRITICAL_ERROR).message(ConstantMessage.CRITICAL_ERROR).timestamp(Constants.rspFormat.format(new Date())).build();
    }
  }

}
