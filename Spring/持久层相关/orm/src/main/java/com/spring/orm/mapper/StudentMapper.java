package com.spring.orm.mapper;

import com.spring.orm.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    int deleteByPrimaryKey(String sno);

    int insert(Student record);

    Student selectByPrimaryKey(String sno);

    List<Student> selectAll();

    int updateByPrimaryKey(Student record);
}