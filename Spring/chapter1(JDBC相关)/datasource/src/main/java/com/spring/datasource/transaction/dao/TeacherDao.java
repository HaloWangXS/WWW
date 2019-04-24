package com.spring.datasource.transaction.dao;

import com.spring.datasource.entity.Teacher;
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
public class TeacherDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void insertTeacher(Teacher tea) {
        String sql = "insert into TEACHER VALUES(:tno,:tname,:tsex,:tbirthday,:prof,:depart)";
        int update = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(tea));
        log.info("namedParameterJdbcTemplate....insert 带事务--> {}",update);
    }

    public int deleteTeacher(String tno) {
        int del = jdbcTemplate.update("delete from TEACHER WHERE TNO = ?", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, tno);
            }
        });
        return del;
    }

    public int updateTeacher(Teacher tea) {
        String sql = "upadte TEACHER set tname = :tname " +
                                        "and tsex = :tsex " +
                                        "and tbirthday = :tbirthday " +
                                        "and prof = :prof " +
                                        "and depart = :depart" +
                                        "where tno = :tno";
        int update = namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(tea));
        log.info("namedParameterJdbcTemplate....insert 带事务--> {}",update);
        return update;
    }

    public List<Teacher> listTeachers() {
        List<Teacher> fooList = jdbcTemplate.query("SELECT * FROM TEACHER", new RowMapper<Teacher>() {
            @Override
            public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Teacher.builder()
                        .tno(rs.getString(1))
                        .tname(rs.getString(2))
                        .tsex(rs.getString(3))
                        .tbirthday(rs.getDate(4))
                        .prof(rs.getString(5))
                        .depart(rs.getString(6))
                        .build();
            }
        });
        return fooList;
    }
}
