package org.itstep.controller;

import java.util.List;

import org.itstep.dao.pojo.Group;
import org.itstep.dao.pojo.Student;
import org.itstep.service.GroupService;
import org.itstep.service.StudentService;
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
@RequestMapping(value = "/student")

public class StudentController {

	@Autowired
	StudentService studentService;

	@PostMapping
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		if (studentService.getStudent(student.getLogin()) == null) {
			Student studentDB = studentService.createAndUpdateStudent(student);
			if (studentDB == null) {
				return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Student>(studentDB, HttpStatus.CREATED);
		}
		return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
	}

	@PutMapping
	public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
		if (studentService.getStudent(student.getLogin()) != null) {
			Student studentDB = studentService.createAndUpdateStudent(student);
			if (studentDB == null) {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Student>(studentDB, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/get-student")
	public ResponseEntity<Student> getOneStudent(@RequestParam(required = true) String studentLogin) {
		Student studentDB = studentService.getStudent(studentLogin);
		if (studentDB == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Student>(studentDB, HttpStatus.OK);
	}

	@GetMapping(value = "/get-student-list")
	public ResponseEntity<List<Student>> getStudents(@RequestParam(required = true) int course) {
		List<Student> studentList = studentService.findAllStudentsByCourse(course);
		return new ResponseEntity<List<Student>>(studentList, HttpStatus.CREATED);

	}

	@DeleteMapping
	public ResponseEntity deleteStudent(@RequestParam(required = true) String studentName) {
		try {
			studentService.deleteStudent(studentName);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(HttpStatus.OK);
	}
}
