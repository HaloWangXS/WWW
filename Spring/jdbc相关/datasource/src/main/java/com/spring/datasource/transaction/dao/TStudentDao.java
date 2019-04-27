package com.spring.datasource.transaction.dao;

import com.spring.datasource.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Slf4j
public class TStudentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void insertStudent(Student stu) {
        String sql = "insert into STUDENT VALUES(:sno,:sname,:ssex,:sbirthday,:sclass)";
        int update = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(stu));
        log.info("namedParameterJdbcTemplate....insert 带事务--> {}",update);
    }

    public int deleteData(String sno) {
        int del = jdbcTemplate.update("delete from STUDENT WHERE SNO = ?", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, sno);
            }
        });
        return del;
    }

    public int updateData(Student stu) {
        String sql = "upadte STUDENT set sname = :sname " +
                                        "and ssex = :ssex " +
                                        "and sbirthday = :sbirthday " +
                                        "and sclass = :sclass " +
                                        "where sno = :sno";
        int update = namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(stu));
        log.info("namedParameterJdbcTemplate....insert 带事务--> {}",update);
        return update;
    }

    public List<Student> listData() {
        List<Student> fooList = jdbcTemplate.query("SELECT * FROM STUDENT", new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Student.builder()
                        .sno(rs.getString(1))
                        .sname(rs.getString(2))
                        .ssex(rs.getString(3))
                        .sbirthday(rs.getDate(4))
                        .sclass(rs.getString(5))
                        .build();
            }
        });
        return fooList;
    }
}
