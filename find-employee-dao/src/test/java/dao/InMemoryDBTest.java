package dao;

import java.math.BigInteger;
import java.time.Instant;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import model.Employee;
import model.conf.BeanConfig;
import model.dao.service.EmployeeRepositoryService;

@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = { BeanConfig.class }, loader = AnnotationConfigContextLoader.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@TestPropertySource("application-test.properties")
public class InMemoryDBTest {
     
    @Autowired
    private EmployeeRepositoryService employeeRepositoryServiceImpl;
     
    @Test
    public void givenStudent_whenSave_thenGetOk() {
        Employee student = new Employee();
        student.setDepartment("Department");
        student.setFirstName("firstName");
        student.setJoinDate(new Date( Instant.now().getEpochSecond()));
        student.setLastName("lastName");
        student.setSalary(new BigInteger("100"));
        Employee e = employeeRepositoryServiceImpl.create(student);
        Assert.assertTrue("firstName".equals( e.getFirstName()));
    }
    
    @Test
    public void test_FindById() {
        Employee e = employeeRepositoryServiceImpl.findById(1L);
        Assert.assertTrue("Lname1".equals( e.getLastName()));
    }
}