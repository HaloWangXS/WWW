package com.spring.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import javax.sql.DataSource;

@SpringBootApplication
@Slf4j
public class DataSourceApplication implements CommandLineRunner {

	@Autowired
	@Qualifier("firstDataSourceProperties")
	private DataSourceProperties dataSourceProperties;

	@Autowired
	@Qualifier("firstDataSource")
	private DataSource dataSource;

	@Autowired
	@Qualifier("secondDataSourceProperties")
	private DataSourceProperties secondDataSourceProperties;

	@Autowired
	@Qualifier("secondDataSource")
	private DataSource secondDataSource;


	public static void main(String[] args) {
		SpringApplication.run(DataSourceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("red joker dataSource ---> " + dataSource.toString());
		log.info("red joker dataSourceProperties ---> " + dataSourceProperties.getUrl());
		log.info("red joker secondDataSource ---> " + secondDataSource.toString());
		log.info("red joker secondDataSourceProperties ---> " + secondDataSourceProperties.getUrl());
	}
}
