package scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class Scheduler {

		@Scheduled (fixedRate=15000) //ogni 15 secondi
		public void ReportData() {
			
		}
}
