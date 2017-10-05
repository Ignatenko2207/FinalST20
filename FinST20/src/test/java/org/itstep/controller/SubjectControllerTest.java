package org.itstep.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import java.net.URI;
import java.net.URISyntaxException;

import org.itstep.App;
import org.itstep.dao.pojo.Subject;
import org.itstep.service.SubjectService;
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
public class SubjectControllerTest {

	@MockBean
	SubjectService subjectService;
	
	@Autowired
	TestRestTemplate testRestTemplate;
	
	@Test
	public void testCreateSubject() {
		Subject subject = new Subject();
		subject.setSubject("javaEE");
		
		Mockito.when(subjectService.getSubject("javaEE")).thenReturn(null);
		Mockito.when(subjectService.createAndUpdateSubject(Mockito.<Subject>any())).thenReturn(subject);
		RequestEntity<Subject> request = null;
		try {
			request = new RequestEntity<Subject>(subject, HttpMethod.POST, new URI("/subject"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		ResponseEntity<Subject> response = testRestTemplate.exchange(request, Subject.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		verify(subjectService, times(1)).createAndUpdateSubject(Mockito.<Subject>any());
	}

	@Test
	public void testUpdateSubject() {
	}

	@Test
	public void testGetOneSubject() {
	}

	@Test
	public void testDeleteSubject() {
		
		Subject subject = new Subject();
		subject.setSubject("javaEE");
		
		Mockito.doNothing().when(subjectService).deleteSubject(Mockito.<String>any());
		
		RequestEntity<Subject> request = null;
		try {
			request = new RequestEntity<Subject>(subject, HttpMethod.DELETE, new URI("/subject?subject=" + subject.getSubject()));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		ResponseEntity<Subject> response = testRestTemplate.exchange(request, Subject.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(subjectService, times(1)).deleteSubject(Mockito.anyString());
	
		}

}
