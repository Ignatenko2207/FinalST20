package org.itstep.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.itstep.App;
import org.itstep.dao.pojo.Group;
import org.itstep.dao.pojo.Lesson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class LessonDAOTest {
	@Autowired
	LessonDAO lessonDAO;
	@Autowired
	GroupDAO groupDAO;

	@Test
	public void createAndGetAndDelete() {
		Lesson lesson = new Lesson();
		lesson.setLessonStart((long) (9));
		lesson.setLength((long) (1000 * 60 * 45));
		lesson.setTeacher("Ivanov");
		lesson.setGroup("ST-20");
		lesson.setRoom("2");
		lesson.setSubject("Math");
		Lesson lessonFromDB = lessonDAO.saveAndFlush(lesson);
		assertNotNull(lessonFromDB);
		Lesson checkedLesson = lessonDAO.findOne(lessonFromDB.getLessonId());
		assertNotNull(checkedLesson);
		assertEquals("Math", lessonFromDB.getSubject());
		lessonDAO.delete(lessonFromDB.getLessonId());

	}

	@Test
	public void testGetLessonsForCourseForPeriod() {
		Lesson lesson = new Lesson();
		lesson.setLessonStart(1L);
		lesson.setLength(1L);
		lesson.setRoom("2");
		lesson.setSubject("Math");
		lesson.setTeacher("Ivanov");
		lesson.setGroup("ST20");
		Lesson lessonFromDB = lessonDAO.saveAndFlush(lesson);
		Group group = new Group();
		group.setGroupName("ST20");
		group.setCourse(1);
		lesson.setGroup(group.getGroupName());
		Group groupFromDB = groupDAO.saveAndFlush(group);
		List<Lesson> lessonsFromDB = lessonDAO.getLessonsForCourseForPeriod(groupFromDB.getCourse(), 0L, 3L);
		assertNotNull(lessonsFromDB);
		// assertEquals(lessonFromDB.getLessonId(), lessonsFromDB.get(0).getLessonId());
		groupDAO.delete(groupFromDB.getGroupName());
		lessonDAO.delete(lessonFromDB.getLessonId());
	}

	@Test
	public void testGetLessonsForGroupForPeriod() {
		Lesson lesson = new Lesson();
		lesson.setLessonStart(1L);
		lesson.setLength(1L);
		lesson.setRoom("2");
		lesson.setSubject("Math");
		lesson.setTeacher("Ivanov");
		lesson.setGroup("ST20");
		Lesson lessonFromDB = lessonDAO.saveAndFlush(lesson);
		Group group = new Group();
		group.setGroupName("ST20");
		group.setCourse(1);
		lesson.setGroup(group.getGroupName());
		Group groupFromDB = groupDAO.saveAndFlush(group);
		List<Lesson> lessonsFromDB = lessonDAO.getLessonsForGroupForPeriod(groupFromDB.getGroupName(), 0L, 3L);
		assertNotNull(lessonsFromDB);
		// assertEquals(lessonFromDB.getLessonId(), lessonsFromDB.get(0).getLessonId());
		groupDAO.delete(groupFromDB.getGroupName());
		lessonDAO.delete(lessonFromDB.getLessonId());

	}

	@Test
	public void testGetLessonsForPeriod() {
		Lesson lesson = new Lesson();
		lesson.setLessonStart(2L);
		lesson.setLength(1L);
		lesson.setRoom("2");
		lesson.setSubject("Math");
		lesson.setTeacher("Ivanov");
		lesson.setGroup("ST20");
		Lesson lessonFromDB = lessonDAO.saveAndFlush(lesson);
		Group group = new Group();
		group.setGroupName("ST20");
		lesson.setGroup(group.getGroupName());
		Group groupFromDB = groupDAO.saveAndFlush(group);
		List<Lesson> lessonsFromDB = lessonDAO.getLessonsForPeriod("Ivanov", 2L, 3L);
		assertNotNull(lessonsFromDB);
		// assertEquals(lessonFromDB.getLessonId(), lessonsFromDB.get(0).getLessonId());
		groupDAO.delete(groupFromDB.getGroupName());
		lessonDAO.delete(lessonFromDB.getLessonId());

	}

	@Test
	public void testGetOneByGroupAndStartTime() {
		Lesson lesson = new Lesson();
		lesson.setLessonStart(2L);
		lesson.setLength(0L);
		lesson.setRoom("2");
		lesson.setSubject("Math");
		lesson.setTeacher("Ivanov");
		Group group = new Group();
		group.setGroupName("ST20");
		lesson.setGroup(group.getGroupName());
		Lesson lessonFromDB = lessonDAO.save(lesson);
		Group groupFromDB = groupDAO.save(group);
		Lesson lessonDB = lessonDAO.getOneByGroupAndStartTime(groupFromDB.getGroupName(), 2L);
		assertNotNull(lessonDB);
		groupDAO.delete(groupFromDB.getGroupName());
		lessonDAO.delete(lessonFromDB.getLessonId());

	}

	@Test
	public void testGetOneByTeacherAndStartTime() {
		Lesson lesson = new Lesson();
		lesson.setLessonStart(2L);
		lesson.setLength(0L);
		lesson.setRoom("2");
		lesson.setSubject("Math");
		lesson.setTeacher("Ivanov");
		Group group = new Group();
		group.setGroupName("ST20");
		lesson.setGroup(group.getGroupName());
		Lesson lessonFromDB = lessonDAO.save(lesson);
		Group groupFromDB = groupDAO.save(group);
		Lesson lessonDB = lessonDAO.getOneByTeacherAndStartTime("Ivanov", 2L);
		assertNotNull(lessonDB);
		groupDAO.delete(groupFromDB.getGroupName());
		lessonDAO.delete(lessonFromDB.getLessonId());

	}

}
