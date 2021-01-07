package com.Scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import service.AskAndWrite;


@SpringBootApplication
@EnableScheduling
public class SchedulerApplication {

	public static void main(String[] args) {
		AskAndWrite.askAndWrite();
		//SpringApplication.run(SchedulerApplication.class);
	}

}
