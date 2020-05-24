package modele;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class CronJob implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.print("Job en cours d'execution");
		//JSONParsing.updateDatabaseWithURL();
	}

}
