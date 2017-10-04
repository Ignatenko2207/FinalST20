package org.itstep.service;

import static org.junit.Assert.*;

import org.itstep.App;
import org.itstep.dao.pojo.Subject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class SubjectServiceTest {

	@Autowired
	SubjectService subjectService;

	Subject subject;

	@Before
	public void setData() {
		subject = new Subject();
		subject.setSubject("java");
		subjectService.createAndUpdateSubject(subject);
	}

	@After
	public void removeData() {
		subjectService.deleteSubject(subject.getSubject());
	}

	@Test
	public void testGetSubject() {
		Subject checkedSubject = subjectService.getSubject(subject.getSubject());
		assertNotNull(checkedSubject);
	}

}
