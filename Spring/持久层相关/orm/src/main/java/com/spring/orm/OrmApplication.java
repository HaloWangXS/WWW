package com.spring.orm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.orm.model.Student;
import com.spring.orm.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@SpringBootApplication
@Slf4j
@MapperScan("com.spring.orm.mapper")
public class OrmApplication implements CommandLineRunner {
	@Autowired
	private StudentService studentService;

	public static void main(String[] args) {
		SpringApplication.run(OrmApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PageInfo<Student> students = studentService.listStudentWithPage(4, 5);
		log.info("查询student列表,带分页:{}",students);
	}

	/**
	 * 自动生成xml
	 * @throws Exception
	 */
	private void generateArtifacts() throws Exception {
		List<String> warnings = new ArrayList<>();
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(
				this.getClass().getResourceAsStream("/generatorConfig.xml"));
		DefaultShellCallback callback = new DefaultShellCallback(true);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}

	/**
	 * 配置mybatis的分页插件pageHelper
	 */
	@Bean
	public PageHelper pageHelper(){
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum","true");
		properties.setProperty("rowBoundsWithCount","true");
		properties.setProperty("reasonable","true");
		properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
		pageHelper.setProperties(properties);
		return pageHelper;
	}
}
