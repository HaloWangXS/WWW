package com.spring.datasource;

import com.spring.datasource.entity.Student;
import com.spring.datasource.entity.Teacher;
import com.spring.datasource.transaction.service.StudentSerivice;
import com.spring.datasource.transaction.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@Slf4j
public class TransactionApplication implements CommandLineRunner {
    @Autowired
    private StudentSerivice studentSerivice;

    @Autowired
    private TeacherService teacherService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void run(String... args) throws Exception {
        testNestedWithTransWithException2();
    }

    /**
     * 验证REQUIRED级别 外围方法没有使用事务
     * 结果: 两者都插入成功
     * 原因: 外围方法的事务未开启, 内层方法使用独立的事务,外围方法不影响内层方法的事务。
     */

    public void testRequired() {
        Student stu = new Student();
        stu.setSno("201");
        stu.setSname("事务测试");
        stu.setSsex("男");
        stu.setSclass("2019");
        studentSerivice.required(stu);
        Teacher tea = new Teacher();
        tea.setTno("901");
        tea.setTname("蔡徐坤");
        tea.setTsex("男");
        teacherService.required(tea);
        throw new RuntimeException();
    }

    /**
     * 验证REQUIRED级别 外围方法没有使用事务
     * 结果: stu插入成功, tea回滚
     */
    public void testRequiredWithException() {
        Student stu = new Student();
        stu.setSno("202");
        stu.setSname("事务测试2");
        stu.setSsex("男");
        stu.setSclass("2019");
        studentSerivice.required(stu);
        Teacher tea = new Teacher();
        tea.setTno("902");
        tea.setTname("蔡徐坤2");
        tea.setTsex("男");
        teacherService.requiredException(tea);
    }

    /**
     * 验证REQUIRED级别 外围方法使用事务
     * 结果: 全部回滚
     * 原因: 外围方法有事务, 对于内层方法来说,事务级别是REQUIRED  如果当前存在事务(外围事务) 使用该事务
     *      所以 外围方法和内围方法是同一个事务, 外围事务异常, 所以全部回滚。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void testRequiredWithTrans() {
        Student stu = new Student();
        stu.setSno("203");
        stu.setSname("事务测试3");
        stu.setSsex("男");
        stu.setSclass("2019");
        studentSerivice.required(stu);
        Teacher tea = new Teacher();
        tea.setTno("903");
        tea.setTname("蔡徐坤3");
        tea.setTsex("男");
        teacherService.required(tea);
        throw new RuntimeException();
    }

    /**
     * 验证REQUIRED级别 外围方法使用事务
     * 结果: 全部回滚
     * 原因: 外围方法有事务, 对于内层方法来说,事务级别是REQUIRED  如果当前存在事务(外围事务) 使用该事务
     *      所以 外围方法和内围方法是同一个事务, 内部方法异常, 所以全部回滚。
     * 补充情况: 即使内部方法异常在外部方法中被try...catch掉   也会回滚
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void testRequiredWithTransWithException() {
        Student stu = new Student();
        stu.setSno("204");
        stu.setSname("事务测试4");
        stu.setSsex("男");
        stu.setSclass("2019");
        studentSerivice.required(stu);
        Teacher tea = new Teacher();
        tea.setTno("904");
        tea.setTname("蔡徐坤4");
        tea.setTsex("男");
        teacherService.requiredException(tea);
    }

    /**
     * 验证REQUIRES_NEW级别 外围方法使用事务
     * 结果: 全部提交
     * 原因: 外围方法有事务, 对于内层方法来说,事务级别是REQUIRES_NEW 无论外围方法有没有事务, 内层事务都会新建一个事务
     *      所以 外围方法和内围方法不是同一个事务, 外围方法异常, 不影响内层方法, 内层方法执行成功。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void testRequiresNewWithTrans() {
        Student stu = new Student();
        stu.setSno("205");
        stu.setSname("事务测试5");
        stu.setSsex("男");
        stu.setSclass("2019");
        studentSerivice.requires_new(stu);
        Teacher tea = new Teacher();
        tea.setTno("905");
        tea.setTname("蔡徐坤5");
        tea.setTsex("男");
        teacherService.requires_new(tea);
        throw new RuntimeException();
    }

    /**
     * 验证REQUIRES_NEW级别 外围方法使用事务
     * 结果: stu成功, tea失败
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void testRequiresNewWithTransWithException() {
        Student stu = new Student();
        stu.setSno("207");
        stu.setSname("事务测试7");
        stu.setSsex("男");
        stu.setSclass("2019");
        studentSerivice.requires_new(stu);
        Teacher tea = new Teacher();
        tea.setTno("907");
        tea.setTname("蔡徐坤7");
        tea.setTsex("男");
        teacherService.requiresNewException(tea);
    }

    /**
     * 验证Nested级别 外围方法使用事务
     * 结果: 全部回滚
     * 原因: 内部事务为外围事务的子事务，外围方法回滚，内部方法也要回滚。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void testNestedWithTrans() {
        Student stu = new Student();
        stu.setSno("208");
        stu.setSname("事务测试8");
        stu.setSsex("男");
        stu.setSclass("2019");
        studentSerivice.nested(stu);
        Teacher tea = new Teacher();
        tea.setTno("908");
        tea.setTname("蔡徐坤8");
        tea.setTsex("男");
        teacherService.nested(tea);
        throw new RuntimeException();
    }

    /**
     * 验证Nested级别 外围方法使用事务
     * 结果: 全部回滚
     * 原因: 内部事务为外围事务的子事务，内部方法抛出异常回滚，且外围方法感知异常致使整体事务回滚。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void testNestedWithTransWithException() {
        Student stu = new Student();
        stu.setSno("209");
        stu.setSname("事务测试9");
        stu.setSsex("男");
        stu.setSclass("2019");
        studentSerivice.nested(stu);
        Teacher tea = new Teacher();
        tea.setTno("909");
        tea.setTname("蔡徐坤9");
        tea.setTsex("男");
        teacherService.nestedException(tea);
    }

    /**
     * 验证Nested级别 外围方法使用事务
     * 结果: 全部回滚
     * 原因: 内部事务为外围事务的子事务，内部方法抛出异常回滚，插入“事务测试9”内部方法抛出异常，可以单独对子事务回滚。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void testNestedWithTransWithException2() {
        Student stu = new Student();
        stu.setSno("209");
        stu.setSname("事务测试9");
        stu.setSsex("男");
        stu.setSclass("2019");
        studentSerivice.nested(stu);
        Teacher tea = new Teacher();
        tea.setTno("909");
        tea.setTname("蔡徐坤9");
        tea.setTsex("男");
        try{
            teacherService.nestedException(tea);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
