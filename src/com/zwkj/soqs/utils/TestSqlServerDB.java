package com.zwkj.soqs.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSqlServerDB {

	public static void main(String[] args) {
		String diverName  = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbUrl = "jdbc:sqlserver://3.242.221.156:1433;DatabaseName=PMMS_SJV";
		String user="PMMSSJV";
		String pwd="PMMSSJV";
		
		try {
			Class.forName(diverName);
			Connection conn = DriverManager.getConnection(dbUrl, user, pwd);
			
			Statement s1 = conn.createStatement();
            ResultSet rs = s1.executeQuery("SELECT TOP 1 * FROM ORDER_INFO");
            String[] result = new String[2];
            if(rs!=null){
                while (rs.next()){
                    System.out.println(rs.getString(1));
                    System.out.println(rs.getString(2));
                }
            }
            
			System.out.println("ddddddddddd");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
