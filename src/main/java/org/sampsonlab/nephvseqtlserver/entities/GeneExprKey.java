package org.sampsonlab.nephvseqtlserver.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GeneExprKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3568288095491591007L;

	@Column(name="tissue")	
	private String tissue;
	
	@Column(name="entrezId")	
	private Long entrezId;
	
	@Column(name="subjectId")	
	private Integer subjectId;

	public String getTissue() {
		return tissue;
	}

	public void setTissue(String tissue) {
		this.tissue = tissue;
	}

	public Long getEntrezId() {
		return entrezId;
	}

	public void setEntrezId(Long entrezId) {
		this.entrezId = entrezId;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	
	
}
