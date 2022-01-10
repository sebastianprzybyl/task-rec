package com.test.task;


import com.test.task.event.json.EventLogJsonStreaming;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;

@EnableAsync
@SpringBootApplication
public class TaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}

	@Autowired
	EventLogJsonStreaming eventLogJsonStreaming;

	@PostConstruct
	public void startProcessing() {
		eventLogJsonStreaming.readAndProcessFile();
	}

}
