package com.week.log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class FullSub {
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@172.16.203.151:1521:traindb";
    static final String USER = "kronith";
    static final String PASS = "prokarma";

	public static void printsubtask(Logger logger) throws SQLException, ClassNotFoundException
	{
		Connection conn = null;
        Statement stmt1 = null;
     try {  

         Class.forName(JDBC_DRIVER);
         logger.info("Connecting to database...");
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
		 stmt1 = conn.createStatement();
         String sqlsub = "select *from subtaskdetails";
         ResultSet resultSetsub = stmt1.executeQuery(sqlsub);
         System.out.println("Displaying");
          while (resultSetsub.next()) 
         {
             int mid = resultSetsub.getInt("mid");
              int sid = resultSetsub.getInt("sid");
              int pid=resultSetsub.getInt("pid");
              String sname = resultSetsub.getString("sname");
              String sdesc = resultSetsub.getString("sdesc");
              int ehours=resultSetsub.getInt("estimatehours");
             logger.info("------------------------------------------: ");
             logger.info("MID: " + mid);
             logger.info("PID: "+pid);
             logger.info("SID: "+sid);
             logger.info("Subtask title:" + sname);
             logger.info("Subtask Description: " + sdesc);
             logger.info("Estimate hours: "+ehours);
             logger.info("------------------------------------------: ");
         }
          
         resultSetsub.close();
	}
     catch(SQLException s)
     {
    	 logger.error(s);
     }
         finally {
        	 try
     		{
     			if(stmt1!=null) {
     				stmt1.close();
     			}
     		}
     		catch (final SQLException se) {
                 se.printStackTrace();
             }
         }
	}
}
