package com.week.log;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class MainTask extends Abstract_Task {
	Scanner scan = new Scanner(System.in);
	private String maintasktitle;
	private String maintaskdescription;
	private Person p;
	private SubTask sb;
	private static final org.apache.log4j.Logger logger = Logger.getLogger(MainWeek.class);	

	public MainTask(int age, String name) {
		super(age, name);
		this.age = age;
		this.name = name;
	}

	public MainTask(String maintasktitle, String maintaskdescription, SubTask sb) {
		super(age, name);
		this.maintasktitle = maintasktitle;
		this.maintaskdescription = maintaskdescription;
		this.sb = sb;
	}

	public String toString() {
		return "" + maintasktitle + "," + maintaskdescription + "," + sb.getSubtasktitle() + ","
				+ sb.getSubtaskdescription() + "," + sb.estimatehours
+ sb.p1.getName() + "," + sb.p1.getAge() + "," + "\n";
	}

	public String getMaintasktitle() {
		return maintasktitle;
	}

	public String getMaintaskdescription() {
		return maintaskdescription;
	}

	public String title() {
logger.info("Enter the main task's title");
		maintasktitle = scan.next();
		return maintasktitle;
	}

	public String description() {
		logger.info("Enter the main task's description");
		maintaskdescription = scan.next();
		return maintaskdescription;
	}

	public void assignsubs() throws IOException, SubTaskException, MainTaskException, SQLException {
		SubTask.subTaskAssignment();
	}

}
