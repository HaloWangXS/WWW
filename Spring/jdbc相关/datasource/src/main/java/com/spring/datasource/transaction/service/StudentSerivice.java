package com.spring.datasource.transaction.service;

import com.spring.datasource.entity.Student;
import com.spring.datasource.transaction.dao.TStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 验证事务的传播特性
 */
@Service
public class StudentSerivice {
    @Autowired
    private TStudentDao tStudentDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void required(Student stu) {
        tStudentDao.insertStudent(stu);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requires_new(Student stu) {
        tStudentDao.insertStudent(stu);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void nested(Student stu) {
        tStudentDao.insertStudent(stu);
    }
    public List<Student> allStudent() {
        return tStudentDao.listData();
    }
}
