package com.week.log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class UpdateMainTask 
{
	private static final org.apache.log4j.Logger logger = Logger.getLogger(MainWeek.class);	

	static Connection conn = null;
	static Scanner sc=null;
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@172.16.203.151:1521:traindb";
    static final String USER = "kronith";
    static final String PASS = "prokarma";
    public static void update() throws SQLException 
    {
        PreparedStatement pstmt = null;
        String sold="";
        String descnew="";
         sc=new Scanner(System.in);
        try
        {
        	logger.info("Enter the existing maintask name which needs to be replaced");
        	sold=sc.nextLine();
        	logger.info("Enter the new maintask title");
        	String snew=sc.next();
            logger.info("Enter the main task details");
            descnew=sc.next();
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String query = "update maintaskdetails set mname = ? , mdesc=? where mname = ?";
            pstmt=conn.prepareStatement(query);
            pstmt.setString(1,snew);
            pstmt.setString(2,descnew);
            pstmt.setString(3,sold);//existiung
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch(SQLException se)
        {
        	logger.error("Error message"+se);
        }
    }
}