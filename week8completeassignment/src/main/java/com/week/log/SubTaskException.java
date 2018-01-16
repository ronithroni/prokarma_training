package com.week.log;

import org.apache.log4j.Logger;

public class SubTaskException extends Exception {
	private static final org.apache.log4j.Logger logger = Logger.getLogger(MainWeek.class);	

	public SubTaskException(String s)
	{
		logger.info(s);
	}
}
