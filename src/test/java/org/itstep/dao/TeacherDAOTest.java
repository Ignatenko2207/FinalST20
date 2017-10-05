package org.itstep.dao;

import static org.junit.Assert.*;

import org.itstep.App;
import org.itstep.dao.pojo.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class TeacherDAOTest {

	@Autowired
	TeacherDAO teacherDAO;

	@Test
	public void testFin() {
		Teacher teacher = new Teacher();
		teacher.setLogin("Zoyas");
		teacher.setPassword("2344576");
		teacher.setFirstName("Zoya");
		teacher.setLastName("Abdin");
		teacher.setSubject("ST20");
		Teacher teacherDB = teacherDAO.saveAndFlush(teacher);
		assertNotNull(teacher);
		Teacher checkedTeacher = teacherDAO.findOne(teacher.getLogin());
		assertNotNull(teacher);
		assertEquals("Abdin", checkedTeacher.getLastName());
		teacherDAO.delete(teacherDB.getLogin());
	}
}