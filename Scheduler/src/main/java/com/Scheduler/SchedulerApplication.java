package com.Scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import service.*;
import model.City;
import java.util.Vector;

@EnableScheduling
@SpringBootApplication
public class SchedulerApplication {

	public static void main(String[] args) {
		Vector<City> cities = AskAndWrite.getCities();
		SpringApplication.run(SchedulerApplication.class);
	}

}
