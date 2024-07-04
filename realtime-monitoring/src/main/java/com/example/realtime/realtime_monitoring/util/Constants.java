package com.example.realtime.realtime_monitoring.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;

@UtilityClass
public class Constants {
  public static final SimpleDateFormat rspFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

  public static final ObjectMapper mapper = new ObjectMapper();

  private static final Gson gson = new Gson();
}
