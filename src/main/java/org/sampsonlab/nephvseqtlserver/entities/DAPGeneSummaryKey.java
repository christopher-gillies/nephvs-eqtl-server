package org.sampsonlab.nephvseqtlserver.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DAPGeneSummaryKey implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8436135306395122673L;

	@Column(name="dataType", nullable = false)	
	private String dataType; 
	
	@Column(name="entrezId", nullable = false)	
	private Long entrezId;

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Long getEntrezId() {
		return entrezId;
	}

	public void setEntrezId(Long entrezId) {
		this.entrezId = entrezId;
	}
	
	
	
}
