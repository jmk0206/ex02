package org.zerock.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.controller.HomeController;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")

public class DataSourceTests {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private DataSource dataSource;
	@Test
	public void testConnection() {
		try(Connection con = dataSource.getConnection()) {
			logger.info(""+con);
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}

}