package org.zerock.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zerock.controller.HomeController;



public class JDBCTests {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		try(Connection con = DriverManager.getConnection(
								// url 주소
								"jdbc:mysql://localhost:3306/ex02?serverTimezone=Asia/Seoul",
								// userName
								"root",
								// password
								"kangta0206##")) {
			logger.info(""+con);
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}

}
