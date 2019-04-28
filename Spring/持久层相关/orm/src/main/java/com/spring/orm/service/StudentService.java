package com.spring.orm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.orm.mapper.StudentMapper;
import com.spring.orm.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;
    public PageInfo<Student> listStudentWithPage(int current,int pageSize) {
        PageHelper.startPage(current,pageSize);
        List<Student> students = studentMapper.selectAll();
        PageInfo<Student> result = new PageInfo<>(students);
        return result;
    }
}
