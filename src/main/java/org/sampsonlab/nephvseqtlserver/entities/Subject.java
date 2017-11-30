package org.sampsonlab.nephvseqtlserver.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subjects")
public class Subject {
	
	@Id
	@Column(name="subjectID")
	private Integer subjectId;
	
	@Column(name="numTissues")
	private Integer numTissues;

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getNumTissues() {
		return numTissues;
	}

	public void setNumTissues(Integer numTissues) {
		this.numTissues = numTissues;
	}
	
	
}
