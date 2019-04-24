package com.spring.datasource.transaction.service;

import com.spring.datasource.entity.Teacher;
import com.spring.datasource.transaction.dao.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void required(Teacher tea) {
        teacherDao.insertTeacher(tea);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void requiredException(Teacher tea) {
        teacherDao.insertTeacher(tea);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requires_new(Teacher tea) {
        teacherDao.insertTeacher(tea);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiresNewException(Teacher tea) {
        teacherDao.insertTeacher(tea);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.NESTED)
    public void nested(Teacher tea) {
        teacherDao.insertTeacher(tea);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void nestedException(Teacher tea) {
        teacherDao.insertTeacher(tea);
        throw new RuntimeException();
    }
    public List<Teacher> allStudent() {
        return teacherDao.listTeachers();
    }
}
