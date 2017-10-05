package org.itstep.service;

import org.itstep.dao.pojo.Teacher;

public interface TeacherService {

	public Teacher getTeacher(String login);
	
	public Teacher createAndUpdateTeacher(String teacher);
	
	public void deleteTeacher(String login);

	public Object isUnique(String anyString);
}
