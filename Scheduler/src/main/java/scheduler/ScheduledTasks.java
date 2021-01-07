package scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import service.AskAndWrite;

@Component
public class ScheduledTasks {
	
	@Scheduled (fixedRate=5000) //ogni 15 secondi
	public void ReportData() {
		System.out.println("Bella");
		//AskAndWrite.askAndWrite();
	}
}
