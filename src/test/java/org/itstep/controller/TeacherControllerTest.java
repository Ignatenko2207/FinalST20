package org.itstep.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import java.net.URI;
import java.net.URISyntaxException;

import org.itstep.App;
import org.itstep.dao.pojo.Subject;
import org.itstep.dao.pojo.Teacher;
import org.itstep.service.SubjectService;
import org.itstep.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = WebEnvironment.RANDOM_PORT)

public class TeacherControllerTest {

	@MockBean
	TeacherService teacherService;

	@Autowired
	TestRestTemplate testRestTemplate;

	@Test
	public void testGetTeacher() {
		Teacher teacher = new Teacher();
		teacher.setLogin("Ignatenko2207");
		teacher.setPassword("password");
		teacher.setFirstName("Alex");
		teacher.setLastName("Ignatenko");
		teacher.setSubject("Java");

		Mockito.when(teacherService.getTeacher("Ignatenko")).thenReturn(teacher);
		RequestEntity<Teacher> request = null;
		try {
			request = new RequestEntity<Teacher>(teacher, HttpMethod.POST, new URI("/teacher"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		ResponseEntity<Teacher> response = testRestTemplate.exchange(request, Teacher.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		verify(teacherService, times(1)).createAndUpdateTeacher(Mockito.<Teacher>anyString());

	}

	@Test
	public void testCreateAndUpdateTeacher() {

		Teacher teacher = new Teacher();
		teacher.setLogin("Ignatenko2207");
		teacher.setPassword("password");
		teacher.setFirstName("Alex");
		teacher.setLastName("Ignatenko");
		teacher.setSubject("Java");

		Mockito.when(teacherService.isUnique(Mockito.<Teacher>anyString())).thenReturn(false);
		Mockito.when(teacherService.createAndUpdateTeacher(Mockito.<Teacher>anyString())).thenReturn(teacher);

		RequestEntity<Teacher> requestEntity = null;
		try {
			requestEntity = new RequestEntity<Teacher>(teacher, HttpMethod.PUT, new URI("/teacher"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		ResponseEntity<Teacher> responseEntity = testRestTemplate.exchange(requestEntity, Teacher.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Mockito.verify(teacherService, Mockito.times(1)).createAndUpdateTeacher(Mockito.<Teacher>anyString());

	}

	@Test
	public void testDeleteTeacher() {

		Teacher teacher = new Teacher();
		teacher.setLogin("Ignatenko2207");
		teacher.setPassword("password");
		teacher.setFirstName("Alex");
		teacher.setLastName("Ignatenko");
		teacher.setSubject("Java");
		Mockito.doNothing().when(teacherService).deleteTeacher(Mockito.<String>any());
		RequestEntity<Teacher> request = null;
		try {
			request = new RequestEntity<Teacher>(HttpMethod.DELETE,
					new URI("/teacher?teacher=" + teacher.getLogin()));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		ResponseEntity response = testRestTemplate.exchange(request, Teacher.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(teacherService, times(1)).deleteTeacher(Mockito.anyString());

	}

}
