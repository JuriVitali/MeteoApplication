package com.Scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import service.AskAndWrite;

@Component
public class ScheduledTasks {
	
	//private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	
	@Scheduled (fixedRate = 5000) //ogni 5 secondi
	public void ReportData() {
		
	    AskAndWrite.parse();
	    System.out.println();
	    AskAndWrite.WriteJSONExample();
	}    
	/*@Scheduled (fixedRate = 5000) //ogni 5 secondi
    public void ReportData1() {
	    
	}*/
}
