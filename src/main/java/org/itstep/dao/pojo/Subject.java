package org.itstep.dao.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "SUBJECTS")
public class Subject {

	@Id
	@Column(name = "SUBJECT")
	private String subject;

	public Subject() {

	}

}
