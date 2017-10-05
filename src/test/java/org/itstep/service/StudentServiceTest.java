package org.itstep.service;

import static org.junit.Assert.*;
import java.util.List;
import org.itstep.App;
import org.itstep.dao.pojo.Group;
import org.itstep.dao.pojo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class StudentServiceTest {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	GroupService groupService;


	@Test
	public void testCreateAndUpdateStudent() {
		Student student = new Student();
		student.setLogin("Vasiliy17");
		student.setPassword("123456");
		student.setFirstName("Vasiliy");
		student.setLastName("Pupkin");
		student.setGroupName("ST-20");
		Student studentFromDB = studentService.createAndUpdateStudent(student);
		assertNotNull(studentFromDB);
		Student checkedStudent = studentService.getStudent(studentFromDB.getLogin());
		assertEquals("Vasiliy17", checkedStudent.getLogin());
	}

	@Test
	public void testDeleteStudent() {
		Student student = new Student();
		student.setLogin("Vasiliy17");
		student.setPassword("123456");
		student.setFirstName("Vasiliy");
		student.setLastName("Pupkin");
		student.setGroupName("ST-20");
		Student studentFromDB = studentService.createAndUpdateStudent(student);
		studentService.deleteStudent(studentFromDB.getLogin());
		Student checkedStudent = studentService.getStudent(studentFromDB.getLogin());
	    assertNull (checkedStudent);
	}

	@Test
	public void testFindStudentsByGroup() {
		Student student = new Student();
		student.setLogin("Vasiliy17");
		student.setPassword("123456");
		student.setFirstName("Vasiliy");
		student.setLastName("Pupkin");
		student.setGroupName("ST-20");
		List<Student> studentList = studentService.findStudentsByGroup("ST-20");
		assertTrue (!studentList.isEmpty());
	}

	@Test
	public void testFindAllStudentsByCourse() {
		Student student = new Student();
		student.setLogin("Vasiliy17");
		student.setPassword("123456");
		student.setFirstName("Vasiliy");
		student.setLastName("Pupkin");
		student.setGroupName("ST-20");
		Student studentFromDB = studentService.createAndUpdateStudent(student);
		Group group = new Group();
		group.setGroupName("ST-20");
		group.setCourse(1);
		Group groupDB = groupService.createAndUpdateGroup(group);
		List<Student> studentList = studentService.findAllStudentsByCourse(1) ;
		assertTrue(!studentList.isEmpty());
		studentService.deleteStudent(studentFromDB.getLogin());
		groupService.deleteGroup(groupDB.getGroupName());
	}

}
