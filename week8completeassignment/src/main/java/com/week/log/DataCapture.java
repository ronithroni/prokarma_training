package com.week.log;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class DataCapture 
{
	private static String maintitle;
	private static String maindesc;
	private static String mdcname;
	private static int mdcage;
	static List<String> sub_name=new ArrayList<String>();
	static List<Integer> sub_age=new ArrayList<Integer>();
	static List<String> subtasknames=new ArrayList<String>();
	static List<String> subtaskdescriptions=new ArrayList<String>();
	private static int count;
	private static String sub_n;
	private static int assignhours;
	private static int sub_a;
private static int k;
static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
static final String DB_URL = "jdbc:oracle:thin:@172.16.203.151:1521:traindb";
static final String USER = "kronith";
static final String PASS = "prokarma";
static Connection conn=null;
static CallableStatement call=null;
private static int personidage;
private static int m_id;
private static int personidages;
	
private static Connection dbConnection(Logger logger)
{
	try 
		{
    Class.forName("oracle.jdbc.driver.OracleDriver");

		}
catch (final ClassNotFoundException e) 
		{
    logger.error("Driver not found.Please check the driver configuration");
    e.printStackTrace();
 
		}
try
	{
		 conn = DriverManager.getConnection(DB_URL, USER, PASS);
		 return conn;
		 
	}
catch(SQLException se)
{
logger.error(se);
}
return conn;

}
public static int mainnextid(Logger logger)
{
	Statement stmt1=null;
	int mid = 0;
	 try 
        {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt1 = conn.createStatement();
             String sqll = "select maintask_sequence.nextval from dual";
             ResultSet resultSet = stmt1.executeQuery(sqll);
            logger.info("Displaying");
            if(resultSet.next())
            {
            	mid=resultSet.getInt(1);
            }
            resultSet.close();
            stmt1.close();
            //conn.close();
        } 
        catch (final SQLException se) 
        {
            se.printStackTrace();
        }
        catch (final Exception e) 
        {            e.printStackTrace();
        } finally {

            try 
            {
                if (stmt1 != null) 
                {
                    stmt1.close();
                }
            } 
            catch (final SQLException se2) 
            {
            	logger.error("Exception ex"+se2);
            }
        }
	return mid;
 
}
public static int subnextid(Logger logger)
{
	Statement stmt1=null;
	int sid = 0;
	 try 
        {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt1 = conn.createStatement();
             String sqll = "select subtask_sequence.nextval from dual";
             ResultSet resultSet = stmt1.executeQuery(sqll);
            logger.info("Displaying");
            if(resultSet.next())
            {
            	sid=resultSet.getInt(1);
            }
            resultSet.close();
            stmt1.close();
        } 
        catch (final SQLException se) 
        {
            se.printStackTrace();
        }
        catch (final Exception e) 
        {            e.printStackTrace();
        } finally {

            try 
            {
                if (stmt1 != null) 
                {
                    stmt1.close();
                }
            } 
            catch (final SQLException se2) 
            {
            	logger.error("Exception ex"+se2);
            }
            
        }
	return sid;
 
}

	public static void dataCaptureMethod(String str,Logger logger) throws IOException, SubTaskException, MainTaskException, SQLException 
	{
		
		if(str.equalsIgnoreCase("MainTask"))
		{
			Person p= Person.personDetails("Maintask");
			 mdcname=p.getName();
			 mdcage=p.getAge();
			 personidage=Person.getPinsage();
			MainTask mt=new MainTask(mdcage,mdcname);
			 maintitle=mt.title();
			 maindesc=mt.description();
			 String insertmaindetails= "{call insert1_maindetails(?,?,?,?)}";
			 try {
			 conn=dbConnection(logger);
			  m_id=mainnextid(logger);
			 call=conn.prepareCall(insertmaindetails);
				call.setInt(1,m_id);
				call.setInt(2,personidage);
				call.setString(3,maintitle);
				call.setString(4, maindesc);
				call.executeUpdate();
				
			 }
			 catch(SQLException se)
			 {
				 logger.error(se);
			 }
			mt.assignsubs();		
		}
		if(str.equalsIgnoreCase("SubTask")) 
		{
			int ch=0,i=0;
			String insertsubdetails= "{call insert_subdetails(?,?,?,?,?,?)}";
			try
			{
				conn=dbConnection(logger);
				int s_id=subnextid(logger);
				call=conn.prepareCall(insertsubdetails);
				
			}
			catch(SQLException se)
			{
				logger.error(se);
			}
			
			do 
			{
				++count;
				Scanner sc=new Scanner(System.in);
			Person p1=Person.personDetails("SubTask");
					SubTask st=new SubTask(sub_a,sub_n);
					String subt=st.title();
					subtasknames.add(i,subt);
					String subd=st.description();
					subtaskdescriptions.add(i,subd);
					 personidages=Person.getPinsage();
					 logger.info("No.of estimated hours");
					 int eh=sc.nextInt();
					++i;
					int s_id=subnextid(logger);
					call.setInt(1,m_id);
					call.setInt(2, s_id);
					call.setInt(3, personidages);
					call.setString(4, subt);
					call.setString(5, subd);
					call.setInt(6, eh);
					call.executeUpdate();
					
					logger.info("Do you want another subtask  1.yes  2.no");
					ch=sc.nextInt();
					
				 }while(ch!=2);
					}
conn.close();		
	}
		}
