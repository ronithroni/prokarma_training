package com.week.log;

import org.apache.log4j.Logger;

public class MainTaskException extends Exception {
	private static final org.apache.log4j.Logger logger = Logger.getLogger(MainWeek.class);	

public MainTaskException(String s)
{
	logger.info(s);
}
}
