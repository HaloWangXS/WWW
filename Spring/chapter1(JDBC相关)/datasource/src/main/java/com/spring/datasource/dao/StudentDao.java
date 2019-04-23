package com.spring.datasource.dao;

import com.spring.datasource.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
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
public class StudentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void insertData() {
        int update = jdbcTemplate.update("INSERT INTO STUDENT VALUES (?,?,?,?,?)", "111", "金樱花2", "女", null, "95091");
        log.info("jdbcTemplate....insert --> {}",update);
    }

    public void insertData2() {
        //使用 NamedParameterJdbcTemplate
        String sql = "insert into STUDENT VALUES(:sno,:sname,:ssex,:sbirthday,:sclass)";
        Student stu = new Student();
        stu.setSno("141");
        stu.setSname("mac");
        stu.setSsex("男");
        stu.setSbirthday(null);
        stu.setSclass("1923123123");
        namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(stu));
    }

    public void batchInsertData() {
        jdbcTemplate.batchUpdate("insert into STUDENT values (?,?,?,?,?)", new BatchPreparedStatementSetter() {
            // i 从0开始
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,"13" + i);
                ps.setString(2,"iphonex" + i);
                ps.setString(3,"男");
                ps.setString(4,null);
                ps.setString(5,"9509" + i);
            }
            // 批处理 执行几次
            @Override
            public int getBatchSize() {
                return 5;
            }
        });
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

    public int updateData(Student student) {
        int update = jdbcTemplate.update("upadte STUDENT set sname = ? and sclass = ? where sno = ?", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, student.getSname());
                ps.setString(2, student.getSclass());
                ps.setString(3, student.getSno());
            }
        });
        return update;
    }

    public void listData() {
        log.info("Count: {}",
                jdbcTemplate.queryForObject("SELECT COUNT(*) FROM STUDENT", Long.class));

        List<String> list = jdbcTemplate.queryForList("SELECT sname FROM STUDENT", String.class);
        list.forEach(s -> log.info("Student,name: {}", s));

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
        fooList.forEach(f -> log.info("Student: {}", f));
    }
}
