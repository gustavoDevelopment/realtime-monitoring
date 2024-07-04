package com.example.realtime.realtime_monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
public class RealtimeMonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealtimeMonitoringApplication.class, args);
	}

}
