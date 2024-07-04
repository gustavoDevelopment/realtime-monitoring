package com.example.realtime.realtime_monitoring.component;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.BlockingQueue;

@Component
public class LogFileReaderService implements Runnable {
  private final BlockingQueue<String> logQueue;
  @Value("${log.file.path}")
  private String logFilePath;

  public LogFileReaderService(BlockingQueue<String> logQueue) {
    this.logQueue = logQueue;
  }

  @PostConstruct
  public void start() {
    Thread thread = new Thread(this);
    thread.start();
  }

  @Override
  public void run() {
    try (RandomAccessFile file = new RandomAccessFile(logFilePath, "r")) {
      Path logPath = Paths.get(logFilePath);
      WatchService watchService = FileSystems.getDefault().newWatchService();
      logPath.getParent().register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

      String line;
      while (true) {
        while ((line = file.readLine()) != null) {
          logQueue.offer(line);
        }

        WatchKey key = watchService.take();
        for (WatchEvent<?> event : key.pollEvents()) {
          if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
            while ((line = file.readLine()) != null) {
              logQueue.offer(line);
            }
          }
        }
        key.reset();
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
