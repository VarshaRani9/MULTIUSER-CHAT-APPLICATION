package com.company.chatapp.users.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.company.chatapp.utils.ConfigReader;

import static com.company.chatapp.utils.ConfigReader.getValue;
import static com.company.chatapp.utils.KeyConstants.*;
public interface CommonDAO {
	public static Connection createConnection() throws ClassNotFoundException, SQLException{
//		Connection connection = null;
//		STEP 1 - Loading a Driver
		Class.forName(getValue(DRIVER_NAME));
//		STEP 2 - Create a Connection
		Connection con = DriverManager.getConnection(ConfigReader.getValue(DB_URL),
				getValue(DB_USERID),
				getValue(DB_PWD));
		if(con!=null)System.out.println("Connection created...");
		return con;
	}
}
