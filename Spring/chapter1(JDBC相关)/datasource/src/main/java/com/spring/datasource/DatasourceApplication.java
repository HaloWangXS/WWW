package com.spring.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.Connection;

@SpringBootApplication
@Slf4j
@EnableAutoConfiguration
public class DatasourceApplication implements CommandLineRunner {

	@Autowired
	private DruidDataSource druidDataSource;

	public static void main(String[] args) {
		SpringApplication.run(DatasourceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info(druidDataSource.toString());
		Connection conn = druidDataSource.getConnection();
		log.info(conn.toString());
		conn.close();
	}
}
