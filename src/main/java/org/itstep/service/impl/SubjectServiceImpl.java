package org.itstep.service.impl;

import org.itstep.dao.SubjectDAO;
import org.itstep.dao.pojo.Subject;
import org.itstep.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	SubjectDAO subjectDAO;

	@Override
	public Subject getSubject(String subject) {
		return subjectDAO.findOne(subject);
	}

	@Override
	public Subject createAndUpdateSubject(Subject subject) {
		return subjectDAO.saveAndFlush(subject);
	}

	@Override
	public void deleteSubject(String subject) {
		subjectDAO.delete(subject);
	}

}
