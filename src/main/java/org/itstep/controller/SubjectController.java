package org.itstep.controller;

import org.itstep.dao.pojo.Subject;
import org.itstep.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/subject")
public class SubjectController {

	@Autowired
	SubjectService subjectService;

	@PostMapping
	public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
		if (subjectService.getSubject(subject.getSubject()) == null) {
			Subject subjectDB = subjectService.createAndUpdateSubject(subject);
			if (subjectDB == null) {
				return new ResponseEntity<Subject>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Subject>(subjectDB, HttpStatus.CREATED);
		}
		return new ResponseEntity<Subject>(HttpStatus.NOT_FOUND);
	}

	@PutMapping
	public ResponseEntity<Subject> updateSubject(@RequestBody Subject subject) {
		if (subjectService.getSubject(subject.getSubject()) != null) {
			Subject subjectDB = subjectService.createAndUpdateSubject(subject);
			if (subjectDB == null) {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Subject>(subjectDB, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/get-subject")
	public ResponseEntity<Subject> getOneSubject(@RequestParam(required = true) String subject) {
		Subject subjectDB = subjectService.getSubject(subject);
		if (subjectDB == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Subject>(subjectDB, HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity deleteSubject(@RequestParam(required = true) String subject) {
		try {
			subjectService.deleteSubject(subject);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(HttpStatus.OK);
	}
}
