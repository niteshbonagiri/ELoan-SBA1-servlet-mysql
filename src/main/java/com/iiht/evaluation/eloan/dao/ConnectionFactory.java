package com.iiht.evaluation.eloan.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory {

	private static Connection jdbcConnection;
	static Context ctx=null;
	private static DataSource ds;
	
	public static Connection getConnection() {
	try {
		ctx=new InitialContext();
		ds=(DataSource) ctx.lookup("java:/comp/env/jdbc/eloan");
		if (jdbcConnection == null || jdbcConnection.isClosed()) {	
			jdbcConnection = ds.getConnection();
		}
	} catch (NamingException | SQLException e) {
		e.printStackTrace();
		}
	return jdbcConnection;
    }


	public static void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
}
