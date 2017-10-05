package org.itstep.dao;

import static org.junit.Assert.*;

import org.itstep.App;
import org.itstep.dao.pojo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class StudentDAOTest {

	@Autowired
	StudentDAO studentDAO;
	
	@Test
	public void createAndGetAndDelete() {
		Student student = new Student();
		student.setLogin("Ignatenko2207");
		student.setPassword("123456");
		student.setFirstName("Alex");
		student.setLastName("Ignatenko");
		student.setGroupName("ST20");
		Student studentFromDB = studentDAO.save(student);
		assertNotNull(studentFromDB);
		Student checkedStudent = studentDAO.findOne(studentFromDB.getLogin());
		assertNotNull(checkedStudent);
		assertEquals("Ignatenko", studentFromDB.getLastName());
		studentDAO.delete(studentFromDB.getLogin());
	}

}
