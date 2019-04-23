package com.spring.datasource;

import com.spring.datasource.dao.StudentDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class JDBCApplication implements CommandLineRunner {
    @Autowired
    private StudentDao studentDao;

    @Override
    public void run(String... args) throws Exception {
        //studentDao.insertData();
        //studentDao.batchInsertData();
        studentDao.insertData2();
        log.info("新增success");
        studentDao.listData();
    }
}
