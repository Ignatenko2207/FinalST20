package org.itstep.service;

import org.itstep.dao.pojo.Subject;

public interface SubjectService {

	public Subject getSubject(String subject);

	public Subject createAndUpdateSubject(Subject subject);

	public void deleteSubject(String subject);
}
