package com.week.log;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

public class MainWeek
{
	private static final org.apache.log4j.Logger logger = Logger.getLogger(MainWeek.class);	

public static void main(String[] args) throws IOException, SubTaskException, MainTaskException, SQLException, ClassNotFoundException 
{
	Scanner sc=null;
	try {
	 sc=new Scanner(System.in);
	int option=0;
	do {
		logger.info("MENU\n1.Add a MainTask\n2.Print existing tasks\n3.SingleThreadTasks\n4.MultipleThreads\n5.Update Main Task\n6.Exit");
		logger.info("Enter your choice");
		option=sc.nextInt();
		switch(option)
		{
		case 1:
			DataCapture.dataCaptureMethod("MainTask",logger);
			break;
		case 2:
			FullDetails.fullDetailsPrint(logger);
			FullSub.printsubtask(logger);
			break;
		case 3:
			long starttime1=System.currentTimeMillis();
			Thread t=new Thread(new GenerateMultipleTasks());
			t.start();
			logger.info("Tasks are generated-Single Threaded");
			long endtime1=System.currentTimeMillis();
			logger.info("Total Time"+(starttime1-endtime1));
			break;
		case 4:
			long starttime=System.currentTimeMillis();
			ExecutorService executor=Executors.newFixedThreadPool(5);
			for(int i=0;i<5;i++)
			{
				Runnable mt=new GenerateTasks();
				executor.execute(mt);
			}
			executor.shutdown();
			logger.info("Tasks are generated-Multi_Threaded");
			long endtime=System.currentTimeMillis();
			logger.info("TotalTime"+(starttime-endtime));
			break;
		case 5:
			UpdateMainTask.update();
			break;
		case 6:
			System.exit(0);
		}
		}while(option!=6);
	}
	catch(Exception e)
	{
		logger.error(e);
	}
	finally {
		sc.close();
	}
}
}

