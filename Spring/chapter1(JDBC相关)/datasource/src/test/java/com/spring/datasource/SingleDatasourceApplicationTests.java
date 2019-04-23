package com.spring.datasource;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SingleDatasourceApplicationTests {
	@Autowired
	ApplicationContext applicationContext;

	@Test
	public void contextLoads() throws Exception{
		DataSource source = applicationContext.getBean(DataSource.class);
		log.info("red joker ---> " + source.toString());
		log.info("red joker2 ---> " + source.getClass().getName());
		Connection conn = source.getConnection();
		log.info("red joker ---> " + conn.toString());
		conn.close();
	}
}
