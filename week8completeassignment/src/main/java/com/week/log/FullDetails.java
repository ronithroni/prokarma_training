package com.week.log;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
public class FullDetails {
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@172.16.203.151:1521:traindb";
    static final String USER = "kronith";
    static final String PASS = "prokarma";

	public static void fullDetailsPrint(Logger logger) throws IOException
	{
		Connection conn = null;
        Statement stmt = null;
       
        Statement stmt2=null;
        try 
        {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
             String sqlmain = "select *from maintaskdetails";
             ResultSet resultSet = stmt.executeQuery(sqlmain);
             while (resultSet.next()) 
            {
                int mid = resultSet.getInt("mid");
                 int pid = resultSet.getInt("pid");
                 String mname = resultSet.getString("mname");
                 String mdesc = resultSet.getString("mdesc");
                logger.info("------------------------------------------: ");
                logger.info("MID: " + mid);
                logger.info("PID: "+pid);
                logger.info("Maintask title:" + mname);
                logger.info("Maintask Description" + mdesc);
                logger.info("------------------------------------------: ");
            }
             
            resultSet.close();
           
           
            stmt2 = conn.createStatement();
            String sqlper = "select *from persondetails";
            ResultSet resultSetper = stmt.executeQuery(sqlper);
            System.out.println("Displaying");
             while (resultSetper.next()) 
            {
                 int pid=resultSetper.getInt("pid");
                 String pname = resultSetper.getString("pname");
                 int page = resultSetper.getInt("page");
                logger.info("------------------------------------------: ");
                logger.info("PID: "+pid);
                logger.info("Person Name:" + pname);
                logger.info("Person's age " + page);
                
                logger.info("------------------------------------------: ");
            }
             
            resultSetper.close();

             } 
        catch (final SQLException se) 
        {
            se.printStackTrace();
        }
        catch (final Exception e) 
        {            e.printStackTrace();
        } 
        finally {
        		try
        		{
        			if(stmt!=null) {
        				stmt.close();
        			}
        		}
        		catch (final SQLException se) {
                    se.printStackTrace();
                }
            try {
                if (conn != null) {
                    conn.close();
                }
            } 
            catch (final SQLException se) {
                se.printStackTrace();
            }
        }
        logger.info("Goodbye!");

	}
}

