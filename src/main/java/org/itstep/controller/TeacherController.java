package org.itstep.controller;

import java.util.List;

import org.itstep.dao.pojo.Group;
import org.itstep.dao.pojo.Teacher;
import org.itstep.service.GroupService;
import org.itstep.service.TeacherService;
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
@RequestMapping(value = "/teacher")
public class TeacherController {

	@Autowired
	TeacherService teacherService;

	@PostMapping
	public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
		if(teacherService.getTeacher(teacher.getLogin())!=null) {
			Teacher teacherDB = teacherService.createAndUpdateTeacher(teacher);
			if(teacherDB == null) {
				return new ResponseEntity<Teacher>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Teacher>(teacherDB, HttpStatus.CREATED);
		}
		return new ResponseEntity<Teacher>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping
	public ResponseEntity<Teacher> updateTeacher(@RequestBody Teacher teacher) {
		if(teacherService.getTeacher(teacher.getLogin())==null) {
			Teacher teacherDB = teacherService.createAndUpdateTeacher(teacher);
			if(teacherDB == null) {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Teacher>(teacherDB, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	
	
	@GetMapping(value = "/get-teacher")
	public ResponseEntity<Teacher> getOneTeacher(@RequestParam(required = true) String teacherName) {
		Teacher teacherDB = teacherService.getTeacher(teacherName);
		if(teacherDB == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Teacher>(teacherDB, HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity deleteTeacher(@RequestParam(required = true) String teacherName) {
		try {
			teacherService.deleteTeacher(teacherName);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(HttpStatus.OK);
	}
}
